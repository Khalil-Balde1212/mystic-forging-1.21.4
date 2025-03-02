package khalil.mysticforging.effects;

import net.minecraft.entity.effect.StatusEffect;

import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class AirPathfinderEffect extends StatusEffect {

    public AirPathfinderEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            if (player.isSprinting()) {
                player.getAttributes()
                        .getCustomInstance(EntityAttributes.MOVEMENT_SPEED)
                        .setBaseValue(0.1 * (amplifier + 1));
            } else {
                player.getAttributes()
                        .getCustomInstance(EntityAttributes.MOVEMENT_SPEED)
                        .setBaseValue(0.1);
            }
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);
        attributeContainer.getCustomInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.1);
    }
}
