package khalil.mysticforging;

import java.util.List;
import java.util.TreeMap;

import khalil.mysticforging.mysticSigils.MysticSigil;
import khalil.mysticforging.registrationhelpers.ModComponents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ModClientEvents {
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    public static void register() {
        ItemTooltipCallback.EVENT
                .register((ItemStack stack, TooltipContext context, TooltipType tooltipType, List<Text> lines) -> {

                    if(stack.get(ModComponents.SIGIL) == null)return;
                    MysticSigil sigil = stack.get(ModComponents.SIGIL);

                    switch (sigil.getSchool()) {
                        case MysticSigil.School.WATER:
                            lines.add(Text.literal(sigil.getName()  + " " + toRoman(sigil.getLevel())).formatted(Formatting.BLUE));
                            break;
                        case MysticSigil.School.EARTH:
                            lines.add(Text.literal(sigil.getName()  + " " + toRoman(sigil.getLevel())).formatted(Formatting.GRAY));
                            break;
                        case MysticSigil.School.AIR:
                            lines.add(Text.literal(sigil.getName() + " " + toRoman(sigil.getLevel())).formatted(Formatting.AQUA));
                            break;
                        case MysticSigil.School.FIRE:
                            lines.add(Text.literal(sigil.getName()  + " " + toRoman(sigil.getLevel())).formatted(Formatting.RED));
                            break;
                        default:
                        break;
                    }
                });
    }


    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    private final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }
}
