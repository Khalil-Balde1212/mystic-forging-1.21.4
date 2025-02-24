package khalil.mysticforging.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import khalil.mysticforging.registrationhelpers.ModComponents;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Mixin(PlayerEntity.class)
public class TestPlayerEffectMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    public void checkArmorForSpeedEffect(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (hasFullArmorWithDataComponent(player, ModComponents.AIR_PATHFINDER1)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1, false, false, true));
        } else if(hasFullArmorWithDataComponent(player, ModComponents.EARTH_BULWARK1)){
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 2, false, false, true));
        }
    }

    private boolean hasFullArmorWithDataComponent(PlayerEntity player, ComponentType<Boolean> component) {
        for (ItemStack armorPiece : player.getArmorItems()) {
            if (!hasDataComponentTrue(armorPiece, component)) {
                return false;
            }
        }
        return true; // All armor pieces have the data component set to true
    }

    private boolean hasDataComponentTrue(ItemStack itemStack, ComponentType<Boolean> component) {
        if(itemStack.contains(component)) 
            return itemStack.get(component);
        return false;
    }
}
