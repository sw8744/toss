package io.github.sw8744.toss.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GUIManager {
    public static void openGUI(Player p, String name, int size, ArrayList<ItemStack> items) {
        Inventory gui = Bukkit.createInventory((InventoryHolder)null, size, name);
        for(int i=0; i<size; i++) {
            gui.setItem(i, items.get(i));
        }
        p.openInventory(gui);
    }
}
