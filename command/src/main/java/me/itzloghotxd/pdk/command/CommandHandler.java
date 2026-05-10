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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Handles command execution for a specific registered command.
 *
 * @author ItzLoghotXD
 * @since 0.1.0
 */
public class CommandHandler implements CommandExecutor {

    private final BaseCommandExecutor baseExecutor;

    /**
     * Constructs a new CommandHandler for a specified command.
     *
     * @param plugin The plugin instance registering this command handler.
     * @param name The name of the command being handled.
     * @param baseExecutor The {@link BaseCommandExecutor} implementation.
     */
    public CommandHandler(@NotNull JavaPlugin plugin, @NotNull String name, @NotNull BaseCommandExecutor baseExecutor) {
        this.baseExecutor = baseExecutor;
        if (plugin.getCommand(name) == null) {
            plugin.getLogger().severe("[PDK COMMAND] The command is null.");
            return;
        }
        Objects.requireNonNull(plugin.getCommand(name)).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return baseExecutor.execute(sender, command, label, args);
    }
}