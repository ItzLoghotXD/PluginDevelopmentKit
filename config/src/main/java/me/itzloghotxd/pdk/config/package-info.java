/**
 * Provides a YAML configuration management system for Bukkit and Spigot plugins.
 *
 * <p>This package contains classes for creating, loading, and managing multiple
 * configuration files efficiently. It includes:</p>
 *
 * <ul>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigHandler} - Handles the lifecycle, resource extraction, and IO operations of individual YAML files.</li>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigManager} - Central registry for tracking, retrieving, and performing batch operations on configurations.</li>
 * </ul>
 *
 * <p>Individual configuration files are managed via {@link me.itzloghotxd.pdk.config.ConfigHandler}, which safely initializes files
 * from the plugin jar and intercepts syntax errors. These are coordinated by the {@link me.itzloghotxd.pdk.config.ConfigManager},
 * which maps files to string identifiers for clean data retrieval and global operations like reloads.</p>
 *
 * @author ItzLoghotXD
 * @since 0.2.0
 */
package me.itzloghotxd.pdk.config;