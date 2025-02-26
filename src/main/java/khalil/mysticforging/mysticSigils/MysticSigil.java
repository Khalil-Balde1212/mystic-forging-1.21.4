package khalil.mysticforging.mysticSigils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;

import java.util.List;

public class MysticSigil {
    public final String NAME;
    public final int LEVEL;
    public final School SCHOOL;
    public final Block FOCUS_BLOCK;
    public final MysticPattern PATTERN;
    private final StatusEffect effect;

    public static final Codec<MysticSigil> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(MysticSigil::getName),
            School.CODEC.fieldOf("school").forGetter(MysticSigil::getSchool),
            Codec.INT.fieldOf("level").forGetter(MysticSigil::getLevel),
            Registries.BLOCK.getCodec().fieldOf("focus_block").forGetter(sigil -> sigil.FOCUS_BLOCK),
            MysticPattern.CODEC.fieldOf("pattern").forGetter(sigil -> sigil.PATTERN),
            Registries.STATUS_EFFECT.getCodec().fieldOf("effect").forGetter(sigil -> sigil.effect))
            .apply(instance, MysticSigil::new));

    public MysticSigil(String name, School school, int level, Block focusBlock, MysticPattern pattern,
            StatusEffect effect) {
        this.NAME = name;
        this.LEVEL = level;
        this.SCHOOL = school;
        this.FOCUS_BLOCK = focusBlock;
        this.PATTERN = pattern;
        this.effect = effect;
    }
    

    public void applyEffect(PlayerEntity player) {
        if (effect != null) {
            player.addStatusEffect(new StatusEffectInstance(Registries.STATUS_EFFECT.getEntry(effect), 200, LEVEL - 1, false, true));
        }
    }
    
    public StatusEffect getEffect() {
        return effect;
    }

    public String getName() {
        return NAME;
    }

    public int getLevel() {
        return LEVEL;
    }

    public School getSchool() {
        return SCHOOL;
    }

    public enum School {
        WATER, EARTH, FIRE, AIR, WOOD;

        public static final Codec<School> CODEC = Codec.STRING.xmap(
                name -> School.valueOf(name.toUpperCase()),
                School::name);
    }

    public static class Patterns {
        public static final MysticPattern BULLWARK_1 = new MysticPattern("Bullwark I", new String[] {
                "   ",
                "X0X",
                "XXX"
        });

        public static final MysticPattern PATHFINDER_1 = new MysticPattern("Pathfinder I", new String[] {
                "XXX",
                " 0X",
                "X  "
        });

        public static final MysticPattern BULLWARK_2 = new MysticPattern("Bullwark II", new String[] {
                "X   X",
                "X X X",
                "X 0 X",
                "XXXXX",
                "X   X"
        });

        public static final MysticPattern PATHFINDER_2 = new MysticPattern("Pathfinder II", new String[] {
                " XXXX",
                "   XX",
                "X 0 X",
                "XX  X",
                "XXX  "
        });
    }

    public record MysticPattern(String name, String[] pattern) {
        public static final Codec<MysticPattern> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("name").forGetter(MysticPattern::name),
                Codec.STRING.listOf().xmap(list -> list.toArray(new String[0]), List::of).fieldOf("pattern")
                        .forGetter(MysticPattern::pattern))
                .apply(instance, MysticPattern::new));
    }
}