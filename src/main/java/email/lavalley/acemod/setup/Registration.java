package email.lavalley.acemod.setup;

import email.lavalley.acemod.AceModMain;
import email.lavalley.acemod.item.AceHeadItem;
import email.lavalley.acemod.item.EllaHeadItem;
import email.lavalley.acemod.item.MegaSwordItem;
import email.lavalley.acemod.item.PocketPickaxeItem;
import email.lavalley.acemod.utils.ModCreativeTab;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AceModMain.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AceModMain.MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        // BLOCK_ENTITIES.register(bus);
        // CONTAINERS.register(bus);
        // ENTITIES.register(bus);
        // STRUCTURES.register(bus);
    }

    private static final Item.Properties MOD_ITEM_PROPERTIES = new Item.Properties().tab(ModCreativeTab.ACEMOD_TAB);
    private static final BlockBehaviour.Properties MOD_ORE_BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();

    public static final RegistryObject<Item> MEGASWORD = ITEMS.register("mega_sword", () -> new MegaSwordItem(new Item.Properties().tab(ModCreativeTab.ACEMOD_TAB)));
    public static final RegistryObject<Item> POCKETPICKAXE = ITEMS.register("pocket_pickaxe",() -> new PocketPickaxeItem(new Item.Properties().tab(ModCreativeTab.ACEMOD_TAB)));
    public static final RegistryObject<Item> ACEHEAD = ITEMS.register("acehead", () -> new AceHeadItem(new Item.Properties().tab(ModCreativeTab.ACEMOD_TAB)));
    public static final RegistryObject<Item> ELLAHEAD = ITEMS.register("ellahead", () -> new EllaHeadItem(new Item.Properties().tab(ModCreativeTab.ACEMOD_TAB)));

    public static final RegistryObject<Item> POTATO_SLICE = ITEMS.register("potato_slice", () -> new Item(new Item.Properties().tab(ModCreativeTab.ACEMOD_TAB)
                                                .food(new FoodProperties.Builder()
                                                    .nutrition(1)
                                                    .saturationMod(0.5F)
                                                    .build())));

    public static final RegistryObject<Item> DORITO = ITEMS.register("dorito", () -> new Item(new Item.Properties().tab(ModCreativeTab.ACEMOD_TAB)
                                                .food(new FoodProperties.Builder()
                                                    .alwaysEat()
                                                    .nutrition(6)
                                                    .saturationMod(14.4F)                                                    
                                                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F)
                                                    .effect(() -> new MobEffectInstance(MobEffects.HEAL, 20, 5), 1.0F)
                                                    .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 4), 1.0F)
                                                    .fast()
                                                    .build())));


    public static final RegistryObject<Block> DORITOS_BOX = BLOCKS.register("doritos_box", () -> new Block(BlockBehaviour.Properties.of(Material.SAND).strength(1f)));
    public static final RegistryObject<Item> DORITOS_BOX_ITEM = fromBlock(DORITOS_BOX);
    public static final RegistryObject<Item> DORITOS_BAG = ITEMS.register("doritos_bag", () -> new Item(MOD_ITEM_PROPERTIES));

    public static final RegistryObject<Block> MYSTERIOUS_ORE_OVERWORLD = BLOCKS.register("mysterious_ore_overworld", () -> new Block(MOD_ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MYSTERIOUS_ORE_OVERWORLD_ITEM = fromBlock(MYSTERIOUS_ORE_OVERWORLD);
    public static final RegistryObject<Block> MYSTERIOUS_ORE_NETHER = BLOCKS.register("mysterious_ore_nether", () -> new Block(MOD_ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MYSTERIOUS_ORE_NETHER_ITEM = fromBlock(MYSTERIOUS_ORE_NETHER);
    public static final RegistryObject<Block> MYSTERIOUS_ORE_END = BLOCKS.register("mysterious_ore_end", () -> new Block(MOD_ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MYSTERIOUS_ORE_END_ITEM = fromBlock(MYSTERIOUS_ORE_END);
    public static final RegistryObject<Block> MYSTERIOUS_ORE_DEEPSLATE = BLOCKS.register("mysterious_ore_deepslate", () -> new Block(MOD_ORE_BLOCK_PROPERTIES));
    public static final RegistryObject<Item> MYSTERIOUS_ORE_DEEPSLATE_ITEM = fromBlock(MYSTERIOUS_ORE_DEEPSLATE);

    public static final RegistryObject<Item> RAW_MYSTERIOUS_CHUNK = ITEMS.register("raw_mysterious_chunk", () -> new Item(MOD_ITEM_PROPERTIES));
    public static final RegistryObject<Item> MYSTERIOUS_INGOT = ITEMS.register("mysterious_ingot", () -> new Item(MOD_ITEM_PROPERTIES));

    public static final TagKey<Block> MYSTERIOUS_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(AceModMain.MODID, "mysterious_ore"));
    public static final TagKey<Item> MYSTERIOUS_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(AceModMain.MODID, "mysterious_ore"));

    // Conveniance function: Take a RegistryObject<Block> and make a corresponding RegistryObject<Item> from it
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), MOD_ITEM_PROPERTIES));
    }
}
