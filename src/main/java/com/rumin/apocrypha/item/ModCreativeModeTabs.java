package com.rumin.apocrypha.item;

import com.rumin.apocrypha.Apocrypha;
import com.rumin.apocrypha.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Apocrypha.MOD_ID);

//    塔罗物品栏
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAROT_TAB =
            CREATIVE_MODE_TABS.register("tarot_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.apocrypha"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)//不生效，但可以把COMBAT换成别的模组物品栏使其在该物品栏之前,格式参下
                    .icon(() -> new ItemStack(ModItems.FOOL_CARD.get()))
                    .displayItems((itemDisplayParameters, output) -> {
//                        暂时没有实际功能的卡牌被隐藏起来
                        output.accept(ModItems.FOOL_CARD);
//                        output.accept(ModItems.MAGICIAN_CARD);
//                        output.accept(ModItems.HIGH_PRIESTESS_CARD);
//                        output.accept(ModItems.EMPRESS_CARD);
//                        output.accept(ModItems.EMPEROR_CARD);
                        output.accept(ModItems.HIEROPHANT_CARD);
//                        output.accept(ModItems.LOVERS_CARD);
//                        output.accept(ModItems.CHARIOT_CARD);
                        output.accept(ModItems.STRENGTH_CARD);
                        output.accept(ModItems.HERMIT_CARD);
                        output.accept(ModItems.WHEEL_OF_FORTUNE_CARD);
//                        output.accept(ModItems.JUSTICE_CARD);
//                        output.accept(ModItems.HANGED_MAN_CARD);
//                        output.accept(ModItems.DEATH_CARD);
//                        output.accept(ModItems.TEMPERANCE_CARD);
//                        output.accept(ModItems.DEVIL_CARD);
//                        output.accept(ModItems.TOWER_CARD);
//                        output.accept(ModItems.STAR_CARD);
                        output.accept(ModItems.MOON_CARD);
                        output.accept(ModItems.SUN_CARD);
//                        output.accept(ModItems.JUDGEMENT_CARD);
//                        output.accept(ModItems.WORLD_CARD);

                        output.accept(ModItems.CARD_MOON);
                        output.accept(ModItems.CARD_SUN);


                    }).build());

    /*public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAROT_TAB2 =
            CREATIVE_MODE_TABS.register("tarot_tab2", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.apocrypha2"))
                    .withTabsBefore(TAROT_TAB.getId())
                    .icon(() -> new ItemStack(ModItems.FOOL_CARD.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.FOOL_CARD);
                        output.accept(ModItems.MAGICIAN_CARD);

                    }).build());*/
//    如果要添加新的物品栏，格式参上，也就是把已有的复制一遍改个名

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
        Apocrypha.LOGGER.info("Registering Mod Creative Mode Tabs for"+Apocrypha.MOD_ID);
    }
}
