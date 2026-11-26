package com.rumin.apocrypha.tag;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

public class ModEffectTags {
    public static final TagKey<MobEffect> RELIC=bind("relic");
    public static final TagKey<MobEffect> IRREMOVABLE=bind("irremovable");

    private static TagKey<MobEffect> bind(String name) {
        return TagKey.create(Registries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,name));
    }
}
