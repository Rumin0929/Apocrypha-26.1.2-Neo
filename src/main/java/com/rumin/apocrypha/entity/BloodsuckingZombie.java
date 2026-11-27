package com.rumin.apocrypha.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;

public class BloodsuckingZombie extends Zombie {
    public BloodsuckingZombie(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
    }
}
