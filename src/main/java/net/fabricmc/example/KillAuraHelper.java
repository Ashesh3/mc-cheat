package net.fabricmc.example;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;

public class KillAuraHelper {

    public static float wrapAngleTo180_float(float p_76142_0_) {
        p_76142_0_ %= 360.0F;

        if (p_76142_0_ >= 180.0F) {
            p_76142_0_ -= 360.0F;
        }

        if (p_76142_0_ < -180.0F) {
            p_76142_0_ += 360.0F;
        }

        return p_76142_0_;
    }

    public static synchronized void faceEntity(OtherClientPlayerEntity entity, ClientPlayerEntity thePlayer) {
        final float[] rotations = getRotationsNeeded(entity, thePlayer);

        if (rotations != null) {
            thePlayer.yaw = rotations[0];
            thePlayer.pitch = rotations[1] + 1.0F;
        }
    }

    public static float[] getRotationsNeeded(OtherClientPlayerEntity entity, ClientPlayerEntity thePlayer) {
        if (entity == null) {
            return null;
        }

        final double diffX = entity.getX() - thePlayer.getX();
        final double diffZ = entity.getZ() - thePlayer.getZ();
        double diffY;

        if (entity instanceof OtherClientPlayerEntity) {
            final OtherClientPlayerEntity entityLivingBase = (OtherClientPlayerEntity) entity;
            diffY = entityLivingBase.getY() + entityLivingBase.getEyeY() - (thePlayer.getY() + thePlayer.getEyeY());

            final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
            final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
            final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
            return new float[] { thePlayer.yaw + wrapAngleTo180_float(yaw - thePlayer.yaw),
                    thePlayer.pitch + wrapAngleTo180_float(pitch - thePlayer.pitch) };
        }
        return new float[] { 0, 0 };
    }
}