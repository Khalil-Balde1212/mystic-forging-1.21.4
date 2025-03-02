package khalil.mysticforging.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.mojang.serialization.MapCodec;

import khalil.mysticforging.mysticSigils.MysticSigil;
import khalil.mysticforging.registrationhelpers.ModBlocks;


public class MysticFocusBlock extends BlockWithEntity {
    public static final MapCodec<MysticFocusBlock> CODEC = createCodec(MysticFocusBlock::new);
    private MysticSigil.School school;
    
    public MysticFocusBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
            BlockEntityType<T> type) {
        return validateTicker(type, ModBlocks.MYSTIC_FOCUS_BLOCK_ENTITY, MysticFocusBlockEntity::tick);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MysticFocusBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
}