package com.rumin.apocrypha.item;

import com.google.common.collect.Maps;
import com.rumin.apocrypha.Apocrypha;
import com.rumin.apocrypha.tag.ModItemTags;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.*;

import java.util.Map;

public interface ModArmorMaterials {
    ArmorMaterial SILVER =new ArmorMaterial(
            17,makeDefense(2,5,7,2,6),18,
            SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F,
            ModItemTags.SILVER_ARMOR_MATERIALS,createId("silver")
            );

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }

    static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Apocrypha.MOD_ID,name));
    }
}
