package email.lavalley.acemod.item;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class TeleportItem extends Item {

    private final int range;
    private final int cooldown;

    public TeleportItem(Properties props, int teleportRange, int cooldownTicks) {
        super(props);

        range = teleportRange;
        cooldown = cooldownTicks;
    }

    public TeleportItem(Properties props, int teleportRange) {
        super(props);

        range = teleportRange;
        cooldown = 0;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (cooldown > 0) {
            player.getCooldowns().addCooldown(this, cooldown);
        }

        return super.use(world, player, hand);
    }

    protected BlockHitResult getPlayerPOVTeleportResult(Level world, Player player, ClipContext.Fluid clipCtx) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec31 = vec3.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        return world.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, clipCtx, player));
    }
    
}
