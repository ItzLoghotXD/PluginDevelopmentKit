/**
 * Provides a custom GUI framework for Bukkit and Spigot plugins.
 *
 * <p>This package contains classes for creating, managing, and handling interactions
 * within custom inventories. It includes:</p>
 *
 * <ul>
 *     <li>{@link me.itzloghotxd.pdk.gui.inventory.AbstractInventory} - Base structure for building custom GUI implementations.</li>
 *     <li>{@link me.itzloghotxd.pdk.gui.inventory.InventoryListener} - Listener that handles and delegates inventory events.</li>
 * </ul>
 *
 * <p>Custom GUIs are built by extending {@link me.itzloghotxd.pdk.gui.inventory.AbstractInventory}, which manages item placement
 * and state. Interactivity is driven by the {@link me.itzloghotxd.pdk.gui.inventory.InventoryListener}, which must be registered
 * by the plugin to catch and route inventory events to their respective menus.</p>
 *
 * @author ItzLoghotXD
 */
package me.itzloghotxd.pdk.gui.inventory;