package email.lavalley.acemod.setup;

import email.lavalley.acemod.AceModMain;
// import email.lavalley.acemod.client.GeneratorModelLoader;
// import email.lavalley.acemod.client.PowergenRenderer;
// import email.lavalley.acemod.client.PowergenScreen;
// import email.lavalley.acemod.entities.ThiefModel;
// import email.lavalley.acemod.entities.ThiefRenderer;
// import email.lavalley.acemod.manasystem.client.KeyBindings;
// import email.lavalley.acemod.manasystem.client.KeyInputHandler;
// import email.lavalley.acemod.manasystem.client.ManaOverlay;
// import net.minecraft.client.gui.screens.MenuScreens;
// import net.minecraft.client.renderer.ItemBlockRenderTypes;
// import net.minecraft.client.renderer.RenderType;
// import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
// import net.minecraftforge.client.gui.OverlayRegistry;
// import net.minecraftforge.client.model.ModelLoaderRegistry;
// import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
// import net.minecraftforge.client.gui.ForgeIngameGui;

@Mod.EventBusSubscriber(modid = AceModMain.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // MenuScreens.register(Registration.POWERGEN_CONTAINER.get(), PowergenScreen::new);
            // ItemBlockRenderTypes.setRenderLayer(Registration.POWERGEN.get(), RenderType.translucent());
            // PowergenRenderer.register();
        });
        // MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
        // KeyBindings.init();
        // OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);
    }

    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        // ModelLoaderRegistry.registerLoader(GeneratorModelLoader.GENERATOR_LOADER, new GeneratorModelLoader());
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // event.registerLayerDefinition(ThiefModel.THIEF_LAYER, ThiefModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        // event.registerEntityRenderer(Registration.THIEF.get(), ThiefRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        // if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
        //     return;
        // }
        // event.addSprite(PowergenRenderer.HALO);
    }
}
