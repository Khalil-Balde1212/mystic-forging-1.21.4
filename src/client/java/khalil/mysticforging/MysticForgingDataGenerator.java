package khalil.mysticforging;

import khalil.mysticforging.datagen.ModBlockTagProvider;
import khalil.mysticforging.datagen.ModItemTagProvider;
import khalil.mysticforging.datagen.ModLootTableProvider;
import khalil.mysticforging.datagen.ModModelProvider;
import khalil.mysticforging.datagen.ModRecipeProvider;
import khalil.mysticforging.datagen.ModRegistryDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MysticForgingDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
	}
}
