package khalil.mysticforging.registrationhelpers;

import java.util.function.Function;

import khalil.mysticforging.MysticForging;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MYSTIC_UPGRADE_TEMPLATE = registerItem("mystic_upgrade_template", Item::new,
            new Item.Settings());

    public static final ItemGroup SAURID_MAIN_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MysticForging.MOD_ID, "mystic_forging_main"),
            FabricItemGroup.builder().icon(() -> new ItemStack(Items.SMITHING_TABLE))
                    .displayName(Text.translatable("itemgroup.mystic_forging_main"))
                    .entries((displayContext, entries) -> {
                        entries.add(MYSTIC_UPGRADE_TEMPLATE);
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
