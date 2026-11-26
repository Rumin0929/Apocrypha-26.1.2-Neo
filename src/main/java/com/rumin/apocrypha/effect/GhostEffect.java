package com.rumin.apocrypha.effect;

import com.rumin.apocrypha.tag.ModDamageTypeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber
public class GhostEffect extends MobEffect {
    public GhostEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @SubscribeEvent
    public static void fragileGhost(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
        if (serverPlayer.hasEffect(ApocryphaModEffects.GHOST)) {
            if (event.getSource().is(ModDamageTypeTags.GHOST_IMMUNE_TO)) {
                event.setCanceled(true);
            } else {
                event.setAmount(Float.MAX_VALUE);
            }
        }
    }

    @SubscribeEvent
    public static void noHeal(LivingHealEvent event){
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer))return;
        if (serverPlayer.hasEffect(ApocryphaModEffects.GHOST)){
            event.setCanceled(true);
        }
    }

}
