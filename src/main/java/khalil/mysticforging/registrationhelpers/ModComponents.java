package khalil.mysticforging.registrationhelpers;

import com.mojang.serialization.Codec;

import khalil.mysticforging.MysticForging;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<Boolean> AIR_PATHFINDER1 = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(MysticForging.MOD_ID, "air_pathfinder1"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build());

            public static final ComponentType<Boolean> EARTH_BULWARK1 = Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(MysticForging.MOD_ID, "earth_bulwark1"),
                ComponentType.<Boolean>builder().codec(Codec.BOOL).build());

    public static void registerModDataComponents() {
        MysticForging.LOGGER.info("Registering data components for: " + MysticForging.MOD_ID);
    }
}
