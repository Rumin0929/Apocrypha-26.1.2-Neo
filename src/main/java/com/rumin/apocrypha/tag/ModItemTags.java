package com.rumin.apocrypha.tag;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> TAROT_CARD=bind("tarot_card");
    public static final TagKey<Item> SILVER_TOOL_MATERIALS =bind("silver_tool_materials");
    public static final TagKey<Item> SILVER_ARMOR_MATERIALS=bind("silver_armor_materials");
    public static final TagKey<Item> HOLY=bind("holy");
    public static final TagKey<Item> RELIC=bind("relic");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,name));
    }
}
