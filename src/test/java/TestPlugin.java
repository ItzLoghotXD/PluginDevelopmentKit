import actions.CommandAction;
import me.itzloghotxd.pdk.actions.ActionManager;
import me.itzloghotxd.pdk.command.CommandHandler;
import me.itzloghotxd.pdk.command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin {

    private static TestPlugin plugin;
    private CommandManager commandManager;
    private ActionManager actionManager;

    public void onEnable() {
        plugin = this;

        commandManager = new CommandManager();
        new CommandHandler(this, "test");
        registerCommands();

        actionManager = new ActionManager(this);
        registerActions();
    }

    public void registerCommands() {
        commandManager.registerCommand("test2", new testcommand());
    }

    public void registerActions() {
        actionManager.registerAction(new CommandAction());
    }

    public static TestPlugin getPlugin() {
        return plugin;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
}
