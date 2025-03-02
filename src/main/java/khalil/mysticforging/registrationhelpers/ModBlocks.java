package khalil.mysticforging.registrationhelpers;

import java.util.function.Function;

import khalil.mysticforging.MysticForging;
import khalil.mysticforging.blocks.MysticFocusBlock;
import khalil.mysticforging.blocks.MysticFocusBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block MYSTIC_FOCUS_BLOCK = register(
            "mystic_focus_block",
            MysticFocusBlock::new,
            AbstractBlock.Settings.copy(Blocks.STONE).ticksRandomly(),
            true);

    public static final BlockEntityType<MysticFocusBlockEntity> MYSTIC_FOCUS_BLOCK_ENTITY = registerBlockEntity(
            "mystic_focus_block_entity",
            FabricBlockEntityTypeBuilder.create(MysticFocusBlockEntity::new, MYSTIC_FOCUS_BLOCK).build());



    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory,
            AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType<T> blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MysticForging.MOD_ID, name), blockEntityType);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MysticForging.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MysticForging.MOD_ID, name));
    }

    public static void registerModBlocks() {
        MysticForging.LOGGER.info("Registering Mod Blocks for " + MysticForging.MOD_ID);
    }
}
