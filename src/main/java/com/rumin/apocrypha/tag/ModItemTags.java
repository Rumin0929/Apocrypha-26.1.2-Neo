package com.rumin.apocrypha.tag;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> TAROT_CARD=bind("tarot_card");
    public static final TagKey<Item> SILVER_TOOL_MATERIALS =bind("silver_tool_materials");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,name));
    }
}
