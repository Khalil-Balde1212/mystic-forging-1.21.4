package khalil.mysticforging.datagen;

import khalil.mysticforging.registrationhelpers.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKeys;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.MYSTIC_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYSTIC_AIR_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYSTIC_EARTH_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYSTIC_FIRE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYSTIC_LAVA_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.MYSTIC_LEATHER_HELMET, Models.GENERATED);
    }



}