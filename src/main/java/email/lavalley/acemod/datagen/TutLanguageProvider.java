package email.lavalley.acemod.datagen;

import email.lavalley.acemod.AceModMain;
import email.lavalley.acemod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class TutLanguageProvider extends LanguageProvider {

    public TutLanguageProvider(DataGenerator gen, String locale) {
        super(gen, AceModMain.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(Registration.MYSTERIOUS_ORE_OVERWORLD.get(), "Mysterious ore");
        add(Registration.MYSTERIOUS_ORE_NETHER.get(), "Mysterious ore");
        add(Registration.MYSTERIOUS_ORE_END.get(), "Mysterious ore");
        add(Registration.MYSTERIOUS_ORE_DEEPSLATE.get(), "Mysterious ore");

        add(Registration.RAW_MYSTERIOUS_CHUNK.get(), "Mysterious Raw Chunk");
        add(Registration.MYSTERIOUS_INGOT.get(), "Mysterious Ingot");
    }
}
