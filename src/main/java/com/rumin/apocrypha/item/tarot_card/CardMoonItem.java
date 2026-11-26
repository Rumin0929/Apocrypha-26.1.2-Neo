package com.rumin.apocrypha.item.tarot_card;

import com.rumin.apocrypha.util.TimeController;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class CardMoonItem extends Item {

    public CardMoonItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level, player, hand);

        if (!level.isClientSide()){
            MinecraftServer server=level.getServer();
            if (server!=null){
                Holder<WorldClock> clock=server.registryAccess().getOrThrow(WorldClocks.OVERWORLD);
                long currentTime=server.clockManager().getTotalTicks(clock);
                long currentDayTime=currentTime%24000;
                if (currentDayTime<11834){
                    server.clockManager().setTotalTicks(clock,currentTime-currentDayTime+18000);
                }else if (currentDayTime>=22200){
                    server.clockManager().setTotalTicks(clock,currentTime-currentDayTime+42000);
                }else{
                    TimeController.accelerate(server,currentTime-currentDayTime+22200,0.2F,8);
                }
            }
        }

        return result;
    }
}
