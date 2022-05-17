package email.lavalley.acemod.item;

import java.util.Arrays;
import java.util.Collection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class AceHeadItem extends TeleportItem {

    private final int slowFall;
    private final Collection<Material> invalidMaterials;

    public AceHeadItem(Properties props, int range, int slowFallTicks, int cooldownTicks) {
        super(props, range, cooldownTicks);

        slowFall = slowFallTicks;
        invalidMaterials = Arrays.asList(
            Material.WATER,
            Material.LAVA
        );
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getDamageValue() < stack.getMaxDamage()) {
            BlockHitResult ray = getPlayerPOVTeleportResult(world, player, ClipContext.Fluid.ANY);
            Direction direction = ray.getDirection();
            BlockPos telePos = ray.getBlockPos().relative(ray.getDirection());
            if (direction == Direction.EAST) {
                telePos = new BlockPos(telePos.getX() + 1, telePos.getY(), telePos.getZ());
            }
            if (direction == Direction.SOUTH) {
                telePos = new BlockPos(telePos.getX(), telePos.getY(), telePos.getZ() + 1);
            }
            BlockPos blockPos = ray.getBlockPos();
            BlockState blockstate = world.getBlockState(blockPos);            
            if (invalidMaterials.contains(blockstate.getMaterial()) || direction == Direction.DOWN){
                return InteractionResultHolder.pass(stack);
            }

            player.teleportTo(telePos.getX(), telePos.getY(), telePos.getZ());
            player.fallDistance = 0F;
            player.flyDist = 0F;
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
            if (world.getBlockState(telePos.below()).isAir()) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, slowFall, 0), player.getItemInHand(hand).getEntityRepresentation());
            }
            stack.setDamageValue(stack.getDamageValue() + 1);
        }
        
        return super.use(world, player, hand);
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack tool, ItemStack material) {
        return material.getItem() == ItemInit.DORITO.get();
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        return false;
     }
}
