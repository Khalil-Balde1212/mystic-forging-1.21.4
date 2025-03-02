package khalil.mysticforging.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import khalil.mysticforging.mysticSigils.MysticSigil;
import khalil.mysticforging.mysticSigils.MysticSigils;
import khalil.mysticforging.registrationhelpers.ModBlocks;

public class MysticFocusBlockEntity extends BlockEntity{
    public MysticFocusBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.MYSTIC_FOCUS_BLOCK_ENTITY, pos, state);
    }
    
    public static void tick(World world, BlockPos pos, BlockState state, MysticFocusBlockEntity blockEntity)  {
        if (world != null && !world.isClient) {
            checkPatterns(world, pos);
        }
    }

    private static void checkPatterns(World world, BlockPos pos) {
        for (MysticSigil sigil : MysticSigils.sigilList) {
            if (checkPattern(world, pos, sigil.getPattern().pattern(), sigil.getFocusBlock())) {
                applyEffects(world, pos, sigil);
            }
        }
    }

    private static boolean checkPattern(World world, BlockPos centerPos, String[] pattern, Block requiredBlock) {
        int patternSize = pattern.length;
        int offset = patternSize / 2; // Center the pattern around the block

        for (int z = 0; z < patternSize; z++) {
            for (int x = 0; x < pattern[z].length(); x++) {
                char symbol = pattern[z].charAt(x);
                BlockPos checkPos = centerPos.add(x - offset, -1, z - offset);
                Block actualBlock = world.getBlockState(checkPos).getBlock();

                switch (symbol) {
                    case 'X': // Must be the required block
                        if (actualBlock != requiredBlock)
                            return false;
                        break;
                    case ' ': // Wildcard, any block allowed
                        if (actualBlock == requiredBlock) {
                            return false;
                        }
                    default:
                        break;
                }
            }
        }

        return true; // Pattern matched
    }

    private static void applyEffects(World world, BlockPos pos, MysticSigil sigil) {
        // Apply relevant effects in a radius around the block
        // Example: Apply a status effect to all entities in a radius
        int radius = 5;
        world.getEntitiesByClass(LivingEntity.class, new Box(pos).expand(radius), entity -> true)
            .forEach(entity -> {
                sigil.applyEffect(entity);
            });
    }
}
