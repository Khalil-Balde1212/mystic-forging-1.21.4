package khalil.mysticforging.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import khalil.mysticforging.registrationhelpers.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Mixin(PlayerEntity.class)
public class TestPlayerEffectMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    public void checkArmorForSpeedEffect(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        for (ItemStack armorPiece : player.getArmorItems()) {
            if (armorPiece.get(ModComponents.SIGIL) != null) {
                armorPiece.get(ModComponents.SIGIL).applyEffect(player);
            }
        }
    }
}
