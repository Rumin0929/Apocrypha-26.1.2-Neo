package com.rumin.apocrypha.util;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class RecipeUnlocker {

    @SubscribeEvent
    public static void unlockAll(PlayerEvent.PlayerLoggedInEvent event){
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer))return;
        serverPlayer.awardRecipes(serverPlayer.level().getServer().getRecipeManager().getRecipes());
    }
}
