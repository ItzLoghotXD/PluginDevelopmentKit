package me.itzloghotxd.pdk.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

/**
 * Handles inventory-related events such as clicks and inventory closing.
 * This class listens for {@link InventoryClickEvent} and {@link InventoryCloseEvent}
 * and delegates the event handling to the corresponding {@link AbstractInventory}.
 *
 * @author ItzLoghotXD
 */
public class InventoryListener implements Listener {

    /**
     * Handles inventory click events.
     * If the inventory belongs to an {@link AbstractInventory}, it calls its {@code handleInventory} method.
     *
     * @param event The inventory click event.
     */
    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof AbstractInventory inventory) {
            if (event.getCurrentItem() == null) return;

            inventory.handleInventory(event);
        }
    }

    /**
     * Handles inventory close events.
     * If the inventory belongs to an {@link AbstractInventory}, it calls its {@code onClose} method.
     *
     * @param event The inventory close event.
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof AbstractInventory inventory) {
            inventory.onClose(event);
        }
    }
}
