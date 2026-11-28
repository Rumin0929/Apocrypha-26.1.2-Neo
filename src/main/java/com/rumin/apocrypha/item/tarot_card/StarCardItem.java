package com.rumin.apocrypha.item.tarot_card;

import com.rumin.apocrypha.util.ContainerLootReroller;
import com.rumin.apocrypha.util.LookedAtContainerFinder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class StarCardItem extends Item {
    public StarCardItem(Properties properties) {
        super(properties);
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level, player, hand);

        if (!level.isClientSide()){
            if (player instanceof ServerPlayer serverPlayer){
                RandomizableContainer randomizableContainer=LookedAtContainerFinder.findLoot(serverPlayer);
                if (randomizableContainer==null){
                    serverPlayer.sendSystemMessage(Component.literal("面前没有宝箱").withStyle(ChatFormatting.GRAY));
                }
                ContainerLootReroller.rerollLoot(serverPlayer,randomizableContainer);
                if (randomizableContainer.getLootTable()!=null){
                    level.playSound(null,player.getX(),player.getY(),player.getZ(), SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.PLAYERS,1.0F,1.0F);
                }
            }
        }

        return result;
    }
}
