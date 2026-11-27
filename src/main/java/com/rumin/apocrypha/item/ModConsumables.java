package com.rumin.apocrypha.item;

import com.rumin.apocrypha.effect.ApocryphaModEffects;
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

    public static final Consumable HIGH_PRIESTESS_CARD =ModConsumables.defaultTarot()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(ApocryphaModEffects.INSIGHT,180*20,0)
                    )
            ).build();

    public static final Consumable HIEROPHANT_CARD = ModConsumables.defaultTarot().build();

    public static final Consumable CHARIOT_CARD=ModConsumables.defaultTarot()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.SPEED,2400,31),
                                    new MobEffectInstance(MobEffects.JUMP_BOOST,2400,2),
                                    new MobEffectInstance(ApocryphaModEffects.HEMOPHOBIA,2400,0)
                            )
                    )
            ).build();

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

    public static final Consumable WHEEL_OF_FORTUNE_CARD = ModConsumables.defaultTarot()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(MobEffects.LUCK,1800*20,3)
                    )
            ).build();

    public static final Consumable DEATH_CARD=ModConsumables.defaultTarot()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(ApocryphaModEffects.GHOST,1200*20,0,false,true)
                    )
            ).build();

    public static final Consumable TOWER_CARD = ModConsumables.defaultTarot().build();
    public static final Consumable MOON_CARD = ModConsumables.defaultTarot().build();
    public static final Consumable SUN_CARD = ModConsumables.defaultTarot().build();

    public static final Consumable CARD_WHEEL_OF_FORTUNE_CARD = ModConsumables.defaultTarot().build();
    public static final Consumable CARD_MOON = ModConsumables.defaultTarot().build();
    public static final Consumable CARD_SUN = ModConsumables.defaultTarot().build();


    public static final Consumable WAFER=ModConsumables.defaultRelic()
        .onConsume(
                new ApplyStatusEffectsConsumeEffect(
                        new MobEffectInstance(ApocryphaModEffects.WAFER,-1,0,false,false,false)
                )
        ).build();
    public static final Consumable ETERNAL_HEART = ModConsumables.defaultRelic()
            .onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            new MobEffectInstance(ApocryphaModEffects.ETERNAL_HEART, -1, 0, false, false, false)
                    )
            ).build();


    public static Consumable.Builder defaultTarot() {
        return Consumable.builder().consumeSeconds(0F).animation(ItemUseAnimation.NONE).sound(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.BOOK_PAGE_TURN)).hasConsumeParticles(false);
    }

    public static Consumable.Builder defaultRelic() {
        return Consumable.builder().consumeSeconds(0F).animation(ItemUseAnimation.NONE).sound(SoundEvents.TRIDENT_THUNDER).hasConsumeParticles(false);
    }

}