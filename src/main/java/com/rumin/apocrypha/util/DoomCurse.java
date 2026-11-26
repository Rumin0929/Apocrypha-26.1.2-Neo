package com.rumin.apocrypha.util;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class DoomCurse {

    private static final String PREFIX = "apocrypha:doom_";
    private static final int[] WARNING_HURT_TICKS = {10, 40, 70, 90, 110, 120, 130, 135};


    //做标记以及是否成功打上标记
    public static boolean trySetTag(Player player, long doomTime) {
        if (getDoomTime(player)>=0)return false;
        clearTag(player);
        player.addTag(PREFIX + doomTime);
        return true;
    }

    //获取死亡时间
    public static long getDoomTime(Player player) {
        for (String tag : player.entityTags()) {
            if (tag.startsWith(PREFIX)) {
                try {
                    return Long.parseLong(tag.substring(PREFIX.length()));
                } catch (NumberFormatException e) {
                    return -1L;
                }
            }
        }
        return -1L;
    }

    //清除标记
    public static void clearTag(Player player) {
        player.entityTags().removeIf(tag -> tag.startsWith(PREFIX));
    }

    @SubscribeEvent
    public static void executeDoom(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        Holder<WorldClock> clock = server.registryAccess().getOrThrow(WorldClocks.OVERWORLD);
        long currentTime = server.clockManager().getTotalTicks(clock);

        for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
            long doomTime = getDoomTime(serverPlayer);
            if (doomTime < 0 || currentTime < doomTime) continue;

            Holder<DamageType> type = serverPlayer.level().registryAccess()
                    .lookupOrThrow(Registries.DAMAGE_TYPE)
                    .getOrThrow(ResourceKey.create(
                            Registries.DAMAGE_TYPE,
                            Identifier.fromNamespaceAndPath("apocrypha", "destined_death")));

            for (int j : WARNING_HURT_TICKS) {
                if (currentTime == doomTime + j) {
                    serverPlayer.hurtServer(serverPlayer.level(), new DamageSource(type), 1.0F);
                }
            }

            if (currentTime >= doomTime + 140) {
                clearTag(serverPlayer);
                serverPlayer.hurtServer(serverPlayer.level(), new DamageSource(type), Float.MAX_VALUE);
            }
        }
    }

    public static void deathHandler(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

        DoomCurse.clearTag(serverPlayer);
        TeleportInvulnerability.clearTag(serverPlayer);
    }

}