package khalil.mysticforging.registrationhelpers;

import khalil.mysticforging.MysticForging;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final ItemGroup SAURID_MAIN_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MysticForging.MOD_ID, ""),
            FabricItemGroup.builder().icon(() -> new ItemStack(Items.SMITHING_TABLE))
                    .displayName(Text.translatable("itemgroup.saurid-dungeon.saurid_main"))
                    .entries((displayContext, entries) -> {
                        entries.add(Items.SMITHING_TABLE);
                    }).build());

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MysticForging.MOD_ID, name), item);
    }

    public static void registerModItems() {
        MysticForging.LOGGER.info("Registering Mod Items for " + MysticForging.MOD_ID);
    }
}
