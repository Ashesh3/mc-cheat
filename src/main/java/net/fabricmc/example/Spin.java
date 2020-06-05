package net.fabricmc.example;

import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Spin extends Item {
    static int y = 0;
    static int sign = 1;
    static World world;

    // inside tick event
    public Spin(Settings item$Settings1) {
        super(item$Settings1);
    }

    public static void AA(ClientPlayerEntity player) {
        if (Variables.AAOn) {
            if (Variables.ticks > 1) {
                player.yaw = y;
                y = (y >= 360) ? 0 : y;
                player.pitch = (player.pitch == 89) ? -89 : 89;
                Variables.ticks = 0;
                y += 100;

            }
        }
    }

}