package khalil.mysticforging.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LavaSwimEffect extends StatusEffect {
    private final Map<BlockPos, BlockState> originalBlockStates = new HashMap<>();
    private ServerWorld world;

    BlockState[] earthyBlocks = {
            Blocks.DIRT.getDefaultState(),
            Blocks.GRASS_BLOCK.getDefaultState(),
            Blocks.COARSE_DIRT.getDefaultState(),
            Blocks.PODZOL.getDefaultState(),
            Blocks.MYCELIUM.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            Blocks.GRANITE.getDefaultState(),
            Blocks.DIORITE.getDefaultState(),
            Blocks.ANDESITE.getDefaultState(),
            Blocks.DEEPSLATE.getDefaultState()
    };

    public LavaSwimEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        this.world = world;

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20, 0, false, false, true));
        if (entity instanceof PlayerEntity player) {
                BlockPos pos = entity.getBlockPos();
                int radius = 2; // Define the radius of the lava bubble

                // Detects area around the player
                for (int x = -radius; x <= radius; x++) {
                    for (int y = -radius; y <= radius; y++) {
                        for (int z = -radius; z <= radius; z++) {
                            BlockPos currentPos = pos.add(x, y, z);
                            BlockState currentBlock = world.getBlockState(currentPos);

                            if (!originalBlockStates.containsKey(currentPos)) {
                                if (isEarthyBlock(currentBlock)) {
                                    if (!currentBlock.isOf(Blocks.LAVA)) {
                                        originalBlockStates.put(currentPos, currentBlock);
                                    }
                                    world.setBlockState(currentPos, Blocks.LAVA.getDefaultState());
                                }
                            }
                        }
                    }
                }

                // Removes the lava bubble
                Set<BlockPos> positionsToRemove = new HashSet<>();

                for (BlockPos originalPos : originalBlockStates.keySet()) {
                    if (originalPos.isWithinDistance(pos, radius)) {
                        continue;
                    }
                    positionsToRemove.add(originalPos);
                }

                for (BlockPos removePos : positionsToRemove) {
                    world.setBlockState(removePos, originalBlockStates.get(removePos));
                    originalBlockStates.remove(removePos);
                }

                player.setSwimming(true);
        }
        return true;
    }

    private boolean isEarthyBlock(BlockState block) {
        for (BlockState earthyBlock : earthyBlocks) {
            if (block.isOf(earthyBlock.getBlock())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onRemoved(AttributeContainer attributes) {
        if (world != null) {
            for (Map.Entry<BlockPos, BlockState> entry : originalBlockStates.entrySet()) {
                BlockPos pos = entry.getKey();
                BlockState originalState = entry.getValue();
                world.setBlockState(pos, originalState);
            }
        }
        originalBlockStates.clear();
        super.onRemoved(attributes);
    }
}
