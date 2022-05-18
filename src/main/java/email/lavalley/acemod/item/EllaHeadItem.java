package email.lavalley.acemod.item;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import email.lavalley.acemod.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class EllaHeadItem extends TeleportItem {

    private final int freeze = 60;
    private final Map<Material, Block> freezeMap;
    private final Collection<Material> snowMats;
    private final Block defaultFrozenBlock = Blocks.SNOW_BLOCK;

    public EllaHeadItem(Properties props) {
        super(props.defaultDurability(1000), 25, 20);

        snowMats = Arrays.asList(
            Material.SNOW,
            Material.TOP_SNOW,
            Material.POWDER_SNOW
        );

        freezeMap = new HashMap<Material, Block>();
        freezeMap.put(Material.WATER, Blocks.FROSTED_ICE);
        freezeMap.put(Material.LAVA, Blocks.OBSIDIAN);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getDamageValue() < stack.getMaxDamage()) {
            BlockHitResult ray = getPlayerPOVTeleportResult(world, player, ClipContext.Fluid.ANY);     
            BlockPos blockPos = ray.getBlockPos();
            BlockState blockState = world.getBlockState(blockPos);
            
            if (blockState.isAir()) {
                return InteractionResultHolder.pass(stack);
            }

            if (blockState.getMaterial() == Material.WATER) {
                freezeWater(world, blockPos);
            } 
            else if (blockState.getMaterial() == Material.WOOD || blockState.getMaterial() == Material.LEAVES) {
                freezeTree(world, blockPos);
            }
            else {
                freezeGround(world, blockPos);
            }
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_HURT_FREEZE, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.setTicksFrozen(player.getTicksFrozen() + freeze);
            stack.setDamageValue(stack.getDamageValue() + 1);         
        }

        return super.use(world, player, hand);
    }

    private void freezeTree(Level world, BlockPos blockPos) {
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getMaterial() == Material.LEAVES) {
            BlockPos checkPos = blockPos;
            BlockState checkState = world.getBlockState(checkPos);
            while(checkState.getMaterial() == Material.LEAVES) {
                checkPos = checkPos.above();
                checkState = world.getBlockState(checkPos);
            }
            snowLeaves(world, checkPos);
        }
        
        if (blockState.getMaterial() == Material.WOOD) {
            BlockPos checkPos = blockPos;
            BlockState checkState = world.getBlockState(checkPos);
            while(checkState.getMaterial() == Material.WOOD) {
                checkPos = checkPos.above();
                checkState = world.getBlockState(checkPos);
            }
            snowLeaves(world, checkPos);
        }
    }

    private void snowLeaves(Level world, BlockPos blockPos) {
        for(int i=-5; i<=5; i++) {
            for(int j=-5; j<=5; j++) {
                for(int k=-3; k<=3; k++) {
                    BlockPos leafPos = new BlockPos(blockPos.getX() + i, blockPos.getY() + k, blockPos.getZ() + j);
                    BlockState leafState = world.getBlockState(leafPos);
                    if (leafState.getMaterial() == Material.LEAVES && world.getBlockState(leafPos.above()).isAir()) {
                        snowBlock(world, leafPos);
                    }
                }
            }
        }
    }

    private void freezeGround(Level world, BlockPos blockPos) {
        if (world.getBlockState(blockPos).is(Blocks.SNOW)) {
            blockPos = blockPos.below();
        }
        for(int i=-3; i<=3; i++) {
            for(int j=-3; j<=3; j++) {
                for(int k=-3; k<=3; k++) {
                    if (Mth.abs(i) + Mth.abs(j) > 3 || Mth.abs(i) + Mth.abs(k) > 3 || Mth.abs(j) + Mth.abs(k) > 3) continue;
                    BlockPos freezePos = new BlockPos(blockPos.getX() + i, blockPos.getY() + j, blockPos.getZ() + k);
                    freezeBlock(world, freezePos);
                }
            }
        }           
    }

    private void freezeWater(Level world, BlockPos blockPos) {
        for(int i=-3; i<=3; i++) {
            for(int j=-3; j<=3; j++) {
                if (Mth.abs(i) + Mth.abs(j) > 3) continue;
                BlockPos freezePos = new BlockPos(blockPos.getX() + i, blockPos.getY(), blockPos.getZ() + j);
                freezeBlock(world, freezePos);
            }
        }           
    }

    private void freezeBlock(Level world, BlockPos blockPos) {
        BlockState checkState = world.getBlockState(blockPos);
        Material material = checkState.getMaterial();
        if(checkState.isAir() || checkState.is(Blocks.SNOW)) return;
        if (!freezeMap.keySet().contains(material)) {
            snowBlock(world, blockPos);
            return;
        }
        Block replacement = defaultFrozenBlock;
        if (freezeMap.keySet().contains(material)) {
            replacement = freezeMap.get(material);
        }
        world.setBlockAndUpdate(blockPos, replacement.defaultBlockState());
    }

    private void snowBlock(Level world, BlockPos blockPos) {
        BlockState checkState = world.getBlockState(blockPos);
        BlockState above = world.getBlockState(blockPos.above());
        if (above.is(Blocks.SNOW)) {
            int layers = Math.min(8, above.getValue(BlockStateProperties.LAYERS) + 1);
            BlockState updated = above.setValue(BlockStateProperties.LAYERS, layers);
            world.setBlockAndUpdate(blockPos.above(), updated);
            return;
        }

        if(checkState.isAir() || snowMats.contains(checkState.getMaterial())) return;
        if(above.getMaterial() == Material.REPLACEABLE_PLANT) {
            world.destroyBlock(blockPos.above(), true);
            world.setBlockAndUpdate(blockPos.above(), Blocks.SNOW.defaultBlockState());
        }

        if(!above.isAir() || checkState.isAir() || snowMats.contains(checkState.getMaterial())) return;
        world.setBlockAndUpdate(blockPos.above(), Blocks.SNOW.defaultBlockState());
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack tool, ItemStack material) {
        return material.getItem() == Registration.DORITO.get();
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        return false;
    }
}
