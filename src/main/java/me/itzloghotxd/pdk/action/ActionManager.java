package me.itzloghotxd.pdk.action;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Manages the registration and execution of actions within the plugin.
 * This class allows actions to be registered and later executed based on input commands.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings("unused")
public class ActionManager {

    private final JavaPlugin plugin;
    private final Map<String, Action> actions;

    /**
     * Constructs a new ActionManager for handling and executing actions.
     *
     * @param plugin The instance of the JavaPlugin using this manager.
     */
    public ActionManager(JavaPlugin plugin) {
        this.plugin = plugin;
        actions = new HashMap<>();
    }

    /**
     * Registers one or more actions to the manager.
     *
     * @param actions One or more {@link Action} instances to be registered.
     */
    public void registerAction(@NotNull Action... actions) {
        for (Action action : actions) {
            this.actions.put(action.getIdentifier().toUpperCase(), action);
        }
        Bukkit.getLogger().log(Level.INFO, "Successfully registered " + this.actions.size() + " Action(s)!");
    }

    /**
     * Executes a list of actions for a given player.
     * Each action string should be formatted as:
     * {@code [ACTION_NAME] optional_data}.
     * The {@code ACTION_NAME} must be enclosed in square brackets,
     * followed by an optional space-separated parameter.
     * <p>
     * Logs a warning if an invalid or unknown action is encountered.
     *
     * @param player  The player executing the actions.
     * @param actions A list of action strings to be executed.
     */
    public void executeActions(@NotNull Player player, List<String> actions) {
        for (String actionString : actions) {
            if (!actionString.startsWith("[") || !actionString.contains("]")) {
                plugin.getLogger().log(Level.WARNING, "Invalid action format: '" + actionString + "'");
                continue;
            }

            String actionName = actionString.substring(1, actionString.indexOf("]"));
            Action action = this.actions.get(actionName.toUpperCase());

            if (action != null) {
                String[] parts = actionString.split(" ", 2);
                String data = (parts.length > 1) ? parts[1] : "";

                action.execute(plugin, player, data);
            } else {
                plugin.getLogger().log(Level.WARNING, "Unknown action: '" + actionString + "'");
            }
        }
    }
}
