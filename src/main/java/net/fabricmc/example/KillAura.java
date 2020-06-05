package net.fabricmc.example;

import java.util.List;

import javax.swing.Box;

import com.google.common.base.Predicates;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.hit.HitResult;

public class KillAura {
    static int delay = 0;

    public static void KA(ClientPlayerEntity player, World theWorld, MinecraftClient client) {
        List list = theWorld.getPlayers();
        Iterable<Entity> worldEntities = ((ClientWorld) theWorld).getEntities();
        if (list.size() <= 1) {
            return;
        }
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k) instanceof ClientPlayerEntity)
                continue;
            if (((OtherClientPlayerEntity) list.get(k)).getName() == player.getName()) {
                continue;
            }

            OtherClientPlayerEntity entityplayer = (OtherClientPlayerEntity) list.get(1);

            if (player.distanceTo(entityplayer) > player.distanceTo(((OtherClientPlayerEntity) list.get(k)))) {
                entityplayer = (OtherClientPlayerEntity) list.get(k);
            }
            float f = player.distanceTo(entityplayer);
            for (Entity e : worldEntities) {
                if (e instanceof ClientPlayerEntity)
                    continue;
                if (player.distanceTo(e) < 4F && player.canSee(e)) {
                    if (client.player != null && client.player.getAttackCooldownProgress(0.0f) >= 1.0f
                    // && client.crosshairTarget != null
                    // && client.crosshairTarget.getType() == HitResult.Type.ENTITY
                    ) {
                        if (e.isAlive() && e.isAttackable()) {
                            client.interactionManager.attackEntity(client.player, e);
                            player.sendCommandFeedback();
                        }
                    }
                    delay = 0;
                }
                // KillAuraHelper.faceEntity(entityplayer, player);

                if (f < 4F && player.canSee(entityplayer)) {
                    if (client.player != null && client.player.getAttackCooldownProgress(0.0f) >= 1.0f
                    // && client.crosshairTarget != null
                    // && client.crosshairTarget.getType() == HitResult.Type.ENTITY
                    ) {
                        if (entityplayer.isAlive() && entityplayer.isAttackable()) {
                            client.interactionManager.attackEntity(client.player, entityplayer);
                            player.sendCommandFeedback();
                        }
                    }
                    delay = 0;
                    continue;
                }

            }
        }

    }
}