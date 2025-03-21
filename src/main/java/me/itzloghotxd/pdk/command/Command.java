package me.itzloghotxd.pdk.command;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Represents a command in the plugin.
 * Implement this interface to define custom commands with execution logic,
 * permission checks, and tab completion.
 *
 * @author ItzLoghotXD
 */
public interface Command {

    /**
     * Executes the command with the provided sender and arguments.
     *
     * @param sender The sender who executed the command (player or console).
     * @param args   The arguments passed to the command.
     */
    void execute(CommandSender sender, String[] args);

    /**
     * Provides tab-completion suggestions for the command.
     *
     * @param sender The sender requesting tab completion.
     * @param args   The current arguments typed by the sender.
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
