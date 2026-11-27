package com.rumin.apocrypha.effect;

import com.rumin.apocrypha.Apocrypha;
import com.rumin.apocrypha.tag.ModEffectTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EventBusSubscriber(modid=Apocrypha.MOD_ID)
public class ApocryphaModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS=
            DeferredRegister.create(Registries.MOB_EFFECT, Apocrypha.MOD_ID);

//  状态效果
    public static final DeferredHolder<MobEffect,MobEffect> INVULNERABILITY =
            EFFECTS.register("invulnerability",()->new InvulnerabilityEffect(MobEffectCategory.BENEFICIAL,16766720));
    public static final DeferredHolder<MobEffect,MobEffect> HEMOPHOBIA =
            EFFECTS.register("hemophobia",()->new HemophobiaEffect(MobEffectCategory.HARMFUL,7043727));
    public static final DeferredHolder<MobEffect, MobEffect> GHOST =
            EFFECTS.register("ghost", () -> new MobEffect(MobEffectCategory.NEUTRAL, 2367022) {
                {
                    addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID, "effect.ghost"),
                            10.0, AttributeModifier.Operation.ADD_VALUE);

                }
            });
    public static final DeferredHolder<MobEffect,MobEffect> INSIGHT=
            EFFECTS.register("insight",()->new InsightEffect(MobEffectCategory.NEUTRAL,5231103));


//  圣遗物
    public static final DeferredHolder<MobEffect,MobEffect> WAFER=
            EFFECTS.register("wafer",()->new WaferEffect(MobEffectCategory.NEUTRAL,15526857));
    public static final DeferredHolder<MobEffect,MobEffect> ETERNAL_HEART=
            EFFECTS.register("eternal_heart",()->new MobEffect(MobEffectCategory.BENEFICIAL,15398143){
                {
                    addAttributeModifier(Attributes.MAX_HEALTH,
                            Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,"effect.eternal_heart"),
                            8.0,AttributeModifier.Operation.ADD_VALUE);
                }
            });



    public static void register(IEventBus bus){
        EFFECTS.register(bus);
    }

    private static final Set<UUID> BYPASS =new HashSet<>();

    public static void removeIrremovable(LivingEntity entity, Holder<MobEffect> effect){
        BYPASS.add(entity.getUUID());
        try {
            entity.removeEffect(effect);
        }finally {
            BYPASS.remove(entity.getUUID());
        }
    }

    public static void removeAllIrremovable(LivingEntity entity){
        BYPASS.add(entity.getUUID());
        try {
            entity.removeAllEffects();
        }finally {
            BYPASS.remove(entity.getUUID());
        }
    }

    @SubscribeEvent
    public static void keepEffect(MobEffectEvent.Remove event){
        if (BYPASS.contains(event.getEntity().getUUID()))return;
        if (event.getEffect().is(ModEffectTags.IRREMOVABLE)){
            event.setCanceled(true);
        }
    }
}
