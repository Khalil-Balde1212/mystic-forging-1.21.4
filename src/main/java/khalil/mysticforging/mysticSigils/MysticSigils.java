package khalil.mysticforging.mysticSigils;

import khalil.mysticforging.MysticForging;
import khalil.mysticforging.registrationhelpers.ModEffects;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import java.util.ArrayList;
import java.util.List;

public class MysticSigils {

        public static List<MysticSigil> sigilList = new ArrayList<>();

        public static MysticSigil EARTH_BULWARK = createMysticSigil(
                        "Earth Bulwark", MysticSigil.School.EARTH, 1, Blocks.COBBLESTONE,
                        MysticSigil.Patterns.BULLWARK_1,
                        StatusEffects.RESISTANCE.value());

        public static MysticSigil AIR_PATHFINDER = createMysticSigil(
                        "Air Pathfinder", MysticSigil.School.AIR, 1, Blocks.WHITE_WOOL,
                        MysticSigil.Patterns.PATHFINDER_1,
                        ModEffects.AIR_PATHFINDER.value());

        public static MysticSigil AIR_PATHFINDER2 = createMysticSigil(
                        "Air Pathfinder", MysticSigil.School.AIR, 2, Blocks.WHITE_WOOL,
                        MysticSigil.Patterns.PATHFINDER_2, ModEffects.AIR_PATHFINDER.value());

        public static MysticSigil EARTH_PATHFINDER = createMysticSigil(
                        "Earth Pathfinder", MysticSigil.School.EARTH, 1, Blocks.COBBLESTONE,
                        MysticSigil.Patterns.PATHFINDER_1,
                        ModEffects.EARTH_PATHFINDER.value());

        public static MysticSigil MAGMA_BULWARK = createMysticSigil(
                        "Magma Bulwark", MysticSigil.School.MAGMA, 2, Blocks.MAGMA_BLOCK,
                        MysticSigil.Patterns.BULLWARK_2,
                        StatusEffects.FIRE_RESISTANCE.value());

        public static MysticSigil createMysticSigil(String name, MysticSigil.School school, int level, Block block,
                        MysticSigil.MysticPattern pattern, StatusEffect effect) {
                MysticSigil newSigil = new MysticSigil(name, school, level, block, pattern, effect);
                sigilList.add(newSigil);
                return newSigil;
        }
        

        public void init() {
                MysticForging.LOGGER.info("Initializing Mystic Sigils for: " + MysticForging.MOD_ID);
        }
}
