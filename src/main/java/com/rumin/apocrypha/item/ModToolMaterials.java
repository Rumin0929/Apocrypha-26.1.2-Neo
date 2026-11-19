package com.rumin.apocrypha.item;

import com.rumin.apocrypha.tag.ModItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModToolMaterials {
    public static final ToolMaterial SILVER =new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            320,6.0F,2.0F,18,
            ModItemTags.SILVER_TOOL_MATERIALS);
}
