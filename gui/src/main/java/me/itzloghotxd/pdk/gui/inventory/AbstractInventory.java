/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "Plugin Development Kit - PDK" Library.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.pdk.gui.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an abstract inventory providing a base structure for custom {@link Inventory} implementations.
 * It implements {@link InventoryHolder} and provides utility methods for setting up rows,
 * handling item placement, and responding to click or close events.
 *
 * @author ItzLoghotXD
 */
public abstract class AbstractInventory implements InventoryHolder {

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
    public abstract Component getTitle();

    /**
     * Gets the number of rows in the inventory.
     *
     * @return The number of rows in the inventory.
     */
    public abstract Row getRows();

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
        inventory = Bukkit.createInventory(this, getRows().getSlots(), getTitle());
        setItems();
        player.openInventory(inventory);
    }

    /**
     * Gets the inventory associated with this class.
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
        inventory.clear();
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

    /**
     * Handles the inventory open event.
     * This method can be overridden to implement custom behavior when the inventory is opened.
     *
     * @param event The inventory open event.
     */
    public void onOpen(InventoryOpenEvent event) {
    }

    public enum Row {
        ONE(9),
        TWO(18),
        THREE(27),
        FOUR(36),
        FIVE(45),
        SIX(54);

        private final int slots;

        Row(int slots) {
            this.slots = slots;
        }

        public int getSlots() {
            return slots;
        }
    }
}