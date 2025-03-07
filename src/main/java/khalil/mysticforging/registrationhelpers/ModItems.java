package khalil.mysticforging.registrationhelpers;

import java.util.function.Function;

import khalil.mysticforging.MysticForging;
import khalil.mysticforging.mysticAmor.MysticArmorItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MYSTIC_UPGRADE_TEMPLATE = registerItem("mystic_upgrade_template", Item::new,
            new Item.Settings());

    public static final Item MYSTIC_AIR_TEMPLATE = registerItem("mystic_air_template", Item::new,
            new Item.Settings());

    public static final Item MYSTIC_EARTH_TEMPLATE = registerItem("mystic_earth_template", Item::new,
            new Item.Settings());

    public static final Item MYSTIC_FIRE_TEMPLATE = registerItem("mystic_fire_template", Item::new,
            new Item.Settings());

    public static final Item MYSTIC_LAVA_TEMPLATE = registerItem("mystic_lava_template", Item::new,
            new Item.Settings());

    public static final Item MYSTIC_LEATHER_HELMET = registerItem(
            "mystic_leather_helmet",
            settings -> new ArmorItem(ModArmorMaterials.MYSTIC_LEATHER, EquipmentType.HELMET, settings),
            new Item.Settings().maxDamage(EquipmentType.HELMET.getMaxDamage(ModArmorMaterials.BASE_DURABILITY)));

    public static final Item MYSTIC_LEATHER_CHESTPLATE = registerItem(
            "mystic_leather_chestplate",
            settings -> new MysticArmorItem(ModArmorMaterials.MYSTIC_LEATHER, EquipmentType.CHESTPLATE, settings),
            new Item.Settings().maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(ModArmorMaterials.BASE_DURABILITY)));

    public static final Item MYSTIC_LEATHER_LEGGINGS = registerItem(
            "mystic_leather_leggings",
            settings -> new MysticArmorItem(ModArmorMaterials.MYSTIC_LEATHER, EquipmentType.LEGGINGS, settings),
            new Item.Settings().maxDamage(EquipmentType.LEGGINGS.getMaxDamage(ModArmorMaterials.BASE_DURABILITY)));

    public static final Item MYSTIC_LEATHER_BOOTS = registerItem(
            "mystic_leather_boots",
            settings -> new MysticArmorItem(ModArmorMaterials.MYSTIC_LEATHER, EquipmentType.BOOTS, settings),
            new Item.Settings().maxDamage(EquipmentType.BOOTS.getMaxDamage(ModArmorMaterials.BASE_DURABILITY)));

    public static final ItemGroup SAURID_MAIN_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MysticForging.MOD_ID, "mystic_forging_main"),
            FabricItemGroup.builder().icon(() -> new ItemStack(Items.SMITHING_TABLE))
                    .displayName(Text.translatable("itemgroup.mystic_forging_main"))
                    .entries((displayContext, entries) -> {
                        entries.add(MYSTIC_UPGRADE_TEMPLATE);
                        entries.add(MYSTIC_AIR_TEMPLATE);
                        entries.add(MYSTIC_EARTH_TEMPLATE);
                        entries.add(MYSTIC_FIRE_TEMPLATE);
                        entries.add(MYSTIC_LAVA_TEMPLATE);

                        entries.add(MYSTIC_LEATHER_HELMET);
                        entries.add(MYSTIC_LEATHER_CHESTPLATE);
                        entries.add(MYSTIC_LEATHER_LEGGINGS);
                        entries.add(MYSTIC_LEATHER_BOOTS);

                        entries.add(ModBlocks.MYSTIC_FOCUS_BLOCK);
                    }).build());

    public static Item registerItem(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM,
                Identifier.of(MysticForging.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static void registerModItems() {
        MysticForging.LOGGER.info("Registering Mod Items for " + MysticForging.MOD_ID);
    }
}
