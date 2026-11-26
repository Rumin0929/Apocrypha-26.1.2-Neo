package com.rumin.apocrypha.effect;

import com.rumin.apocrypha.Apocrypha;
import com.rumin.apocrypha.tag.ModDamageTypeTags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class GhostEffect extends MobEffect {
    public GhostEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    public static void damageHandler(LivingIncomingDamageEvent event) {
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
