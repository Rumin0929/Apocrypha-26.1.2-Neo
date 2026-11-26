package com.rumin.apocrypha.listener;

import com.rumin.apocrypha.Apocrypha;
import com.rumin.apocrypha.util.DoomCurse;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class DeathListener {

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event){
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer))return;
        DoomCurse.deathHandler(event);
    }
}
