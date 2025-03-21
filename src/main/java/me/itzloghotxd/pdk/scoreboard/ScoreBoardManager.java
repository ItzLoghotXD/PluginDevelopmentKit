package me.itzloghotxd.pdk.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manages the creation and updating of player scoreboards in Bukkit.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings({"unused", "deprecation", "ConstantConditions"})
public class ScoreBoardManager {

    /**
     * Constructs a ScoreBoardManager instance.
     */
    public ScoreBoardManager() {
    }

    /**
     * Updates an individual player's scoreboard.
     *
     * @param player       The player whose scoreboard is being updated.
     * @param name         The name of the scoreboard.
     * @param criteria     The criteria for the scoreboard.
     * @param displayTitle The title displayed on the scoreboard.
     * @param displaySlot  The slot where the scoreboard will be displayed.
     * @param lines        The lines of text to display on the scoreboard.
     */
    public void updatePlayerScoreboard(@NotNull Player player, @NotNull String name, @NotNull String criteria, @NotNull String displayTitle, @NotNull DisplaySlot displaySlot, @NotNull List<String> lines) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;

        Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard == null || scoreboard == manager.getMainScoreboard()) {
            scoreboard = manager.getNewScoreboard();
        }

        Objective oldObjective = scoreboard.getObjective(name);
        if (oldObjective != null) {
            oldObjective.unregister();
        }

        Objective objective = scoreboard.registerNewObjective(name, criteria, ChatColor.translateAlternateColorCodes('&', displayTitle));
        objective.setDisplaySlot(displaySlot);

        Set<String> usedLines = new HashSet<>();
        int scoreValue = lines.size();

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) continue;
            String coloredLine = ChatColor.translateAlternateColorCodes('&', line);

            while (usedLines.contains(coloredLine)) {
                coloredLine += ChatColor.RESET;
            }

            usedLines.add(coloredLine);
            objective.getScore(coloredLine).setScore(scoreValue--);
        }

        if (player.getScoreboard() != scoreboard) {
            player.setScoreboard(scoreboard);
        }
    }
}
