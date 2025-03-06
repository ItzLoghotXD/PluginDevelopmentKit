import actions.CommandAction;
import me.itzloghotxd.pdk.actions.ActionManager;
import me.itzloghotxd.pdk.command.CommandHandler;
import me.itzloghotxd.pdk.command.CommandManager;
import me.itzloghotxd.pdk.item.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;


public final class TestPlugin extends JavaPlugin {

    private static TestPlugin plugin;
    private CommandManager commandManager;
    private ActionManager actionManager;
    private Item item;

    public void onEnable() {
        plugin = this;

        commandManager = new CommandManager();
        new CommandHandler(this, "test");
        registerCommands();

        actionManager = new ActionManager(this);
        registerActions();

        item = new Item(this);
        createCustomSword();
    }

    public void registerCommands() {
        commandManager.registerCommand("test2", new testcommand());
    }

    public void registerActions() {
        actionManager.registerAction(new CommandAction());
    }

    public void createCustomSword() {
        // Define the material
        Material material = Material.NETHERITE_SWORD;

        // Set a custom name (supports color codes)
        String name = ChatColor.GOLD + "God Slayer";

        // Define lore (description)
        List<String> lore = Arrays.asList(
                ChatColor.DARK_RED + "A sword forged in the flames of the nether.",
                ChatColor.GRAY + "Only wielded by the chosen ones."
        );

        // Define enchantments with levels
        Map<Enchantment, Integer> enchantments = new HashMap<>();
        enchantments.put(Enchantment.DAMAGE_ALL, 10); // Sharpness X
        enchantments.put(Enchantment.FIRE_ASPECT, 2); // Fire Aspect II
        enchantments.put(Enchantment.DURABILITY, 5);  // Unbreaking V

        // Define attributes (Attack Damage boost)
        Map<Attribute, AttributeModifier> attributes = new HashMap<>();
        attributes.put(Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", 20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

        attributes.put(Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier(UUID.randomUUID(), "generic.attack_speed", 1.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));

//        // Define item flags (Hide Enchantments and Attributes)
//        List<ItemFlag> flags = Arrays.asList(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        // Call the method to create the custom item
        ItemStack godSword = item.addItem(material, name, lore, "god_slayer", enchantments, attributes, null);
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

    public Item getItemManager() {
        return item;
    }
}
