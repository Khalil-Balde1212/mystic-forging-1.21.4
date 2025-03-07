package khalil.mysticforging.registrationhelpers;

import java.util.Map;

import khalil.mysticforging.MysticForging;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModArmorMaterials {
    public static final RegistryKey<EquipmentAsset> MYSTIC_LEATHER_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(MysticForging.MOD_ID, "mystic_leather"));
    public static final RegistryKey<EquipmentAsset> MYSTIC_IRON_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(MysticForging.MOD_ID, "mystic_iron"));
    public static final RegistryKey<EquipmentAsset> MYSTIC_DIAMOND_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(MysticForging.MOD_ID, "mystic_diamond"));
    public static final RegistryKey<EquipmentAsset> MYSTIC_NETHERITE_ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(MysticForging.MOD_ID, "mystic_netherite"));

    public static final int BASE_DURABILITY = 5;

    public static final ArmorMaterial MYSTIC_LEATHER = new ArmorMaterial(
        5, 
        Map.of(
            EquipmentType.HELMET, 1,
            EquipmentType.CHESTPLATE, 3,
            EquipmentType.LEGGINGS, 2,
            EquipmentType.BOOTS, 1,
            EquipmentType.BODY, 3
        )
        , 5, 
        SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 
        0.0F, 
        0.0F, 
        ItemTags.REPAIRS_LEATHER_ARMOR,
        MYSTIC_LEATHER_ARMOR_MATERIAL_KEY);

    
    public static final ArmorMaterial MYSTIC_IRON = new ArmorMaterial(
        15, 
        Map.of(
            EquipmentType.HELMET, 2,
            EquipmentType.CHESTPLATE, 6,
            EquipmentType.LEGGINGS, 5,
            EquipmentType.BOOTS, 2,
            EquipmentType.BODY, 6
        ), 
        9, 
        SoundEvents.ITEM_ARMOR_EQUIP_IRON, 
        0.0F, 
        0.0F, 
        ItemTags.REPAIRS_IRON_ARMOR,
        MYSTIC_IRON_ARMOR_MATERIAL_KEY);

    public static final ArmorMaterial MYSTIC_DIAMOND = new ArmorMaterial(
        33, 
        Map.of(
            EquipmentType.HELMET, 3,
            EquipmentType.CHESTPLATE, 8,
            EquipmentType.LEGGINGS, 6,
            EquipmentType.BOOTS, 3,
            EquipmentType.BODY, 8
        ), 
        10, 
        SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 
        2.0F, 
        0.0F, 
        ItemTags.REPAIRS_DIAMOND_ARMOR,
        MYSTIC_DIAMOND_ARMOR_MATERIAL_KEY);

    public static final ArmorMaterial MYSTIC_NETHERITE = new ArmorMaterial(
        37, 
        Map.of(
            EquipmentType.HELMET, 3,
            EquipmentType.CHESTPLATE, 8,
            EquipmentType.LEGGINGS, 6,
            EquipmentType.BOOTS, 3,
            EquipmentType.BODY, 8
        ), 
        15, 
        SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 
        3.0F, 
        0.1F, 
        ItemTags.REPAIRS_NETHERITE_ARMOR,
        MYSTIC_NETHERITE_ARMOR_MATERIAL_KEY);
}
