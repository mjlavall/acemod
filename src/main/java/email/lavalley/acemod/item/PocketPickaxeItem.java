package email.lavalley.acemod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class PocketPickaxeItem extends PickaxeItem {

    public PocketPickaxeItem(Properties props, int damage, float speed) {
        super(Tiers.NETHERITE, damage, speed, props);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        return super.use(world, player, hand);
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack a, ItemStack b) {
        return b.is(ItemInit.DORITO.get());
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        return false;
     }    
}
