package khalil.mysticforging.mysticSigils;

import khalil.mysticforging.MysticForging;
import khalil.mysticforging.registrationhelpers.ModEffects;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffects;

public class MysticSigils {
    public static MysticSigil EARTH_BULWARK = new MysticSigil(
            "Earth Bulwark", MysticSigil.School.EARTH, 1, Blocks.COBBLESTONE, MysticSigil.Patterns.BULLWARK_1,
            StatusEffects.RESISTANCE.value());

    public static MysticSigil AIR_PATHFINDER = new MysticSigil(
            "Air Pathfinder", MysticSigil.School.AIR, 1, Blocks.WHITE_WOOL, MysticSigil.Patterns.PATHFINDER_1,
            ModEffects.AIR_PATHFINDER.value());

    public static MysticSigil EARTH_PATHFINDER = new MysticSigil(
            "Earth Pathfinder", MysticSigil.School.EARTH, 1, Blocks.COBBLESTONE, MysticSigil.Patterns.PATHFINDER_1,
            ModEffects.EARTH_PATHFINDER.value());

    public void init() {
        MysticForging.LOGGER.info("Initializing Mystic Sigils for: " + MysticForging.MOD_ID);
    }
}
