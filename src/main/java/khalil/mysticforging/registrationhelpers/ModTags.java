package khalil.mysticforging.registrationhelpers;

import khalil.mysticforging.MysticForging;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items{
        public static final TagKey<Item> MYSTIC_LEATHER_ARMOR = TagKey.of(RegistryKeys.ITEM, Identifier.of(MysticForging.MOD_ID, "mystic_leather_armor"));
        
    }
}
