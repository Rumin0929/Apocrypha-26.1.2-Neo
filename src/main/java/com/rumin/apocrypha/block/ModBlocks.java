package com.rumin.apocrypha.block;

import com.rumin.apocrypha.Apocrypha;
import com.rumin.apocrypha.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Apocrypha.MOD_ID);

    public static final DeferredBlock<Block> SILVER_BLOCK =registerBlock("silver_block",Block::new,
            ()->BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK),true);
    public static final DeferredBlock<Block> SILVER_ORE =registerBlock("silver_ore",Block::new,
            ()->BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE),true);
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE=registerBlock("deepslate_silver_ore",Block::new,
            ()->BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE),true);
    public static final DeferredBlock<Block> RAW_SILVER_BLOCK =registerBlock("raw_silver_block",Block::new,
            ()->BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK),true);

    private static <T extends Block>DeferredBlock<T> registerBlock(String name,
                                                                   Function<BlockBehaviour.Properties, T> func,
                                                                   Supplier<BlockBehaviour.Properties> properties,
                                                                   boolean shouldRegisterItem) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, func, properties);
        if (shouldRegisterItem) ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    private static <T extends Block>DeferredBlock<T> registerBlock(String name,
                                                                   Function<BlockBehaviour.Properties, T> func,
                                                                   boolean shouldRegisterItem) {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, func);
        if (shouldRegisterItem) ModItems.ITEMS.registerSimpleBlockItem(block);
        return block;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        Apocrypha.LOGGER.info("Register Mod Blocks for"+Apocrypha.MOD_ID);
    }

}