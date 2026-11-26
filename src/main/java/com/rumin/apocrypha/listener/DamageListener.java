package com.rumin.apocrypha.listener;

import com.rumin.apocrypha.Apocrypha;
import com.rumin.apocrypha.effect.GhostEffect;
import com.rumin.apocrypha.effect.InvulnerabilityEffect;
import com.rumin.apocrypha.effect.WaferEffect;
import com.rumin.apocrypha.util.TeleportInvulnerability;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class DamageListener {

    @SubscribeEvent
    public static void onDamage(LivingIncomingDamageEvent event){
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
//到任何一步，只要伤害被取消就停止
        WaferEffect.damageHandler(event);
        if (event.isCanceled())return;
        GhostEffect.damageHandler(event);
        if (event.isCanceled())return;
        TeleportInvulnerability.damageHandler(event);
        if (event.isCanceled())return;
        InvulnerabilityEffect.damageHandler(event);
        if (event.isCanceled())return;
    }
}
