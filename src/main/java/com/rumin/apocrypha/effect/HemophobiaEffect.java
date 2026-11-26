package com.rumin.apocrypha.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber
public class HemophobiaEffect extends MobEffect {
    public HemophobiaEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @SubscribeEvent
    public static void gentle(AttackEntityEvent event){
        if (event.getEntity().hasEffect(ApocryphaModEffects.HEMOPHOBIA)){
            event.setCanceled(true);
        }

    }

}
