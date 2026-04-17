/**
 * Provides YAML configuration management utilities for Bukkit plugins.
 *
 * <p>This package includes classes for handling YAML-based configuration files
 * efficiently, supporting both single-file and multi-file configurations.</p>
 *<p>
 * This package includes:
 * <ul>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigHandler} - Manages individual configuration files.</li>
 *     <li>{@link me.itzloghotxd.pdk.config.ConfigManager} - Handles multiple configuration files using String.</li>
 * </ul>
 *
 * <p>
 * {@link me.itzloghotxd.pdk.config.ConfigManager} registers files and delegates handling to
 * {@link me.itzloghotxd.pdk.config.ConfigHandler}.
 * </p>
 *
 * @author ItzLoghotXD
 * @since 0.2.0
 */
package me.itzloghotxd.pdk.config;