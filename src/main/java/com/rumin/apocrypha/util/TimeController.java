package com.rumin.apocrypha.util;


import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)//明确把这个类登记在事件总线
public final class TimeController {

    private static int acceleratePriority = 0;
    private static boolean pauseActiveState = false;

    private static long accelerateTarget = 0L;
    private static float pauseDuration = 0F;

    private TimeController() {
    }

    //加速器
    public static void accelerate(MinecraftServer server, long targetTime, float speed, int priority) {
        if (priority <= acceleratePriority) return;
        accelerateTarget = targetTime;
        acceleratePriority = priority;
        server.clockManager().setRate(
                server.registryAccess().getOrThrow(WorldClocks.OVERWORLD), speed);
    }

    //暂停器
    public static void pause(MinecraftServer server, float Duration) {
        if (pauseActiveState) return;
        pauseDuration = Duration * 20;
        server.tickRateManager().setFrozen(true);
        pauseActiveState = true;
    }

    //加速计时器
    @SubscribeEvent//引导事件总线监听器在哪
    public static void AccelerateStopJudgement(ServerTickEvent.Post event) {
        if (acceleratePriority == 0) return;

        MinecraftServer server = event.getServer();
        Holder<WorldClock> clock = server.registryAccess().getOrThrow(WorldClocks.OVERWORLD);

        if (server.clockManager().getTotalTicks(clock) >= accelerateTarget) {
            server.clockManager().setTotalTicks(clock, accelerateTarget);
            server.clockManager().setRate(clock, 1.0F);
            acceleratePriority = 0;
        }
    }

    //暂停计时器
    @SubscribeEvent
    public static void PauseStopJudgement(ServerTickEvent.Post event) {
        if (!pauseActiveState) return;

        if (pauseDuration > 0) {
            pauseDuration--;
        } else {
            MinecraftServer server = event.getServer();
            server.tickRateManager().setFrozen(false);
            pauseActiveState = false;
        }


    }
}
