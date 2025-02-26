package khalil.mysticforging.mysticSigils;

import khalil.mysticforging.MysticForging;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffects;

public class MysticSigils {
    public static MysticSigil EARTH_BULWARK1 = new MysticSigil(
        "Earth Bulwark", MysticSigil.School.EARTH, 1, Blocks.COBBLESTONE, MysticSigil.Patterns.BULLWARK_1, StatusEffects.RESISTANCE.value());
    
        public static MysticSigil AIR_PATHFINDER1 = new MysticSigil(
        "Air Pathfinder", MysticSigil.School.AIR, 1, Blocks.COBBLESTONE, MysticSigil.Patterns.PATHFINDER_1, StatusEffects.SPEED.value());
    
    public void init(){
        MysticForging.LOGGER.info("Initializing Mystic Sigils for: " + MysticForging.MOD_ID);
    }
}
