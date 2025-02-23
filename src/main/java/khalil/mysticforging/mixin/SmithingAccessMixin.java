package khalil.mysticforging.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.screen.SmithingScreenHandler;

@Mixin(SmithingScreenHandler.class)
public interface SmithingAccessMixin {

    @Invoker("createRecipeInput")
    SmithingRecipeInput invokeCreateInput();
}
