package com.rumin.apocrypha.entity;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = Apocrypha.MOD_ID)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES=
            DeferredRegister.create(Registries.ENTITY_TYPE,Apocrypha.MOD_ID);

    public static final DeferredHolder<EntityType<?>,EntityType<BloodsuckingZombie>> BLOODSUCKING_ZOMBIE=
            ENTITIES.register("bloodsucking_zombie",id->EntityType.Builder
                    .of(BloodsuckingZombie::new, MobCategory.MONSTER)
                    .sized(0.6F,1.95F)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,id)));

    public static void register(IEventBus bus){
        ENTITIES.register(bus);
    }

    @SubscribeEvent
    public static void attributes(EntityAttributeCreationEvent event){
        event.put(BLOODSUCKING_ZOMBIE.get(), Zombie.createAttributes().build());
    }

    @SubscribeEvent
    public static void spawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(BLOODSUCKING_ZOMBIE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
