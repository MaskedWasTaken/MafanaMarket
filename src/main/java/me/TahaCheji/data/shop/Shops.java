package me.TahaCheji.data.shop;

import me.TahaCheji.data.list.Listing;
import me.TahaCheji.data.list.ListingData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Shops {

    private final Player owner;
    private final UUID uuid;

    public Shops(Player owner, UUID uuid) {
        this.owner = owner;
        this.uuid = uuid;
    }

    public Shops(Player owner) {
        this.owner = owner;
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
    }

    public void saveShop() throws IOException {
        ShopData.saveShop(this);
    }

    public void addListing(Listing listing) throws IOException {
        ListingData.saveListing(listing, this);
    }

    public Player getOwner() {
        return owner;
    }

    public List<Listing> getItems() {
        return ListingData.getAllSavedListings(this);
    }

    public UUID getUuid() {
        return uuid;
    }
}
