package email.lavalley.acemod.utils;

import email.lavalley.acemod.AceModMain;
import email.lavalley.acemod.setup.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab extends CreativeModeTab {
    public static final ModCreativeTab ACEMOD_TAB = new ModCreativeTab(CreativeModeTab.TABS.length, AceModMain.MODID);

    private ModCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Registration.ACEHEAD.get());
    }
}