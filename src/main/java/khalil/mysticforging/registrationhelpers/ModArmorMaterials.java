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

    
}
