package com.rumin.apocrypha.item.tarot_card;

import com.rumin.apocrypha.util.TimeController;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class HermitCardItem extends Item {

    public HermitCardItem(Properties properties){super(properties);}

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level,player,hand);

        if (!level.isClientSide()){
            MinecraftServer server=level.getServer();
            if (server!=null) {
                TimeController.pause(server,5.0F);
            }
        }

        return result;
    }

}
