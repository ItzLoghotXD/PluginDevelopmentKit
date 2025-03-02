package me.itzloghotxd.pdk.utilities.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class for sending formatted chat messages to players and command senders in a Bukkit-based server.
 * This class provides methods to send different types of messages, including information, warnings, and errors.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings("unused")
public final class ChatUtilities {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ChatUtilities() {
    }

    /**
     * Broadcasts a message to all online players.
     *
     * @param message The message to be broadcasted. Supports color codes using '&'.
     */
    public static void broadcastMessage(@NotNull String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    // ==============================
    // PLAYER MESSAGES
    // ==============================

    /**
     * Sends an information message to a player.
     *
     * @param player The player to send the message to.
     * @param info   The information message.
     */
    public static void info(@NotNull Player player, @NotNull String info) {
        player.sendMessage(ChatColor.WHITE + info);
    }

    /**
     * Sends a warning message to a player.
     *
     * @param player  The player to send the message to.
     * @param warning The warning message.
     */
    public static void warn(@NotNull Player player, @NotNull String warning) {
        player.sendMessage(ChatColor.YELLOW + "WARN: " + warning);
    }

    /**
     * Sends an error message to a player.
     *
     * @param player The player to send the message to.
     * @param error  The error message.
     */
    public static void error(@NotNull Player player, @NotNull String error) {
        player.sendMessage(ChatColor.RED + "ERROR: " + error);
    }

    /**
     * Sends an unauthorized access message to a player.
     *
     * @param player The player attempting an unauthorized action.
     */
    public static void unauthorized(@NotNull Player player) {
        error(player, "You do not have the Permission to perform this Action!");
    }

    // ==============================
    // COMMAND SENDER MESSAGES
    // ==============================

    /**
     * Sends an information message to a command sender.
     *
     * @param sender The command sender to send the message to.
     * @param info   The information message.
     */
    public static void info(@NotNull CommandSender sender, @NotNull String info) {
        sender.sendMessage(ChatColor.WHITE + info);
    }

    /**
     * Sends a warning message to a command sender.
     *
     * @param sender  The command sender to send the message to.
     * @param warning The warning message.
     */
    public static void warn(@NotNull CommandSender sender, @NotNull String warning) {
        sender.sendMessage(ChatColor.YELLOW + "WARN: " + warning);
    }

    /**
     * Sends a generic error message to a command sender.
     *
     * @param sender The command sender to send the message to.
     */
    public static void error(@NotNull CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "ERROR: An error occurred while executing the command.");
    }

    /**
     * Sends a specific error message to a command sender.
     *
     * @param sender The command sender to send the message to.
     * @param error  The error message.
     */
    public static void error(@NotNull CommandSender sender, @NotNull String error) {
        sender.sendMessage(ChatColor.RED + "ERROR: " + error);
    }

    /**
     * Notifies a command sender that only players can execute the command.
     *
     * @param sender The command sender attempting to run a player-only command.
     */
    public static void notPlayer(@NotNull CommandSender sender) {
        error(sender, "Only Players can execute this command!");
    }

    /**
     * Sends an unauthorized access message to a command sender.
     *
     * @param sender The command sender attempting an unauthorized action.
     */
    public static void unauthorized(@NotNull CommandSender sender) {
        error(sender, "You do not have the Permission to perform this Command!");
    }
}
