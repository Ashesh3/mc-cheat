package net.fabricmc.example;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.realmsclient.client.Request.Get;

import io.github.cottonmc.clientcommands.*;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandSource;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.world.dimension.DimensionType;

public class Commands implements ClientCommandPlugin {
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal("Initialise").executes(source -> {
            Variables.world = MinecraftClient.getInstance().world;
            Variables.LocalPlayer = MinecraftClient.getInstance().player;
            System.out.println("World: " + Variables.world);
            System.out.println("LocalPlayer: " + Variables.LocalPlayer);
            return 1;
        }));
        dispatcher.register(ArgumentBuilders.literal("client-commands").executes(source -> {
            source.getSource().sendFeedback(new LiteralText("Hello, world!"));
            return 1;
        }));
        dispatcher.register(ArgumentBuilders.literal("Anti-Aim").executes(source -> {
            Variables.AAOn = !Variables.AAOn;
            String CurrentState = (Variables.AAOn) ? "ON" : "OFF";
            if (Variables.AAOn)
                source.getSource()
                        .sendFeedback(new LiteralText(("Anti-Aim : " + CurrentState)).formatted(Formatting.RED));
            else
                source.getSource()
                        .sendFeedback(new LiteralText(("Anti-Aim : " + CurrentState)).formatted(Formatting.GREEN));
            return 1;
        }));
        dispatcher.register(ArgumentBuilders.literal("ESP").executes(source -> {
            Variables.ESPOn = !Variables.ESPOn;
            String CurrentState = (Variables.ESPOn) ? "ON" : "OFF";
            if (Variables.ESPOn) {
                source.getSource().sendFeedback(new LiteralText(("ESP : " + CurrentState)).formatted(Formatting.RED));
                ESP.On();
            } else {
                source.getSource().sendFeedback(new LiteralText(("ESP : " + CurrentState)).formatted(Formatting.GREEN));
                ESP.Off();
            }
            return 1;
        }));
        dispatcher.register(ArgumentBuilders.literal("KillAura").executes(source -> {
            Variables.KAOn = !Variables.KAOn;
            String CurrentState = (Variables.KAOn) ? "ON" : "OFF";
            if (Variables.KAOn)
                source.getSource()
                        .sendFeedback(new LiteralText(("KillAura : " + CurrentState)).formatted(Formatting.RED));
            else
                source.getSource()
                        .sendFeedback(new LiteralText(("KillAura : " + CurrentState)).formatted(Formatting.GREEN));
            return 1;
        }));
    }
}