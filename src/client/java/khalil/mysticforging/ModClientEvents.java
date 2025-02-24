package khalil.mysticforging;

import java.util.List;

import khalil.mysticforging.registrationhelpers.ModComponents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModClientEvents {
    public static void register() {
        ItemTooltipCallback.EVENT
                .register((ItemStack stack, TooltipContext context, TooltipType tooltipType, List<Text> lines) -> {

                    if (stack.contains(ModComponents.AIR_PATHFINDER1))
                        if (stack.get(ModComponents.AIR_PATHFINDER1))
                            lines.add(Text.literal("Wind Pathfinder").formatted(Formatting.GREEN));

                    if (stack.contains(ModComponents.EARTH_BULWARK1))
                        if (stack.get(ModComponents.EARTH_BULWARK1))
                            lines.add(Text.literal("Earth Bullwark").formatted(Formatting.DARK_RED));
                });
    }
}
