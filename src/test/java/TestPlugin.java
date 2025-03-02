import me.itzloghotxd.pdk.utilities.chat.ChatUtilities;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin {
    private ChatUtilities chatUtilities;

    public void onEnable() {
        chatUtilities = new ChatUtilities();

        chatUtilities.broadcastMessage("hi!");
    }
}
