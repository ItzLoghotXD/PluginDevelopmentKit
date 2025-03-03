package actions;

import me.itzloghotxd.pdk.actions.Action;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandAction implements Action {

    @Override
    public String getIdentifier() {
        return "COMMAND";
    }

    @Override
    public void execute(@NotNull JavaPlugin plugin, @NotNull Player player, @Nullable String data) {
        if (data == null) return;
        player.performCommand(data);
    }
}
