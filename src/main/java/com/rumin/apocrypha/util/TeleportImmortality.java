package com.rumin.apocrypha.util;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import static com.rumin.apocrypha.util.DoomCurse.getDoomTime;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class TeleportImmortality {
    private static final String IMMORTAL_TAG = "apocrypha:teleport_immortal";

    public static void setTag(Player player) {
        if (getDoomTime(player) >= 0) return;
        clearTag(player);
        player.addTag(IMMORTAL_TAG);
    }


    public static void clearTag(Player player) {
        player.entityTags().removeIf(tag -> tag.equals(IMMORTAL_TAG));
    }

    public static boolean hasTag(Player player) {
        return player.entityTags().contains(IMMORTAL_TAG);
    }

    @SubscribeEvent
    public static void onHurt(LivingIncomingDamageEvent event) {

        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;

        long doomTime = getDoomTime(serverPlayer);
        if (doomTime >= 0 && hasTag(serverPlayer)) {
            if (event.getSource().getMsgId().equals("destined_death"))return;
            event.setCanceled(true);
            boolean ok = false;
            for (int i = 0; i < 16 && !ok; i++) {
                ok = serverPlayer.randomTeleport(
                        serverPlayer.getX() + (serverPlayer.getRandom().nextDouble() - 0.5) * 28,
                        serverPlayer.getY() + (serverPlayer.getRandom().nextDouble() - 0.5) * 28,
                        serverPlayer.getZ() + (serverPlayer.getRandom().nextDouble() - 0.5) * 28,
                        true);
            }
            Level level = serverPlayer.level();
            level.playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

}


