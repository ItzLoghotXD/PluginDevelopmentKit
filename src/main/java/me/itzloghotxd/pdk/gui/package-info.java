/**
 * This package contains classes related to custom GUI implementations for Bukkit/Spigot plugins.
 * <p>
 * It includes the following classes:
 * <ul>
 *     <li>{@link me.itzloghotxd.pdk.gui.AbstractInventory} - An abstract class that provides a base structure for custom GUIs.</li>
 *     <li>{@link me.itzloghotxd.pdk.gui.InventoryListener} - A listener class that handles inventory-related events.</li>
 * </ul>
 * <p>
 * The {@link me.itzloghotxd.pdk.gui.AbstractInventory} class implements {@link org.bukkit.inventory.InventoryHolder}
 * and provides methods for setting up an inventory, handling item placement, and responding to inventory click and close events.
 * The {@link me.itzloghotxd.pdk.gui.InventoryListener} class listens for {@link org.bukkit.event.inventory.InventoryClickEvent}
 * and {@link org.bukkit.event.inventory.InventoryCloseEvent}, delegating the event handling to the corresponding {@link me.itzloghotxd.pdk.gui.AbstractInventory}.
 * </p>
 * <p>
 * Author: ItzLoghotXD
 * </p>
 */
package me.itzloghotxd.pdk.gui;
