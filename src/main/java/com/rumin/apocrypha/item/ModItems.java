package com.rumin.apocrypha.item;

import com.rumin.apocrypha.Apocrypha;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Apocrypha.MOD_ID);

//    正位塔罗牌
    public static final DeferredItem<Item> FOOL_CARD=ITEMS.registerItem("tarot/fool_card",
            FoolCardItem::new,
            props->props.component(DataComponents.CONSUMABLE,ModConsumables.FOOL_CARD).useCooldown(1.0F));
    public static final DeferredItem<Item> MAGICIAN_CARD=ITEMS.registerSimpleItem("tarot/magician_card");
    public static final DeferredItem<Item> HIGH_PRIESTESS_CARD=ITEMS.registerSimpleItem("tarot/high_priestess_card");
    public static final DeferredItem<Item> EMPRESS_CARD=ITEMS.registerSimpleItem("tarot/empress_card");
    public static final DeferredItem<Item> EMPEROR_CARD=ITEMS.registerSimpleItem("tarot/emperor_card");
    public static final DeferredItem<Item> HIEROPHANT_CARD=ITEMS.registerSimpleItem("tarot/hierophant_card",
            ()->new Item.Properties().component(DataComponents.CONSUMABLE, ModConsumables.HIEROPHANT_CARD).useCooldown(1.0F));
    public static final DeferredItem<Item> LOVERS_CARD=ITEMS.registerSimpleItem("tarot/lovers_card");
    public static final DeferredItem<Item> CHARIOT_CARD=ITEMS.registerSimpleItem("tarot/chariot_card");
    public static final DeferredItem<Item> STRENGTH_CARD=ITEMS.registerSimpleItem("tarot/strength_card",
            ()->new Item.Properties().component(DataComponents.CONSUMABLE,ModConsumables.STRENGTH_CARD).useCooldown(1.0F));
    public static final DeferredItem<Item> HERMIT_CARD=ITEMS.registerItem("tarot/hermit_card",
            HermitCardItem::new,
            props->props.component(DataComponents.CONSUMABLE,ModConsumables.HERMIT_CARD).useCooldown(5.0F));
    public static final DeferredItem<Item> WHEEL_OF_FORTUNE_CARD=ITEMS.registerItem("tarot/wheel_of_fortune_card",
            WheelOfFortuneCardItem::new,
            props->props.component(DataComponents.CONSUMABLE,ModConsumables.WHEEL_OF_FORTUNE_CARD).useCooldown(60.0F));
    public static final DeferredItem<Item> JUSTICE_CARD=ITEMS.registerSimpleItem("tarot/justice_card");
    public static final DeferredItem<Item> HANGED_MAN_CARD=ITEMS.registerSimpleItem("tarot/hanged_man_card");
    public static final DeferredItem<Item> DEATH_CARD=ITEMS.registerSimpleItem("tarot/death_card");
    public static final DeferredItem<Item> TEMPERANCE_CARD=ITEMS.registerSimpleItem("tarot/temperance_card");
    public static final DeferredItem<Item> DEVIL_CARD=ITEMS.registerSimpleItem("tarot/devil_card");
    public static final DeferredItem<Item> TOWER_CARD=ITEMS.registerItem("tarot/tower_card",
            TowerCardItem::new,
            props->props.component(DataComponents.CONSUMABLE,ModConsumables.TOWER_CARD).useCooldown(1.0F));
    public static final DeferredItem<Item> STAR_CARD=ITEMS.registerSimpleItem("tarot/star_card");
    public static final DeferredItem<Item> MOON_CARD=ITEMS.registerItem("tarot/moon_card",
            MoonCardItem::new,
            props->props.component(DataComponents.CONSUMABLE,ModConsumables.MOON_CARD).useCooldown(1.0F));
    public static final DeferredItem<Item> SUN_CARD=ITEMS.registerItem("tarot/sun_card",
           SunCardItem::new,
           props->props.component(DataComponents.CONSUMABLE,ModConsumables.SUN_CARD).useCooldown(1.0F));
    public static final DeferredItem<Item> JUDGEMENT_CARD=ITEMS.registerSimpleItem("tarot/judgement_card");
    public static final DeferredItem<Item> WORLD_CARD=ITEMS.registerSimpleItem("tarot/world_card");

//    逆位塔罗牌
    public static final DeferredItem<Item> CARD_MOON=ITEMS.registerItem("tarot/card_moon",
        CardMoonItem::new,
        props->props.component(DataComponents.CONSUMABLE,ModConsumables.CARD_MOON).useCooldown(1.0F));
    public static final DeferredItem<Item> CARD_SUN=ITEMS.registerItem("tarot/card_sun",
           CardSunItem::new,
            props->props.component(DataComponents.CONSUMABLE,ModConsumables.CARD_SUN).useCooldown(1.0F));

//    材料
    public static final DeferredItem<Item> RAW_SILVER=ITEMS.registerSimpleItem("raw_silver");
    public static final DeferredItem<Item> SILVER_INGOT=ITEMS.registerSimpleItem("silver_ingot");
    public static final DeferredItem<Item> EMERALD_SHARD=ITEMS.registerSimpleItem("emerald_shard");

//    工具
    public static final DeferredItem<Item> SILVER_SWORD=ITEMS.registerItem("silver_sword",
        p-> new Item(p.sword(ModToolMaterials.SILVER,3.0F,-2.4F)));
    public static final DeferredItem<Item> SILVER_PICKAXE=ITEMS.registerItem("silver_pickaxe",
            p->new Item(p.pickaxe(ModToolMaterials.SILVER,1.0F,-2.8F)));
    public static final DeferredItem<Item> SILVER_SHOVEL =ITEMS.registerItem("silver_shovel",
            p->new ShovelItem(ModToolMaterials.SILVER,1.5F,-3.0F,p));
    public static final DeferredItem<Item> SILVER_AXE=ITEMS.registerItem("silver_axe",
            p->new AxeItem(ModToolMaterials.SILVER,6.0F,-3.1F,p));
    public static final DeferredItem<Item> SILVER_HOE=ITEMS.registerItem("silver_hoe",
            p->new HoeItem(ModToolMaterials.SILVER,-2.0F,-1.0F,p));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
        Apocrypha.LOGGER.info("Registering Mod Items for "+Apocrypha.MOD_ID);
    }
}
