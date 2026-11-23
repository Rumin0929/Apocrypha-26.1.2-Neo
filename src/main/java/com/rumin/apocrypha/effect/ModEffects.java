package com.rumin.apocrypha.effect;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid=Apocrypha.MOD_ID)
public class ModEffects{
    public static final DeferredRegister<MobEffect> EFFECTS=
            DeferredRegister.create(Registries.MOB_EFFECT, Apocrypha.MOD_ID);

    public static final DeferredHolder<MobEffect,MobEffect> WAFER=
            EFFECTS.register("wafer",()->new MobEffect(MobEffectCategory.NEUTRAL,15526857){});





    public static void register(IEventBus bus){
        EFFECTS.register(bus);
    }



    public static final TagKey<MobEffect>RELIC=
            TagKey.create(Registries.MOB_EFFECT,
                    Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,"relic"));

    @SubscribeEvent
    public static void keepRelic(MobEffectEvent.Remove event){
        if (event.getEffect().is(RELIC)){
            event.setCanceled(true);
        }
    }
}
