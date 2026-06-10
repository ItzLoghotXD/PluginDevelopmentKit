/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "Plugin Development Kit - PDK" Library.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.pdk.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Manages command registration, execution, and tab completion.
 * This class allows subcommands to be registered dynamically.
 *
 * @author ItzLoghotXD
 * @since 0.1.0
 */
public class CommandManager {

    private final Map<String, SubCommand> subCommands = new HashMap<>();
    private final JavaPlugin plugin;

    /**
     * Constructs a new CommandManager instance.
     *
     * @param plugin The plugin which is using this manager
     */
    public CommandManager(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers one or more {@link SubCommand} to the manager.
     *
     * @param commands The {@link SubCommand} implementation(s).
     */
    public void register(@NotNull SubCommand... commands) {
        for (SubCommand command : commands) {
            String name = command.getIdentifier().toLowerCase(Locale.ROOT);
            if (subCommands.containsKey(name)) {
                throw new IllegalStateException("[PDK COMMAND] SubCommand with name: \"" + name + "\" is already registered. Please re-check.");
            }
            subCommands.put(name, command);
        }
        plugin.getLogger().info("[PDK COMMAND] Successfully registered " + commands.length + " subcommand(s). Total: " + subCommands.size());
    }

    /**
     * Executes a registered subcommand if available and checks permissions.
     *
     * @param sender The sender who executed the command.
     * @param args The arguments passed to the command.
     * @return true if a valid subcommand, otherwise false.
     */
    public boolean execute(CommandSender sender, String[] args) {
        SubCommand subCommand = subCommands.get(args[0].toLowerCase(Locale.ROOT));
        if (subCommand == null) {
            sender.sendMessage(Component.text("Unknown subcommand: " + args[0].toLowerCase(Locale.ROOT)).color(NamedTextColor.RED));
            return true;
        }

        String permission = subCommand.getPermission();
        if (permission != null && !permission.isEmpty()) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(Component.text("You do not have the Permission to perform this subcommand!").color(NamedTextColor.RED));
                return true;
            }
        }

        subCommand.execute(sender, args);
        return true;
    }

    /**
     * Retrieves tab-completion suggestions for the provided arguments.
     *
     * @param sender The sender requesting tab completion.
     * @param args The current arguments typed by the sender.
     * @return A list of possible tab-completion results. Returns an empty list if no matches are found.
     */
    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return subCommands.keySet().stream().filter(subCommand -> subCommand.startsWith(args[0].toLowerCase(Locale.ROOT))).toList();
        }

        if (args.length > 1) {
            SubCommand subCommand = subCommands.get(args[0].toLowerCase(Locale.ROOT));
            if (subCommand != null) {
                return subCommand.onTabComplete(sender, args);
            }
        }

        return List.of();
    }
}
