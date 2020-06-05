package net.fabricmc.example;

import java.util.List;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.OtherClientPlayerEntity;

public class ESP {
    public static void On() {
        Iterable<Entity> worldEntities = ((ClientWorld) Variables.world).getEntities();
        for (Entity e : worldEntities) {
            if (!(e instanceof OtherClientPlayerEntity))
                e.setGlowing(true);
        }
    }

    public static void Off() {
        Iterable<Entity> worldEntities = ((ClientWorld) Variables.world).getEntities();
        for (Entity e : worldEntities) {
            if (!(e instanceof OtherClientPlayerEntity))
                e.setGlowing(false);
        }
    }
}