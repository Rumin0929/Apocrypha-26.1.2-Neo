package com.rumin.apocrypha.effect;

import com.rumin.apocrypha.tag.ModDamageTypeTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class WaferEffect extends MobEffect {
    public WaferEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    public static void damageHandler(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
        if (!(serverPlayer.hasEffect(ApocryphaModEffects.WAFER))) return ;
        event.setAmount(event.getOriginalAmount() * 0.5F);

        //与幽灵buff的联动
        if (serverPlayer.hasEffect(ApocryphaModEffects.GHOST)) {
            if (event.getSource().is(ModDamageTypeTags.GHOST_IMMUNE_TO)) return;
            if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;
            event.setCanceled(true);
            serverPlayer.addEffect(new MobEffectInstance(ApocryphaModEffects.INVULNERABILITY, 20, 0));
            Level level = serverPlayer.level();
            level.playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.RESPAWN_ANCHOR_DEPLETE, SoundSource.PLAYERS, 1.0F, 1.0F);
            ApocryphaModEffects.removeIrremovable(serverPlayer, ApocryphaModEffects.WAFER);
        }
    }

}
