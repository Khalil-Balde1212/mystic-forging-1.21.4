package khalil.mysticforging.datagen;

import java.util.concurrent.CompletableFuture;

import khalil.mysticforging.registrationhelpers.ModItems;
import khalil.mysticforging.registrationhelpers.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.MYSTIC_LEATHER_ARMOR)
            .add(ModItems.MYSTIC_LEATHER_HELMET)
            .add(ModItems.MYSTIC_LEATHER_CHESTPLATE)
            .add(ModItems.MYSTIC_LEATHER_LEGGINGS)
            .add(ModItems.MYSTIC_LEATHER_BOOTS);
    }

    
}
