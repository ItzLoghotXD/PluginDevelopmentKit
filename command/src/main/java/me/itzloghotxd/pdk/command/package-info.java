/**
 * Provides a command handling system for the plugin.
 *
 * <p>This package contains interfaces and classes for defining, registering,
 * and executing commands within a Bukkit plugin. It includes:</p>
 *
 * <ul>
 *     <li>{@link me.itzloghotxd.pdk.command.SubCommand} - Interface for defining custom commands.</li>
 *     <li>{@link me.itzloghotxd.pdk.command.CommandHandler} - Handles command registration, execution, and tab completion.</li>
 *     <li>{@link me.itzloghotxd.pdk.command.CommandManager} - Manages subcommand registration and execution.</li>
 *     <li>{@link me.itzloghotxd.pdk.command.BaseCommandExecutor} - Functional interface for handling base command execution.</li>
 * </ul>
 *
 * <p>Commands are registered dynamically and support permission-based execution
 * as well as tab completion. The {@link me.itzloghotxd.pdk.command.CommandManager}
 * enables structured subcommand handling, while the
 * {@link me.itzloghotxd.pdk.command.CommandHandler} integrates with Bukkit's command system.</p>
 *
 * @author ItzLoghotXD
 * @since 0.1.0
 */
package me.itzloghotxd.pdk.command;