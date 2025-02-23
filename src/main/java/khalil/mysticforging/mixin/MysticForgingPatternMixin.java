package khalil.mysticforging.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.serialization.DynamicOps;

import khalil.mysticforging.MysticSigils;

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
					if (checkPattern(world, blockpos, MysticSigils.BULLWARK_1, Blocks.NETHERRACK)) {
						resultStack = new ItemStack(Items.ACACIA_LOG);
					}

					if(checkPattern(world, blockpos, MysticSigils.PATHFINDER_2, Blocks.COPPER_BLOCK)){
						resultStack = new ItemStack(Items.COPPER_BLOCK); //temp items lol
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
					default:
						break;
				}
			}
		}

		return true; // Pattern matched
	}
}