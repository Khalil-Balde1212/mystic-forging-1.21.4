package khalil.mysticforging.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class EarthPathfinderEffect extends StatusEffect {
    int pillarHeight = 0;
    boolean wasSneaking = false;

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

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public EarthPathfinderEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {

        pillarHeight = amplifier;
        if (entity instanceof PlayerEntity player) {
            BlockPos pos = player.getBlockPos().down();
            BlockState blockState = world.getBlockState(pos);

            // Check if the ground beneath the player is one of the earthy blocks
            if (player.isOnGround() && isEarthyBlock(blockState)) {
                if (!wasSneaking && player.isSneaking()) {
                    launchPlayer(player, amplifier);
                    // player.setPosition(player.getX(), player.getY() + 1, player.getZ());
                    scheduler.schedule(() -> createEarthPillar(world, pos, blockState), 100, TimeUnit.MILLISECONDS);
                }
                wasSneaking = player.isSneaking();
            }
        }
        preventFallDamage(entity);
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    private void createEarthPillar(ServerWorld world, BlockPos pos, BlockState pillarBlock) {
        for (int i = 0; i <= pillarHeight; i++) {
            BlockPos pillarPos = pos.up(i + 1);
            scheduler.schedule(() -> world.setBlockState(pillarPos, pillarBlock), 50 * i, TimeUnit.MILLISECONDS);
        }
        scheduler.schedule(() -> removeEarthPillar(world, pos), 50 * pillarHeight + 500, TimeUnit.MILLISECONDS);
    }

    private void removeEarthPillar(ServerWorld world, BlockPos pos) {
        for (int i = 0; i <= pillarHeight; i++) {
            BlockPos pillarPos = pos.up(i + 1);
            scheduler.schedule(() -> world.setBlockState(pillarPos, Blocks.AIR.getDefaultState()),
                    50 * pillarHeight - (50 * i), TimeUnit.MILLISECONDS);
        }
    }

    private void launchPlayer(PlayerEntity player, int amplifier) {
        double yaw = player.getYaw();
        double x = -Math.sin(Math.toRadians(yaw));
        double z = Math.cos(Math.toRadians(yaw));
        double launchSpeed = 1 + (amplifier * 0.5);

        player.setVelocity(new Vec3d(x, 1, z).normalize().multiply(launchSpeed));
        player.velocityModified = true;
    }

    private boolean isEarthyBlock(BlockState block) {
        for (BlockState earthyBlock : earthyBlocks) {
            if (block.isOf(earthyBlock.getBlock())) {
                return true;
            }
        }
        return false;
    }

    private void preventFallDamage(LivingEntity entity) {
        if (entity.fallDistance > 1 && !entity.isOnGround()) {
            BlockPos pos = entity.getBlockPos().down(2);
            BlockState blockState = entity.getWorld().getBlockState(pos);
            if (isEarthyBlock(blockState)) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 5, 0, false, false, false));
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
