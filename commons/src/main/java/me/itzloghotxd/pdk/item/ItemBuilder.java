/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "Plugin Development Kit - PDK" Library.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.pdk.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * A fluent builder utility for constructing and customizing {@link ItemStack} objects.
 * This class simplifies item creation by stacking modifications like names, lore,
 * enchantments, and item flags.
 *
 * @author ItzLoghotXD
 */
public final class ItemBuilder {

    private final Material type;
    private Component name;
    private List<Component> lore;
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private final Set<ItemFlag> flags = new HashSet<>();
    private Consumer<ItemMeta> metaConsumer;

    private ItemBuilder(Material type) {
        this.type = Objects.requireNonNull(type, "type");
    }

    /**
     * Initiates a new builder chain for a specific item type.
     *
     * @param type The material type of the item.
     * @return A new builder instance.
     */
    @NotNull
    public static ItemBuilder of(@NotNull Material type) {
        return new ItemBuilder(type);
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The text component representing the name.
     * @return The builder instance for chaining.
     */
    @NotNull
    public ItemBuilder name(@NotNull Component name) {
        this.name = Objects.requireNonNull(name, "name");
        return this;
    }

    /**
     * Sets the lore lines of the item.
     *
     * @param lore An array of text components representing the lore.
     * @return The builder instance for chaining.
     */
    @NotNull
    public ItemBuilder lore(Component... lore) {
        this.lore = List.of(Objects.requireNonNull(lore, "lore"));
        return this;
    }

    /**
     * Applies an enchantment to the item.
     *
     * @param enchantment The type of enchantment.
     * @param level The power level (must be greater than 0).
     * @return The builder instance for chaining.
     * @throws IllegalArgumentException If the level is less than 1.
     */
    @NotNull
    public ItemBuilder enchant(@NotNull Enchantment enchantment, int level) {
        if (level < 1) throw new IllegalArgumentException("level must be greater than 0");
        enchantments.put(Objects.requireNonNull(enchantment, "enchantment"), level);
        return this;
    }

    /**
     * Applies visibility flags to the item's details.
     *
     * @param flags The item flags to append.
     * @return The builder instance for chaining.
     */
    @NotNull
    public ItemBuilder flags(@NotNull ItemFlag... flags) {
        this.flags.addAll(List.of(Objects.requireNonNull(flags, "flags")));
        return this;
    }

    /**
     * Provides direct access to the raw {@link ItemMeta} for custom properties
     * not natively covered by the builder. Multiple consumers will be chained sequentially.
     *
     * @param consumer A callback to manipulate the item's metadata.
     * @return The builder instance for chaining.
     */
    @NotNull
    public ItemBuilder meta(@NotNull Consumer<ItemMeta> consumer) {
        Objects.requireNonNull(consumer, "consumer");

        if (metaConsumer == null) {
            metaConsumer = consumer;
        } else {
            metaConsumer = metaConsumer.andThen(consumer);
        }

        return this;
    }

    /**
     * Compiles the accumulated traits into a single {@link ItemStack} with a quantity of 1.
     *
     * @return The finalized item stack.
     */
    @NotNull
    public ItemStack build() {
        return build(1);
    }

    /**
     * Compiles the accumulated traits into a single {@link ItemStack} with a specific quantity.
     *
     * @param amount The stack size (must be greater than 0).
     * @return The finalized item stack.
     * @throws IllegalArgumentException If the stack size is less than 1.
     */
    @NotNull
    public ItemStack build(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }

        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();

        if (name != null) meta.displayName(name);
        if (lore != null) meta.lore(lore);
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            meta.addEnchant(entry.getKey(), entry.getValue(), true);
        }
        if (!flags.isEmpty()) meta.addItemFlags(flags.toArray(ItemFlag[]::new));
        if (metaConsumer != null) metaConsumer.accept(meta);

        item.setItemMeta(meta);

        return item;
    }
}
