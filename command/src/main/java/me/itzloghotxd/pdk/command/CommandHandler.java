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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

/**
 * Handles command execution and tab completion for a specific registered command.
 * It delegates command handling and tab completion to {@link CommandManager}.
 *
 * @author ItzLoghotXD
 * @since 0.1.0
 */
public class CommandHandler implements CommandExecutor, TabExecutor {

    private final String name;
    private final CommandManager commandManager;
    private final BaseCommandExecutor baseExecutor;

    /**
     * Constructs a new CommandHandler for a specified command.
     *
     * @param plugin The plugin instance registering this command handler.
     * @param name The name of the command being handled.
     * @param commandManager The {@link CommandManager} instance.
     * @param baseExecutor The {@link BaseCommandExecutor} implementation.
     */
    public CommandHandler(@NotNull JavaPlugin plugin, @NotNull String name, @NotNull CommandManager commandManager, @Nullable BaseCommandExecutor baseExecutor) {
        this.commandManager = commandManager;
        this.name = name;
        this.baseExecutor = baseExecutor;
        if (plugin.getCommand(name) == null) {
            plugin.getLogger().log(Level.SEVERE, "[PDK COMMAND] The command is null.");
            return;
        }
        Objects.requireNonNull(plugin.getCommand(name)).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand(name)).setTabCompleter(this);
    }

    /**
     * Handles the execution of the command. If no subcommand is provided, it informs the sender of the correct usage.
     *
     * @param sender The sender who executed the command.
     * @param command The Bukkit command object.
     * @param label The command label.
     * @param args The arguments passed to the command.
     * @return {@code true} if the command was handled successfully, {@code false} otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (baseExecutor != null) {
                return baseExecutor.execute(sender, label);
            }

            sender.sendMessage(ChatColor.WHITE + "Usage: /" + name + " <subcommand>");
            return true;
        }

        return commandManager.executeCommand(sender, args);
    }

    /**
     * Handles tab completion for the command.
     *
     * @param sender The sender requesting tab completion.
     * @param command The Bukkit command object.
     * @param alias The alias used for the command.
     * @param args The current arguments typed by the sender.
     * @return A list of possible tab-completion results.
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return commandManager.getTabCompletions(sender, args);
    }
}