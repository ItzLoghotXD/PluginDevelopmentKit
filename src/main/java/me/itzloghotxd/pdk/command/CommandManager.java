package me.itzloghotxd.pdk.command;

import me.itzloghotxd.pdk.utilities.chat.ChatUtilities;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages command registration, execution, and tab completion.
 * This class allows subcommands to be registered dynamically.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings("unused")
public class CommandManager {

    private final Map<String, Command> subCommands = new HashMap<>();

    /**
     * Constructs a new CommandManager instance.
     */
    public CommandManager() {
    }

    /**
     * Registers a new subcommand.
     *
     * @param name    The name of the subcommand.
     * @param command The {@link Command} implementation.
     */
    public void registerCommand(@NotNull String name, @NotNull Command command) {
        subCommands.put(name.toLowerCase(), command);
    }

    /**
     * Executes a registered subcommand.
     *
     * @param sender The sender who executed the command.
     * @param args   The arguments passed to the command.
     * @return true if the command was executed successfully, false otherwise.
     */
    public boolean executeCommand(CommandSender sender, String[] args) {
        Command subCommand = subCommands.get(args[0].toLowerCase());
        if (subCommand == null) {
            ChatUtilities.error(sender, "Unknown command: " + args[0].toLowerCase());
            return true;
        }

        if (!sender.hasPermission(subCommand.getPermission())) {
            ChatUtilities.unauthorized(sender);
            return true;
        }

        subCommand.execute(sender, args);
        return true;
    }

    /**
     * Retrieves tab-completion suggestions for the provided arguments.
     *
     * @param sender The sender requesting tab completion.
     * @param args   The current arguments typed by the sender.
     * @return A list of possible tab-completion results.
     */
    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return subCommands.keySet().stream().filter(subCommand -> subCommand.startsWith(args[0].toLowerCase())).toList();
        }

        if (args.length > 1) {
            Command subCommand = subCommands.get(args[0].toLowerCase());
            if (subCommand != null) {
                return subCommand.onTabComplete(sender, args);
            }
        }

        return List.of();
    }
}
