/**
 * Provides YAML configuration management utilities for Bukkit plugins.
 *
 * <p>This package includes classes for handling YAML-based configuration files
 * efficiently, supporting both single-file and folder-based configurations.</p>
 *
 * <p>This package includes:</p>
 * <ul>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigHandler} - Manages individual configuration files.</li>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigManager} - Handles multiple configuration files using string-based keys.</li>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigFolderHandler} - Handles scanning and managing configuration files inside a folder (including subfolders).</li>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigFolderManager} - Manages multiple configuration folders.</li>
 * </ul>
 *
 * <p>
 * {@link me.itzloghotxd.pdk.config.ConfigManager} registers and manages individual configuration files,
 * while {@link me.itzloghotxd.pdk.config.ConfigFolderHandler} automates discovery and registration
 * of configuration files from directories.
 * </p>
 *
 * <p>
 * Configuration files are identified using their relative paths (without the <code>.yml</code> extension),
 * ensuring support for nested folder structures.
 * </p>
 *
 * @author ItzLoghotXD
 * @since 0.2.0
 */
package me.itzloghotxd.pdk.config;