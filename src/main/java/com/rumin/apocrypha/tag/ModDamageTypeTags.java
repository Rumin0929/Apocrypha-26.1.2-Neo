package com.rumin.apocrypha.tag;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypeTags {
    public static TagKey<DamageType> GHOST_IMMUNE_TO=bind("ghost_immune_to");

    private static TagKey<DamageType> bind(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE,
                Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,name));
    }
}
