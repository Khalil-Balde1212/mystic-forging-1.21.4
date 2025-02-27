package khalil.mysticforging.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import khalil.mysticforging.mysticSigils.MysticSigil;
import khalil.mysticforging.mysticSigils.MysticSigils;
import khalil.mysticforging.registrationhelpers.ModComponents;

@Mixin(SmithingScreenHandler.class)
public class MysticForgingPatternMixin<V> {
	ScreenHandlerContext context;
	SmithingScreenHandler handler;

	@Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At("RETURN"))
	private void onConstructor(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context,
			CallbackInfo ci) {
		this.context = context;
		handler = (SmithingScreenHandler) (Object) this;
	}

	@Inject(method = "updateResult", at = @At("TAIL"), cancellable = true)
	public void mysticNetherack(CallbackInfo ci) {
		context.run((world, blockpos) -> {
			CraftingResultInventory output = ((ForgingAccessMixin) handler).getOutput();
			SmithingRecipeInput smithingRecipeInput = ((SmithingAccessMixin) handler).invokeCreateInput();

			// Check if we are in a server world
			if (world instanceof ServerWorld serverWorld) {

				// getRecipes
				Stream<RecipeEntry<SmithingRecipe>> recipeStream = serverWorld.getRecipeManager()
						.getAllMatches(RecipeType.SMITHING, smithingRecipeInput, serverWorld);
				Optional<SmithingRecipe> firstMatch = recipeStream.findFirst().map(RecipeEntry::value);


				if (firstMatch.isPresent()) {
					//get result stack
					ItemStack resultStack = firstMatch.get().craft(smithingRecipeInput,
							serverWorld.getRegistryManager());
					

					//mystic outputs
					if (checkPattern(world, blockpos, MysticSigil.Patterns.BULLWARK_1.pattern(), Blocks.COBBLESTONE)) {
						resultStack.set(ModComponents.SIGIL, MysticSigils.EARTH_BULWARK);
					}
					
					if (checkPattern(world, blockpos, MysticSigil.Patterns.PATHFINDER_1.pattern(), Blocks.COBBLESTONE)) {
						resultStack.set(ModComponents.SIGIL, MysticSigils.EARTH_PATHFINDER);
					}

					if(checkPattern(world, blockpos, MysticSigil.Patterns.PATHFINDER_1.pattern(), Blocks.WHITE_WOOL)){
						resultStack.set(ModComponents.SIGIL, MysticSigils.AIR_PATHFINDER);
					}
					
					if(checkPattern(world, blockpos, MysticSigil.Patterns.PATHFINDER_2.pattern(), Blocks.WHITE_WOOL)){
						resultStack.set(ModComponents.SIGIL, MysticSigils.AIR_PATHFINDER.setLevel(2));
					}
					
					output.setStack(0, resultStack);
					ci.cancel();
				}
			}
		});
	}

	private boolean checkPattern(World world, BlockPos centerPos, String[] pattern, Block requiredBlock) {
		int patternSize = pattern.length;
		int offset = patternSize / 2; // Center the pattern around the smithing table

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
						if(actualBlock == requiredBlock){
							return false;
						}
					default:
						break;
				}
			}
		}

		return true; // Pattern matched
	}
}