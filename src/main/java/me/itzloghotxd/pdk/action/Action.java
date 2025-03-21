package me.itzloghotxd.pdk.action;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an action that can be executed within the plugin.
 * Each action has a unique identifier and can perform specific tasks when triggered.
 *
 * @author ItzLoghotXD
 */
public interface Action {

    /**
     * Retrieves the unique identifier for this action.
     * This identifier is used to register and execute the action.
     *
     * @return A string representing the unique identifier of the action.
     */
    String getIdentifier();

    /**
     * Executes the action for a given player.
     *
     * @param plugin The instance of the JavaPlugin.
     * @param player The player for whom the action is being executed.
     * @param data   Additional data or parameters for the action. Can be null if no data is required.
     */
    void execute(@NotNull JavaPlugin plugin, @NotNull Player player, @Nullable String data);
}
