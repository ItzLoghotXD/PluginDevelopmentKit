import me.itzloghotxd.pdk.command.Command;
import me.itzloghotxd.pdk.utilities.chat.ChatUtilities;
import org.bukkit.command.CommandSender;

import java.util.List;

public class testcommand implements Command {

    @Override
    public void execute(CommandSender sender, String[] args) {
        ChatUtilities.info(sender, "you executed a command!");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return List.of();
    }

    @Override
    public String getPermission() {
        return "test.test";
    }
}
