package net.fabricmc.example;

import java.awt.AWTException;
import java.util.Collection;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.util.Identifier;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
		ClientTickCallback.EVENT.register((MinecraftClient client) -> {
			Variables.ticks++;
			if (true) {
				if (Variables.AAOn)
					Spin.AA(Variables.LocalPlayer);
				if (Variables.KAOn)
					KillAura.KA(Variables.LocalPlayer, Variables.world, client);
			}
		});
	}
}
