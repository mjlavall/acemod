package email.lavalley.acemod.item;

import email.lavalley.acemod.AceModMain;
import email.lavalley.acemod.utils.ModCreativeTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AceModMain.MOD_ID);

    public static final RegistryObject<Item> MEGASWORD = ITEMS.register("mega_sword",
        () -> new MegaSwordItem(new Item.Properties()
        .tab(ModCreativeTab.instance)
        .defaultDurability(1000),
        100,
        100F)
    );

    public static final RegistryObject<Item> POCKETPICKAXE = ITEMS.register("pocket_pickaxe",
        () -> new PocketPickaxeItem(new Item.Properties()
                .tab(ModCreativeTab.instance)
                .defaultDurability(1000),
                100,
                100F)
    );

    public static final RegistryObject<Item> ACEHEAD = ITEMS.register("acehead",
        () -> new AceHeadItem(new Item.Properties()
                .tab(ModCreativeTab.instance)
                .defaultDurability(1000),
                75,
                60,
                20)
    );

    public static final RegistryObject<Item> ELLAHEAD = ITEMS.register("ellahead",
        () -> new EllaHeadItem(new Item.Properties()
                .tab(ModCreativeTab.instance)
                .defaultDurability(1000),
                25,
                60,
                20)
    );

    public static final RegistryObject<Item> POTATO_SLICE = ITEMS.register("potato_slice",
        () -> new Item(new Item.Properties()
                .tab(ModCreativeTab.instance)                
                .food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.5F)
                    .build()))
    );

    public static final RegistryObject<Item> DORITO = ITEMS.register("dorito",
        () -> new Item(new Item.Properties()
                .tab(ModCreativeTab.instance)                
                .food(new FoodProperties.Builder()
                    .alwaysEat()
                    .nutrition(6)
                    .saturationMod(14.4F)
                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.HEAL, 20, 5), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200, 4), 1.0F)
                    .fast()
                    .build()))
    );

    public static final RegistryObject<Item> DORITOS_BAG = ITEMS.register("doritos_bag",
        () -> new Item(new Item.Properties()
                .tab(ModCreativeTab.instance))
    );
}

