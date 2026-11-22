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
public class DoomMarker {

    private static final String PREFIX = "apocrypha:doom_";
    private static final int[] warningHurtTick = {10, 40, 70, 90, 110, 120, 130, 135};


    //做标记以及是否成功打上标记
    public static boolean markDoomWithOutcome(Player player, long deadline) {
        if (getDeadline(player)>=0)return false;
        clearMark(player);
        player.addTag(PREFIX + deadline);
        return true;
    }

    //获取死亡时间
    public static long getDeadline(Player player) {
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
    public static void clearMark(Player player) {
        player.entityTags().removeIf(tag -> tag.startsWith(PREFIX));
    }

    @SubscribeEvent
    public static void deathJudgement(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        Holder<WorldClock> clock = server.registryAccess().getOrThrow(WorldClocks.OVERWORLD);
        long currentTime = server.clockManager().getTotalTicks(clock);

        for (ServerPlayer sp : server.getPlayerList().getPlayers()) {
            long deadline = getDeadline(sp);
            if (deadline < 0 || currentTime < deadline) continue;

            Holder<DamageType> type = sp.level().registryAccess()
                    .lookupOrThrow(Registries.DAMAGE_TYPE)
                    .getOrThrow(ResourceKey.create(
                            Registries.DAMAGE_TYPE,
                            Identifier.fromNamespaceAndPath("apocrypha", "wheel_of_fortune_card")));

            for (int j : warningHurtTick) {
                if (currentTime == deadline + j) {
                    sp.hurtServer(sp.level(), new DamageSource(type), 1.0F);
                }
            }

            if (currentTime >= deadline + 140) {
                clearMark(sp);
                sp.hurtServer(sp.level(), new DamageSource(type), Float.MAX_VALUE);
            }
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer sp)) return;

        DoomMarker.clearMark(sp);
    }


}