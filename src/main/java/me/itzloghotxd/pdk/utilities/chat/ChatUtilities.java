package me.itzloghotxd.pdk.utilities.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ChatUtilities {
    public ChatUtilities() {
    }

    public void broadcastMessage(@NotNull String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

//  PLAYER

    public void info(@NotNull Player player, @NotNull String info) {
        player.sendMessage(ChatColor.WHITE + info);
    }

    public void warn(@NotNull Player player, @NotNull String warning) {
        player.sendMessage(ChatColor.YELLOW + "WARN: " + warning);
    }

    public void error(@NotNull Player player, @NotNull String error) {
        player.sendMessage(ChatColor.RED + "ERROR: " + error);
    }

    public void unauthorized(@NotNull Player player) {
        error(player, "You do not have the Permission to perform this Action!");
    }

//  COMMAND SENDER

    public void info(@NotNull CommandSender sender, @NotNull String info) {
        sender.sendMessage(ChatColor.WHITE + info);
    }

    public void warn(@NotNull CommandSender sender, @NotNull String warning) {
        sender.sendMessage(ChatColor.YELLOW + "WARN: " + warning);
    }

    public void error(@NotNull CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "ERROR: An error occurred while executing the command.");
    }

    public void error(@NotNull CommandSender sender, @NotNull String error) {
        sender.sendMessage(ChatColor.RED + "ERROR: " + error);
    }

    public void notPlayer(@NotNull CommandSender sender) {
        error(sender, "Only Players can execute this command!");
    }

    public void unauthorized(@NotNull CommandSender sender) {
        error(sender, "You do not have the Permission to perform this Command!");
    }
}
