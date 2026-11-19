package com.rumin.apocrypha.tag;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> SILVER=create("silver");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,name));
    }

}
