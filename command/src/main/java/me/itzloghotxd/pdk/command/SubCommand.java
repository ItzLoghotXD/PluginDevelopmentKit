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

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Represents a sub-command in the plugin.
 * Implement this interface to define custom sub-commands with execution logic,
 * permission checks, and tab completion.
 *
 * @author ItzLoghotXD
 * @since 0.1.0
 */
public interface SubCommand {

    /**
     * Retrieves the unique identifier for this {@code Command}.
     * This identifier is used to register and execute the {@code Command}.
     *
     * @return A string representing the unique identifier of the command.
     */
    @NotNull
    String getIdentifier();

    /**
     * Executes the command with the provided sender and arguments.
     *
     * @param sender The sender who executed the command (player or console).
     * @param args The arguments passed to the command.
     */
    void execute(CommandSender sender, String[] args);

    /**
     * Provides tab-completion suggestions for the command.
     *
     * @param sender The sender requesting tab completion.
     * @param args The current arguments typed by the sender.
     * @return A list of possible tab-completion results.
     */
    List<String> onTabComplete(CommandSender sender, String[] args);

    /**
     * Retrieves the permission required to execute the command.
     *
     * @return The permission string required for the command. Returns an empty string if no permission is required.
     */
    String getPermission();
}