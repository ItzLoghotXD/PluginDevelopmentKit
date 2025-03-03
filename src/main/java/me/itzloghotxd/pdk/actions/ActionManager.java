package me.itzloghotxd.pdk.actions;

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
    private final Map<String, Action> actions = new HashMap<>();

    /**
     * Constructs a new ActionManager for handling and executing actions.
     *
     * @param plugin The instance of the JavaPlugin using this manager.
     */
    public ActionManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers one or more actions to the manager.
     *
     * @param actions The {@link Action}(s) to be registered.
     */
    public void registerAction(@NotNull Action... actions) {
        for (Action action : actions) {
            this.actions.put(action.getIdentifier().toUpperCase(), action);
        }
    }

    /**
     * Executes a list of actions for a given player.
     * Each action string should be formatted as "[ACTION_NAME] optional_data".
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
