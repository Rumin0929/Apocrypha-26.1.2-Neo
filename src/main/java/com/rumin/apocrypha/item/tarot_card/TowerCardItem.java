package com.rumin.apocrypha.item.tarot_card;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class TowerCardItem extends Item {
    public TowerCardItem (Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level, player, hand);

        if (!level.isClientSide()){
            MinecraftServer server=level.getServer();
            if (server!=null) {
                server.setWeatherParameters(0, 999999999, true, true);
            }
        }

        return result;
    }
}
