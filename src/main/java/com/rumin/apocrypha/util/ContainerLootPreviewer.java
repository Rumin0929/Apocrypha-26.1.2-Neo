package com.rumin.apocrypha.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public class ContainerLootPreviewer {
    private ContainerLootPreviewer() {
    }

    public static void previewLoot(ServerPlayer serverPlayer, RandomizableContainer randomizableContainer) {
        ServerLevel serverLevel = (ServerLevel) serverPlayer.level();
        ResourceKey<LootTable> lootTableKey = randomizableContainer.getLootTable();
        if (lootTableKey == null) {
            serverPlayer.sendSystemMessage(Component.literal("这个箱子已经开过了").withStyle(ChatFormatting.GRAY));
        } else {
            LootTable lootTable = serverLevel.getServer().reloadableRegistries().getLootTable(lootTableKey);
            LootParams lootParams = new LootParams.Builder(serverLevel)
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(randomizableContainer.getBlockPos()))
                    .withParameter(LootContextParams.THIS_ENTITY, serverPlayer)
                    .withLuck(serverPlayer.getLuck())
                    .create(LootContextParamSets.CHEST);

            SimpleContainer previewContainer = new SimpleContainer(randomizableContainer.getContainerSize());
            lootTable.fill(previewContainer, lootParams, randomizableContainer.getLootTableSeed());
            MutableComponent message = Component.literal("【洞察】").withStyle(ChatFormatting.AQUA);

            boolean foundAnyItem = false;
            for (int slotIndex = 0; slotIndex < randomizableContainer.getContainerSize(); slotIndex++) {
                ItemStack itemStack = previewContainer.getItem(slotIndex);
                if (itemStack.isEmpty()) {
                    continue;
                }
                if (foundAnyItem) {
                    message.append(Component.literal("、"));
                }
                message.append(itemStack.getHoverName())
                        .append(Component.literal("x" + itemStack.getCount()));
                foundAnyItem = true;
            }

            if (!foundAnyItem) {
                message.append(Component.literal("空箱子"));
            }

            serverPlayer.sendSystemMessage(message);
        }
    }
}
