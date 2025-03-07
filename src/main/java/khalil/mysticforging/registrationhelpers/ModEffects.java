package khalil.mysticforging.registrationhelpers;

import khalil.mysticforging.MysticForging;
import khalil.mysticforging.effects.AirPathfinderEffect;
import khalil.mysticforging.effects.EarthBridgeEffect;
import khalil.mysticforging.effects.EarthPathfinderEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> AIR_PATHFINDER = registerStatusEffect("air_pathfinder",
            new AirPathfinderEffect(StatusEffectCategory.BENEFICIAL, 0xA0C4FF));

    public static final RegistryEntry<StatusEffect> EARTH_PATHFINDER = registerStatusEffect("earth_pathfinder",
            new EarthPathfinderEffect(StatusEffectCategory.BENEFICIAL, 0x8B4513));

    public static final RegistryEntry<StatusEffect> EARTH_BRIDGE = registerStatusEffect("earth_bridge",
            new EarthBridgeEffect(StatusEffectCategory.BENEFICIAL, 0x8B4513) {
            });

    public static final RegistryEntry<StatusEffect> MYSTIC_LEATHER_BASE_ARMOR_EFFECT = registerStatusEffect(
            "base_leather_effect",
            new ModStatusEffect(StatusEffectCategory.BENEFICIAL, 0x000000)
                    .addAttributeModifier(EntityAttributes.MOVEMENT_SPEED,
                            Identifier.of(MysticForging.MOD_ID, "base_leather_effect_speed"),
                            0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MysticForging.MOD_ID, name),
                statusEffect);
    }

    public static void registerModEffects() {
        MysticForging.LOGGER.info("Registering effects for: " + MysticForging.MOD_ID);
    }

    private static class ModStatusEffect extends StatusEffect {
        public ModStatusEffect(StatusEffectCategory category, int color) {
            super(category, color);
        }
    }
}
