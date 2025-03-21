package me.itzloghotxd.pdk.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an abstract inventory that provides a base structure for custom GUI implementations.
 * This class implements {@link InventoryHolder} and allows for handling inventory interactions.
 * <p>
 * It provides methods for setting up an inventory, handling item placement, and responding
 * to inventory click and close events.
 * </p>
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings({"unused", "deprecation"})
public abstract class AbstractInventory implements InventoryHolder {

    /** The inventory associated with this GUI. */
    protected Inventory inventory;

    /**
     * Constructs an {@code AbstractInventory} instance.
     */
    public AbstractInventory() {
    }

    /**
     * Gets the title of the inventory.
     *
     * @return The title of the inventory.
     */
    public abstract String getTitle();

    /**
     * Gets the number of slots in the inventory.
     *
     * @return The number of slots in the inventory.
     */
    public abstract int getSlots();

    /**
     * Handles inventory interactions when an item is clicked.
     *
     * @param event The inventory click event.
     */
    public abstract void handleInventory(InventoryClickEvent event);

    /**
     * Sets the initial items inside the inventory.
     * This method should be implemented to define the inventory layout.
     */
    public abstract void setItems();

    /**
     * Opens the inventory for a specific player.
     * This method initializes the inventory and populates it with items.
     *
     * @param player The player for whom the inventory is opened.
     */
    public void open(@NotNull Player player) {
        inventory = Bukkit.createInventory(this, getSlots(), getTitle());
        setItems();
        player.openInventory(inventory);
    }

    /**
     * Gets the inventory associated with this GUI.
     *
     * @return The inventory instance.
     */
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    /**
     * Reloads all items in the inventory by clearing it first and then repopulating it.
     */
    protected void reloadItems() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, null);
        }
        setItems();
    }

    /**
     * Handles the inventory close event.
     * This method can be overridden to implement custom behavior when the inventory is closed.
     *
     * @param event The inventory close event.
     */
    public void onClose(InventoryCloseEvent event) {
    }
}
