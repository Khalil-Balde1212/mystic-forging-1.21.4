package khalil.mysticforging;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import khalil.mysticforging.registrationhelpers.ModBlocks;
import khalil.mysticforging.registrationhelpers.ModComponents;
import khalil.mysticforging.registrationhelpers.ModItems;

public class MysticForging implements ModInitializer {
	public static final String MOD_ID = "mystic-forging";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModComponents.registerModDataComponents();
	}
}