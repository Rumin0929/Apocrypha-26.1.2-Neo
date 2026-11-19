package com.rumin.apocrypha.item;

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

public class MoonCardItem extends Item {

    public MoonCardItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        InteractionResult result = super.use(level, player, hand);

        if (!level.isClientSide()) {
            MinecraftServer server = level.getServer();
            if (server != null) {
                Holder<WorldClock> clock = server.registryAccess().getOrThrow(WorldClocks.OVERWORLD);
                long currentTime = server.clockManager().getTotalTicks(clock);
                long currentDay = currentTime / 24000;
                long targetTime = currentDay * 24000 + 11834;
                if (targetTime <= currentTime) targetTime += 24000;
                TimeController.accelerate(server, targetTime, 120,16);
            }
        }

        return result;
    }
}
