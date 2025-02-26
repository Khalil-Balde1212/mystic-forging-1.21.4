package khalil.mysticforging.registrationhelpers;

import com.mojang.serialization.Codec;

import khalil.mysticforging.MysticForging;
import khalil.mysticforging.mysticSigils.MysticSigil;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<MysticSigil> SIGIL = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(MysticForging.MOD_ID, "sigil"),
            ComponentType.<MysticSigil>builder()
                    .codec(MysticSigil.CODEC)
                    .build());

    public static void registerModDataComponents() {
        MysticForging.LOGGER.info("Registering data components for: " + MysticForging.MOD_ID);
    }
}
