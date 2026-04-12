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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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
            subCommands.put(command.getIdentifier().toLowerCase(), command);
        }
        plugin.getLogger().log(Level.INFO, "[PDK COMMAND] Successfully registered " + commands.length + " subcommand(s). Total: " + subCommands.size());
    }

    /**
     * Executes a registered subcommand if available and checks permissions.
     *
     * @param sender The sender who executed the command.
     * @param args The arguments passed to the command.
     * @return {@code true} if the command was handled successfully, {@code false} otherwise.
     */
    protected boolean executeCommand(CommandSender sender, String[] args) {
        SubCommand subCommand = subCommands.get(args[0].toLowerCase());
        if (subCommand == null) {
            sender.sendMessage(ChatColor.RED + "Unknown command: " + args[0].toLowerCase());
            return true;
        }

        String permission = subCommand.getPermission();
        if (permission != null && !permission.isEmpty()) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(ChatColor.RED + "You do not have the Permission to perform this Command!");
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
    protected List<String> getTabCompletions(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return subCommands.keySet().stream().filter(subCommand -> subCommand.startsWith(args[0].toLowerCase())).toList();
        }

        if (args.length > 1) {
            SubCommand subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                return subCommand.onTabComplete(sender, args);
            }
        }

        return List.of();
    }
}