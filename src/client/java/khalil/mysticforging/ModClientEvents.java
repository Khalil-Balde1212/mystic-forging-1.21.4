package khalil.mysticforging;

import java.util.List;

import khalil.mysticforging.mysticSigils.MysticSigil;
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

                    if(stack.get(ModComponents.SIGIL) == null)return;
                    MysticSigil sigil = stack.get(ModComponents.SIGIL);

                    switch (sigil.getSchool()) {
                        case MysticSigil.School.WATER:
                            lines.add(Text.literal(sigil.getName()).formatted(Formatting.BLUE));
                            break;
                        case MysticSigil.School.EARTH:
                            lines.add(Text.literal(sigil.getName()).formatted(Formatting.GRAY));
                            break;
                        case MysticSigil.School.AIR:
                            lines.add(Text.literal(sigil.getName()).formatted(Formatting.AQUA));
                            break;
                        case MysticSigil.School.FIRE:
                            lines.add(Text.literal(sigil.getName()).formatted(Formatting.RED));
                            break;
                        default:
                        break;
                    }
                });
    }
}
