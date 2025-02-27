package khalil.mysticforging.datagen;

import java.util.concurrent.CompletableFuture;

import khalil.mysticforging.registrationhelpers.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return " ";
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            RegistryEntryLookup<Item> itemLookup = registryLookup.getOptional(RegistryKeys.ITEM)
                    .orElseThrow(() -> new IllegalStateException("Item registry not found"));

            @Override
            public void generate() {
                ShapedRecipeJsonBuilder
                        .create(itemLookup, RecipeCategory.COMBAT, ModItems.MYSTIC_UPGRADE_TEMPLATE)
                        .pattern("SSS")
                        .pattern("SLS")
                        .pattern("SSS")
                        .input('L', Items.LAPIS_LAZULI)
                        .input('S', Items.COBBLESTONE)
                        .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                        .offerTo(exporter);

                Item[] ironArmorPieces = {Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS};
                String[] armorPieceNames = {"iron_helmet", "iron_chestplate", "iron_leggings", "iron_boots"};

                for (int i = 0; i < ironArmorPieces.length; i++) {
                    SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(ModItems.MYSTIC_UPGRADE_TEMPLATE),
                        Ingredient.ofItems(ironArmorPieces[i]),
                        Ingredient.ofItems(Items.LAPIS_LAZULI),
                        RecipeCategory.MISC,
                        ironArmorPieces[i])
                        .criterion(hasItem(ironArmorPieces[i]), conditionsFromItem(ironArmorPieces[i]))
                        .offerTo(exporter, armorPieceNames[i] + "_test_smithing");
                }
            }
        };
    }
}
