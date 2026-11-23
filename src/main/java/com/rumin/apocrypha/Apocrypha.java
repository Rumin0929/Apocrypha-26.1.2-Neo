package com.rumin.apocrypha;

import com.rumin.apocrypha.block.ModBlocks;
import com.rumin.apocrypha.effect.ModEffects;
import com.rumin.apocrypha.item.ModCreativeModeTabs;
import com.rumin.apocrypha.item.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Apocrypha.MOD_ID)
public class Apocrypha {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "apocrypha";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Apocrypha(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(this::addCreative1);
        modEventBus.addListener(this::addCreative3);
        modEventBus.addListener(this::addCreative8);
        modEventBus.addListener(this::addCreative9);
        modEventBus.addListener(this::addCreative11);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModEffects.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Apocrypha) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

//原版创造模式物品栏

//建筑方块
    public void addCreative1(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey()==CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.SILVER_BLOCK);
        }
    }

//    自然方块
    public void addCreative3(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.SILVER_ORE);
            event.accept(ModBlocks.DEEPSLATE_SILVER_ORE);
            event.accept(ModBlocks.RAW_SILVER_BLOCK);
        }
    }

//    工具与实用物品
    public void addCreative8(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey()==CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.SILVER_SHOVEL);
            event.accept(ModItems.SILVER_PICKAXE);
            event.accept(ModItems.SILVER_AXE);
            event.accept(ModItems.SILVER_HOE);
        }
    }

//    战斗用品
    public void addCreative9(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey()==CreativeModeTabs.COMBAT){
            event.accept(ModItems.SILVER_SWORD);
            event.accept(ModItems.SILVER_HELMET);
            event.accept(ModItems.SILVER_CHESTPLATE);
            event.accept(ModItems.SILVER_LEGGINGS);
            event.accept(ModItems.SILVER_BOOTS);
        }
    }

//    原材料
    public void addCreative11(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey()==CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.EMERALD_SHARD);
            event.accept(ModItems.RAW_SILVER);
            event.accept(ModItems.SILVER_INGOT);
        }
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
