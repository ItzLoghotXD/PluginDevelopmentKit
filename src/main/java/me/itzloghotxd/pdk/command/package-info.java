/**
 * Provides a command handling system for the plugin.
 *
 * <p>This package contains interfaces and classes for defining, registering,
 * and executing commands within a Bukkit plugin. It includes:</p>
 *
 * <ul>
 *     <li>{@link me.itzloghotxd.pdk.command.Command} - Interface for defining custom commands.</li>
 *     <li>{@link me.itzloghotxd.pdk.command.CommandHandler} - Handles command registration, execution and tab completion of commands.</li>
 *     <li>{@link me.itzloghotxd.pdk.command.CommandManager} - Manages sub command registration and execution.</li>
 * </ul>
 *
 * <p>Commands are registered dynamically and support permission-based execution
 * as well as tab completion. The {@link me.itzloghotxd.pdk.command.CommandManager} enables handling of subcommands
 * with ease, while the {@link me.itzloghotxd.pdk.command.CommandHandler} integrates with Bukkit's command system.</p>
 *
 * Author: ItzLoghotXD
 */
package me.itzloghotxd.pdk.command;
