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

/**
 * Represents a functional interface for handling the execution
 * of a base command (i.e. when no arguments are provided).
 *
 * @author ItzLoghotXD
 * @since 0.1.0
 */
@FunctionalInterface
public interface BaseCommandExecutor {

    /**
     * Executes the base command logic.
     *
     * @param sender The {@link CommandSender} who executed the command.
     * @param label The alias of the command used.
     * @return {@code true} if the command was handled successfully, {@code false} otherwise.
     */
    boolean execute(CommandSender sender, String label);
}