package com.rumin.apocrypha.item.tarot_card;

import com.rumin.apocrypha.util.ContainerLootPreviewer;
import com.rumin.apocrypha.util.LookedAtContainerFinder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class HighPriestessCardItem extends Item {
    public HighPriestessCardItem(Properties properties) {
        super(properties);
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        InteractionResult result = super.use(level, player, hand);

        if (!level.isClientSide()) {
            if ((player instanceof ServerPlayer serverPlayer)) {
                RandomizableContainer targetContainer=LookedAtContainerFinder.findLoot(serverPlayer);
                ContainerLootPreviewer.previewLoot(serverPlayer,targetContainer);
            }
        }

        return result;
    }
}
