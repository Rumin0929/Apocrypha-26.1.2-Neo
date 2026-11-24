package com.rumin.apocrypha.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

import java.util.SequencedCollection;

@EventBusSubscriber
public class NonaggressionEffect extends MobEffect {
    public NonaggressionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @SubscribeEvent
    public static void nonaggression(AttackEntityEvent event){
        if (event.getEntity().hasEffect(ApocryphaModEffects.NONAGGRESSION)){
            event.setCanceled(true);
        }

    }

}
