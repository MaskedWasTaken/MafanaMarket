package me.TahaCheji.data.menu;

import me.TahaCheji.data.list.Listing;
import me.TahaCheji.data.list.ListingData;
import me.TahaCheji.data.market.ItemType;
import me.TahaCheji.data.shop.ShopData;
import me.TahaCheji.util.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MarketShopMenu implements InventoryHolder {


    Inventory gui;

    public ItemStack getCloseShop() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.RED + "Close");
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "Click to close the menu");
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getClickItem(ItemType type) {
        ItemStack item = null;
        if(type == ItemType.ITEM) {
            item = new ItemStack(Material.ROTTEN_FLESH);
        }
        if(type == ItemType.BOW) {
            item = new ItemStack(Material.BOW);
        }
        if(type == ItemType.BOOTS) {
            item = new ItemStack(Material.CHAINMAIL_BOOTS);
        }
        if(type == ItemType.CHESTPLATE) {
            item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        }
        if(type == ItemType.LEGGGINGS) {
            item = new ItemStack(Material.LEATHER_LEGGINGS);
        }
        if(type == ItemType.HELMET) {
            item = new ItemStack(Material.GOLDEN_HELMET);
        }
        if(type == ItemType.SWORD) {
            item = new ItemStack(Material.IRON_SWORD);
        }
        if(type == ItemType.MATERIAL) {
            item = new ItemStack(Material.SLIME_BALL);
        }
        if(type == ItemType.SPELL) {
            item = new ItemStack(Material.BOOK);
        }
        if(type == ItemType.TOOL) {
            item = new ItemStack(Material.WOODEN_PICKAXE);
        }
        if(type == null) {
            item = new ItemStack(Material.GOLD_NUGGET);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName(ChatColor.GREEN + "All Listings");
            lore.add("--------------------------");
            lore.add(ChatColor.GOLD + "Click to see all listings");
            lore.add("--------------------------");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.GREEN + type.getLore());
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "Click to see all " + type.getLore() +  " listings");
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getShopInfo(Player player) {
        if(ShopData.hasShop(player)) {
            ItemStack item = new ItemStack(Material.GOLD_BLOCK);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName(ChatColor.GOLD + "Shop");
            lore.add("--------------------------");
            lore.add(ChatColor.GOLD + "Click to see your shop");
            lore.add("--------------------------");
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        } else {
            ItemStack item = new ItemStack(Material.BARRIER);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            meta.setDisplayName(ChatColor.RED + "Shop Error");
            lore.add("--------------------------");
            lore.add(ChatColor.GOLD + "You do not have a shop please create one");
            lore.add("--------------------------");
            meta.setLore(lore);
            item.setItemMeta(meta);
            return item;
        }
    }


    public MarketShopMenu(Player player) {
        gui = Bukkit.createInventory(null, 54, ChatColor.GRAY + "" + ChatColor.BOLD + "MarketMenu");
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta newmeta = newItem.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + "");
        newmeta.setLore(lore);
        newItem.setItemMeta(newmeta);

        gui.setItem(10, getClickItem(ItemType.ITEM));
        gui.setItem(11, getClickItem(ItemType.TOOL));
        gui.setItem(12, getClickItem(ItemType.SWORD));
        gui.setItem(19, getClickItem(ItemType.SPELL));
        gui.setItem(20, getClickItem(ItemType.MATERIAL));
        gui.setItem(21, getClickItem(ItemType.BOW));
        gui.setItem(28, getClickItem(ItemType.BOOTS));
        gui.setItem(29, getClickItem(ItemType.LEGGGINGS));
        gui.setItem(30, getClickItem(ItemType.CHESTPLATE));
        gui.setItem(37, getClickItem(ItemType.HELMET));
        gui.setItem(38, getClickItem(null));
        gui.setItem(25, getShopInfo(player));
        gui.setItem(49, getCloseShop());


        for (int emptySlot = 0; emptySlot < gui.getSize(); emptySlot++) {
            if (gui.getItem(emptySlot) == null || gui.getItem(emptySlot).getType().equals(Material.AIR)) {
                gui.setItem(emptySlot, newItem);
            }
        }

    }


    @Override
    public Inventory getInventory() {
        return gui;
    }

}
