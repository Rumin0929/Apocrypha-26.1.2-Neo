package com.rumin.apocrypha.effect;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class InsightEffect extends MobEffect {
    public InsightEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @SubscribeEvent
    public static void insight(PlayerTickEvent.Post event){
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer))return;
        if (!(serverPlayer.hasEffect(ApocryphaModEffects.INSIGHT)))return;
        if (serverPlayer.tickCount%200!=0)return;

        ServerLevel level=(ServerLevel) serverPlayer.level();
        for (LivingEntity mob :level.getEntitiesOfClass(
                LivingEntity.class,
                serverPlayer.getBoundingBox().inflate(16),
                e->!(e instanceof Player))){
            mob.addEffect(new MobEffectInstance(MobEffects.GLOWING,100,0));
        }
        serverPlayer.removeEffect(MobEffects.DARKNESS);
        serverPlayer.removeEffect(MobEffects.BLINDNESS);

        level.playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.6F, 1.0F);

    }
}
