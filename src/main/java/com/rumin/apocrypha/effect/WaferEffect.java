package com.rumin.apocrypha.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class WaferEffect extends MobEffect {
    public WaferEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @SubscribeEvent
    public static void waferResistance(LivingIncomingDamageEvent event){
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer))return;
        if (serverPlayer.hasEffect(ModEffects.WAFER)) {
            event.setAmount(event.getOriginalAmount() * 0.5F);
        }
    }
}
