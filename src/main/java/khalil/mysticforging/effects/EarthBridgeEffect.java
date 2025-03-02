package khalil.mysticforging.effects;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class EarthBridgeEffect extends StatusEffect {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
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

    protected EarthBridgeEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            BlockPos pos = player.getBlockPos().down();
            BlockState blockState = world.getBlockState(pos);

            if (player.isSneaking() && isEarthyBlock(blockState) && player.isOnGround()) {
                BlockPos infrontPos = pos.add(player.getHorizontalFacing().getVector());
                BlockState infrontBlockstate = world.getBlockState(infrontPos);

                if (infrontBlockstate.isOf(Blocks.AIR)) {
                    world.setBlockState(infrontPos, blockState);
                    scheduler.schedule(() -> world.setBlockState(infrontPos, Blocks.AIR.getDefaultState()), 2000,
                            TimeUnit.MILLISECONDS);
                }
            }
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
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

}
