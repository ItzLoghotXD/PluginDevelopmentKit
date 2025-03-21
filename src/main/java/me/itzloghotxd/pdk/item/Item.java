package me.itzloghotxd.pdk.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for creating and modifying {@link ItemStack} instances with custom properties such as names, lore, enchantments, attributes, and persistent data.
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings({"unused", "deprecation", "Java8ListReplaceAll"})
public class Item {
    private final JavaPlugin plugin;

    /**
     * Constructor for the Item class.
     *
     * @param plugin The JavaPlugin instance.
     */
    public Item(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Creates an ItemStack with a specified material, name, and lore.
     *
     * @param material The material of the item.
     * @param name     The display name of the item.
     * @param lore     The lore of the item (nullable).
     * @return The created ItemStack.
     */
    public ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (!name.isEmpty()) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }

            if (lore != null && !lore.isEmpty()) {
                List<String> coloredLore = new ArrayList<>(lore);
                for (int i = 0; i < coloredLore.size(); i++) {
                    coloredLore.set(i, ChatColor.translateAlternateColorCodes('&', coloredLore.get(i)));
                }
                meta.setLore(coloredLore);
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Creates an ItemStack with a specified material, name, lore, and optional glowing effect.
     *
     * @param material The material of the item.
     * @param name     The display name of the item.
     * @param lore     The lore of the item (nullable).
     * @param glow     Whether the item should have a glowing effect.
     * @return The created ItemStack.
     */
    public ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore, boolean glow) {
        ItemStack item = addItem(material, name, lore);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (glow) {
                if (item.getType() != Material.FISHING_ROD) {
                    meta.addEnchant(Enchantment.LUCK, 1, false);
                } else {
                    meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
                }
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Creates an ItemStack with enchantments, attributes, and flags.
     *
     * @param material     The material of the item.
     * @param name         The display name of the item.
     * @param lore         The lore of the item (nullable).
     * @param enchantments The enchantments to apply (nullable).
     * @param attributes   The attributes to apply (nullable).
     * @param flags        The item flags to apply (nullable).
     * @return The created ItemStack.
     */
    public ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore, @Nullable Map<Enchantment, Integer> enchantments, @Nullable Map<Attribute, AttributeModifier> attributes, @Nullable List<ItemFlag> flags) {
        ItemStack item = addItem(material, name, lore);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (enchantments != null && !enchantments.isEmpty()) {
                for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
                    meta.addEnchant(enchantment.getKey(), enchantment.getValue(), true);
                }
            }

            if (attributes != null && !attributes.isEmpty()) {
                for (Map.Entry<Attribute, AttributeModifier> attribute : attributes.entrySet()) {
                    meta.addAttributeModifier(attribute.getKey(), attribute.getValue());
                }
            }

            if (flags != null && !flags.isEmpty()) {
                for (ItemFlag flag : flags) {
                    meta.addItemFlags(flag);
                }
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Creates an ItemStack with a unique ID stored in its PersistentDataContainer.
     *
     * @param material The material of the item.
     * @param name     The display name of the item.
     * @param lore     The lore of the item (nullable).
     * @param ID       The unique ID for the item.
     * @return The created ItemStack.
     */
    public ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore, @NotNull String ID) {
        ItemStack item = addItem(material, name, lore);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (!ID.isEmpty()) {
                ID = sanitizeID(ID);
                NamespacedKey key = new NamespacedKey(plugin, ID);
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, ChatColor.stripColor(ID).toLowerCase());
            }

            item.setItemMeta(meta);
        }

        return item;
    }
    /**
     * Creates an ItemStack with a unique ID and optional glowing effect.
     *
     * @param material The material of the item.
     * @param name     The display name of the item.
     * @param lore     The lore of the item (nullable).
     * @param ID       The unique ID for the item.
     * @param glow     Whether the item should have a glowing effect.
     * @return The created ItemStack.
     */
    public ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore, @NotNull String ID, boolean glow) {
        ItemStack item = addItem(material, name, lore, glow);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (!ID.isEmpty()) {
                ID = sanitizeID(ID);
                NamespacedKey key = new NamespacedKey(plugin, ID);
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, ChatColor.stripColor(ID).toLowerCase());
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Creates an ItemStack with a unique ID, enchantments, attributes, and flags.
     *
     * @param material     The material of the item.
     * @param name         The display name of the item.
     * @param lore         The lore of the item (nullable).
     * @param ID       The unique ID for the item.
     * @param enchantments The enchantments to apply (nullable).
     * @param attributes   The attributes to apply (nullable).
     * @param flags        The item flags to apply (nullable).
     * @return The created ItemStack.
     */
    public ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore, @NotNull String ID, @Nullable Map<Enchantment, Integer> enchantments, @Nullable Map<Attribute, AttributeModifier> attributes, @Nullable List<ItemFlag> flags) {
        ItemStack item = addItem(material, name, lore, enchantments, attributes, flags);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (!ID.isEmpty()) {
                ID = sanitizeID(ID);
                NamespacedKey key = new NamespacedKey(plugin, ID);
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, ChatColor.stripColor(ID).toLowerCase());
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    /**
     * Retrieves the first stored item ID from an ItemStack's PersistentDataContainer.
     *
     * @param item The ItemStack to check.
     * @return The first stored item ID, or null if none found.
     */
    @Nullable
    public String getItemID(@NotNull ItemStack item) {
        if (!item.hasItemMeta()) {
            return null;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return null;
        }

        for (NamespacedKey key : meta.getPersistentDataContainer().getKeys()) {
            String value = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Sanitizes an ID by removing color codes, spaces, and special characters.
     *
     * @param ID The ID to sanitize.
     * @return The sanitized ID.
     * @throws IllegalArgumentException If the ID is empty after sanitization.
     */
    private String sanitizeID(String ID) {
        ID = ID.replaceAll("&[0-9a-fk-or]+", "").replace(" ", "_").replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();
        if (ID.isEmpty()) throw new IllegalArgumentException("ID is empty");
        return ID;
    }
}
