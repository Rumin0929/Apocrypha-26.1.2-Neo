package com.rumin.apocrypha.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.TeleportTransition;

public class FoolCardItem extends Item {
    public FoolCardItem (Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level,player,hand);

        if (!level.isClientSide()){
            if (player instanceof ServerPlayer serverPlayer){
                serverPlayer.setRespawnPosition(null,false);//删除个人重生点
                serverPlayer.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0F);//将血量上限设为20
                serverPlayer.setHealth(20);//将血量设为20
                serverPlayer.getFoodData().setFoodLevel(20);//将饥饿值设为20
                serverPlayer.getFoodData().setSaturation(5.0F);//将饱和度设为5
                serverPlayer.setExperiencePoints(0);//归零经验值
                serverPlayer.setExperienceLevels(0);//归零经验等级
                serverPlayer.teleport(TeleportTransition.createDefault(serverPlayer,TeleportTransition.DO_NOTHING));//传送回世界出生点
            }

        }

        return  result;
    }
}
