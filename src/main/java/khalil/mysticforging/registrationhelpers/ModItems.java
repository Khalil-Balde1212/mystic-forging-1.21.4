package khalil.mysticforging.registrationhelpers;

import java.util.function.Function;

import khalil.mysticforging.MysticForging;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MYSTIC_UPGRADE_TEMPLATE = registerItem("mystic_upgrade_template", Item::new,
            new Item.Settings());

    public static Item registerItem(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM,
                Identifier.of(MysticForging.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static void registerModItems() {
        MysticForging.LOGGER.info("Registering Mod Items for " + MysticForging.MOD_ID);
    }
}
