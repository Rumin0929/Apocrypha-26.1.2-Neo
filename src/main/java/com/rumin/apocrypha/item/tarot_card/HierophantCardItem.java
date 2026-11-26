package com.rumin.apocrypha.item.tarot_card;

import com.rumin.apocrypha.effect.ApocryphaModEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class HierophantCardItem extends Item {
    public HierophantCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level, player, hand);

        if (!level.isClientSide()){
            if (player instanceof ServerPlayer serverPlayer){
                if (serverPlayer.hasEffect(ApocryphaModEffects.GHOST)){
                    serverPlayer.addEffect(new MobEffectInstance(ApocryphaModEffects.INVULNERABILITY,70,0));
                }else {
                    serverPlayer.addEffect(new MobEffectInstance(ApocryphaModEffects.INVULNERABILITY,140,0));
                    serverPlayer.addEffect(new MobEffectInstance(ApocryphaModEffects.HEMOPHOBIA,140,0));
                }
            }
        }

        return result;
    }
}
