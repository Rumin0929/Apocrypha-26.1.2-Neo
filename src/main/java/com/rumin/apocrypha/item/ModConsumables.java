package com.rumin.apocrypha.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ClearAllStatusEffectsConsumeEffect;

import java.util.List;

public class ModConsumables {
    public static final Consumable FOOL_CARD = ModConsumables.defaultTarot()
            .onConsume(
                    new ClearAllStatusEffectsConsumeEffect()
            ).build();

    public static final Consumable HIEROPHANT_CARD = ModConsumables.defaultTarot()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.HEALTH_BOOST, 240 * 20, 1),
                                    new MobEffectInstance(MobEffects.REGENERATION, 120, 3)
                            )
                    )).build();

    public static final Consumable STRENGTH_CARD = ModConsumables.defaultTarot()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.STRENGTH, 400, 2),
                                    new MobEffectInstance(MobEffects.HASTE, 2400, 0),
                                    new MobEffectInstance(MobEffects.SPEED, 2400, 0)
                            )
                    )).build();

    public static final Consumable HERMIT_CARD = ModConsumables.defaultTarot().build();
    public static final Consumable MOON_CARD = ModConsumables.defaultTarot().build();
    public static final Consumable SUN_CARD = ModConsumables.defaultTarot().build();

    public static final Consumable CARD_MOON = ModConsumables.defaultTarot().build();
    public static final Consumable CARD_SUN = ModConsumables.defaultTarot().build();

    public static Consumable.Builder defaultTarot() {
        return Consumable.builder().consumeSeconds(0F).animation(ItemUseAnimation.NONE).sound(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.BOOK_PAGE_TURN)).hasConsumeParticles(false);
    }


}