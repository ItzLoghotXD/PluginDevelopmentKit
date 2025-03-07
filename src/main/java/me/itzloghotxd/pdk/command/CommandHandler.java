package me.itzloghotxd.pdk.command;

import me.itzloghotxd.pdk.utilities.chat.ChatUtilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * Handles command execution and tab completion for a specific registered command.
 * It delegates command handling to {@link CommandManager}.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings("unused")
public class CommandHandler implements CommandExecutor, TabExecutor {

    private final String name;
    private final CommandManager commandManager;

    /**
     * Constructs a new CommandHandler for a specified command.
     *
     * @param plugin The plugin instance registering this command handler.
     * @param name   The name of the command being handled.
     */
    public CommandHandler(@NotNull JavaPlugin plugin, @NotNull String name, @NotNull CommandManager commandManager) {
        this.commandManager = commandManager;
        this.name = name;
        Objects.requireNonNull(plugin.getCommand(name)).setExecutor(this);
        Objects.requireNonNull(plugin.getCommand(name)).setTabCompleter(this);
    }

    /**
     * Handles the execution of the command.
     *
     * @param sender  The sender who executed the command.
     * @param command The Bukkit command object.
     * @param label   The command label.
     * @param args    The arguments passed to the command.
     * @return true if the command was handled successfully, false otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            ChatUtilities.info(sender, "Usage: /" + name + " <subcommand>");
            return true;
        }

        return commandManager.executeCommand(sender, args);
    }

    /**
     * Handles tab completion for the command.
     *
     * @param sender The sender requesting tab completion.
     * @param command The Bukkit command object.
     * @param alias The alias used for the command.
     * @param args The current arguments typed by the sender.
     * @return A list of possible tab-completion results.
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return commandManager.getTabCompletions(sender, args);
    }
}
