package me.TahaCheji.data.shop;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.TahaCheji.data.buyingListedItems.BuyListedItemGui;
import me.TahaCheji.data.list.Listing;
import me.TahaCheji.data.list.ListingData;
import me.TahaCheji.data.menu.MarketShopMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ShopsGuiClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().contains("Shop")) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if(e.getSlot() == 49) {
            Player player = (Player) e.getWhoClicked();
            player.openInventory(new MarketShopMenu(player).getInventory());
        }
        if (!new NBTItem(e.getCurrentItem()).hasKey("ListUUID")) {
            e.setCancelled(true);
            return;
        }
        if(new NBTItem(e.getCurrentItem()).hasKey("Cancel")) {
            e.setCancelled(true);
            for (Listing listing : ListingData.getAllSavedListings()) {
                if (new NBTItem(e.getCurrentItem()).getString("ListUUID").contains(listing.getUuid().toString())) {
                    listing.removeListing();
                    Player player = (Player) e.getWhoClicked();
                    player.sendMessage(ChatColor.RED + "You have canceled this listing");
                }
            }
            Player player = (Player) e.getWhoClicked();
            player.closeInventory();
            return;
        }
        for (Listing listing : ListingData.getAllSavedListings()) {
            if (new NBTItem(e.getCurrentItem()).getString("ListUUID").contains(listing.getUuid().toString())) {
                Player player = (Player) e.getWhoClicked();
                player.openInventory(new BuyListedItemGui(listing, player).getInventory());
            }
        }
    }


}
