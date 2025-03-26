package me.itzloghotxd.pdk.utilities.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for creating and modifying {@link ItemStack} instances with custom properties such as names, lore, enchantments, attributes.
 * <p>
 * (If you want to add your own feature then you can extend this class and override methods)
 *
 * @author ItzLoghotXD
 */
@SuppressWarnings({"unused", "DeprecatedIsStillUsed", "Java8ListReplaceAll"})
public class Item {
    /**
     * Creates an ItemStack with a specified material, name, and lore.
     *
     * @param material The material of the item.
     * @param name     The display name of the item.
     * @param lore     The lore of the item (nullable).
     * @return The created ItemStack.
     */
    @Deprecated // (1.5.1) Use {@link Item#addItem(Material, String, List, Map, Map, List)} or {@link Item#addItem(Material, String, List, boolean)}
    public static ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore) {
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
    public static ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore, boolean glow) {
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
    public static ItemStack addItem(@NotNull Material material, @NotNull String name, @Nullable List<String> lore, @Nullable Map<Enchantment, Integer> enchantments, @Nullable Map<Attribute, AttributeModifier> attributes, @Nullable List<ItemFlag> flags) {
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
}
