package net.fabricmc.example;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.World;

class Variables {
    static int ticks = 0;
    static ClientPlayerEntity LocalPlayer;
    static boolean LocalPlayerFound = false;
    static World world;
    static boolean AAOn = false;
    static boolean KAOn = false;
    static boolean ESPOn = false;
}