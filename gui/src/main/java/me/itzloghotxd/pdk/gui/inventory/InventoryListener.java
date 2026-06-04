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

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

/**
 * Listens for inventory interactions and delegates them to the corresponding {@link AbstractInventory}.
 * It handles inventory clicks, openings, and closures via {@link InventoryClickEvent},
 * {@link InventoryOpenEvent}, and {@link InventoryCloseEvent}. <br>
 *
 * Plugins utilizing this framework must register this listener in their main class.
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
            if (event.getClickedInventory() == null) return;
            if (event.getClickedInventory() != event.getView().getTopInventory()) return;

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

    /**
     * Handles inventory open events.
     * If the inventory belongs to an {@link AbstractInventory}, it calls its {@code onOpen} method.
     *
     * @param event The inventory open event.
     */
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof AbstractInventory inventory) {
            inventory.onOpen(event);
        }
    }
}