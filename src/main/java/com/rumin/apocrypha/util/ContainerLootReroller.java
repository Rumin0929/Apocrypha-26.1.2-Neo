package com.rumin.apocrypha.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ContainerLootReroller {
    private ContainerLootReroller() {
    }

    public static void rerollLoot(ServerPlayer serverPlayer, RandomizableContainer randomizableContainer){
        if (randomizableContainer.getLootTable()==null){
            serverPlayer.sendSystemMessage(Component.literal("这个箱子已经开过了，不能重随").withStyle(ChatFormatting.GRAY));
            return;
        }

        ServerLevel serverLevel=(ServerLevel) serverPlayer.level();

        long newSeed=serverLevel.getRandom().nextLong();
        while (newSeed==0L){
            newSeed=serverLevel.getRandom().nextLong();
        }

        randomizableContainer.setLootTableSeed(newSeed);

        if (randomizableContainer instanceof BlockEntity blockEntity){
            blockEntity.setChanged();
        }

        serverPlayer.sendSystemMessage(Component.literal("箱子的内容已被重置").withStyle(ChatFormatting.GOLD));
    }
}
