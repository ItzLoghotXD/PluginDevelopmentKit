import me.itzloghotxd.pdk.command.CommandHandler;
import me.itzloghotxd.pdk.command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
    private CommandManager commandManager;

    public void onEnable() {
        commandManager = new CommandManager();
        new CommandHandler(this, "test");
        registerCommands();
    }

    public void registerCommands() {
        commandManager.registerCommand("test2", new testcommand());
    }
}
