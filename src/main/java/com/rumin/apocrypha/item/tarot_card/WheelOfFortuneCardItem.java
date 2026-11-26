package com.rumin.apocrypha.item.tarot_card;

import com.rumin.apocrypha.util.DoomCurse;
import com.rumin.apocrypha.util.TeleportInvulnerability;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.Random;

public class WheelOfFortuneCardItem extends Item {
    public WheelOfFortuneCardItem(Properties properties){
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand){
        InteractionResult result=super.use(level, player, hand);

        if (!level.isClientSide()) {
            MinecraftServer server = level.getServer();
            if (server!=null){
                Holder<WorldClock>clock=server.registryAccess().getOrThrow(WorldClocks.OVERWORLD);
                long currentTime=server.clockManager().getTotalTicks(clock);
                Random r=new Random();
                long aliveTime=r.nextLong(14400,26401);
                long doomTime =currentTime+aliveTime;
                boolean outcome = DoomCurse.trySetTag(player, doomTime);
                if (outcome){
                    TeleportInvulnerability.clearTag(player);
                    player.sendSystemMessage(Component.literal
                            ("你将在"+(aliveTime/20/60+1)+"分钟内死亡，请享受剩余的生命，或许你可以提前了断？")
                            .withStyle(ChatFormatting.RED));
                    level.playSound(null,player.getX(),player.getY(),player.getZ(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.PLAYERS,1.0F,1.0F);
                }else {
                    player.sendSystemMessage(Component.literal
                                    ("你已经身患诅咒")
                            .withStyle(ChatFormatting.DARK_RED));
                }
            }
        }

        return result;
    }


}
