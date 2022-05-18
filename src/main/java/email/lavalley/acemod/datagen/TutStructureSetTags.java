package email.lavalley.acemod.datagen;

import email.lavalley.acemod.AceModMain;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutStructureSetTags extends TagsProvider<StructureSet> {

    public TutStructureSetTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.STRUCTURE_SETS, AceModMain.MODID, helper);
    }

    @Override
    protected void addTags() {
        
    }

    @Override
    public String getName() {
        return "AceMod Tags";
    }
}
