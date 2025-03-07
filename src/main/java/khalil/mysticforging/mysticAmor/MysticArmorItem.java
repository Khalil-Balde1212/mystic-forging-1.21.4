package khalil.mysticforging.mysticAmor;

import khalil.mysticforging.registrationhelpers.ModEffects;
import khalil.mysticforging.registrationhelpers.ModTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.world.World;

public class MysticArmorItem extends ArmorItem {

    public MysticArmorItem(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()) {
            return;
        } 

        if (entity instanceof PlayerEntity player) {
            boolean hasFullSet = true;
            for (ItemStack armorStack : player.getInventory().armor) {
                if (!armorStack.isIn(ModTags.Items.MYSTIC_LEATHER_ARMOR)) {
                    hasFullSet = false;
                    break;
                }
            }

            if (hasFullSet) {
                player.addStatusEffect(new StatusEffectInstance(ModEffects.MYSTIC_LEATHER_BASE_ARMOR_EFFECT, 0, 0, false, false, false));
            } 
        }
    }
}
