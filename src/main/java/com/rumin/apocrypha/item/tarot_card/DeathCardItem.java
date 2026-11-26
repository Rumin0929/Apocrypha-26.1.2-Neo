package com.rumin.apocrypha.item.tarot_card;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class DeathCardItem extends Item {
    public DeathCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level, player, hand);

        if (!level.isClientSide()){
            if (player instanceof ServerPlayer serverPlayer){
                serverPlayer.setHealth(0.001F);
                level.playSound(null,player.getX(),player.getY(),player.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.PLAYERS,1.0F,1.0F);
            }
        }

        return result;
    }
}
