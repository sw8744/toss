package io.github.sw8744.toss.enchant;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.joml.Random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.github.sw8744.toss.util.GUIManager.openGUI;

public class Enchant {
    public static ItemStack randomEnchant(ItemStack items) {
        Random random = new Random();
        int maxLevel = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("maxEnchantLevel");
        int maxAmount = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("maxEnchantAmount");
        int amount = random.nextInt(maxAmount) + 1;
        clearEnchant(items);
        ArrayList<Enchantment> enchantments= new ArrayList<Enchantment>();
        enchantments = addEnchants(enchantments);
        for(int i = 0; i < amount; i++) {
            int enchantNumber = random.nextInt(enchantments.size());
            Enchantment enchantment = enchantments.get(enchantNumber);
            int level = random.nextInt(maxLevel) + 1;
            items.addUnsafeEnchantment(enchantment, level);
            enchantments.remove(enchantment);
        }
        return items;
    }

    public static ItemStack clearEnchant(ItemStack items) {
        items.getEnchantments().clear();
        return items;
    }

    @EventHandler
    public void onRightClickOnEnchantingTable(PlayerInteractEvent e) {
        // if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE))  {
            e.setCancelled(true);
            ArrayList<ItemStack> guiSlots = new ArrayList<ItemStack>();
            for(int i=0; i<9; i++) {
                ItemStack item = new ItemStack(Material.AIR);
                if(i <= 3) {
                    item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(" ");
                    item.setItemMeta(meta);

                }
                else if(i >= 5) {
                    item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(" ");
                }
                guiSlots.add(item);
            }
            openGUI(e.getPlayer(), "Random Enchant", 9, guiSlots);
        //}
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        List<Integer> slots = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        if(e.getSlot() == 4 && e.getView().getOriginalTitle().equals("Random Enchant") && e.getClick().isLeftClick() && e.getClickedInventory().getItem(4).getType() != Material.AIR) {
            e.setCancelled(true);
            ItemStack item = e.getClickedInventory().getItem(4);
            item = randomEnchant(item);
            e.getClickedInventory().setItem(4, item);
        }
        else if(e.getSlot() == 4 && e.getView().getOriginalTitle().equals("Random Enchant") && e.getClick().isRightClick() && e.getClickedInventory().getItem(4).getType() != Material.AIR) {
            e.setCancelled(true);
            ItemStack item = e.getClickedInventory().getItem(4);
            p.getInventory().addItem(item);
            e.getClickedInventory().setItem(4, new ItemStack(Material.AIR));
        }
    }

    public static ArrayList<Enchantment> addEnchants(ArrayList<Enchantment> arrayList) {
        arrayList.add(Enchantment.ARROW_DAMAGE);
        arrayList.add(Enchantment.ARROW_FIRE);
        arrayList.add(Enchantment.ARROW_INFINITE);
        arrayList.add(Enchantment.ARROW_KNOCKBACK);
        arrayList.add(Enchantment.BINDING_CURSE);
        arrayList.add(Enchantment.CHANNELING);
        arrayList.add(Enchantment.DAMAGE_ALL);
        arrayList.add(Enchantment.DAMAGE_ARTHROPODS);
        arrayList.add(Enchantment.DAMAGE_UNDEAD);
        arrayList.add(Enchantment.DEPTH_STRIDER);
        arrayList.add(Enchantment.DIG_SPEED);
        arrayList.add(Enchantment.DURABILITY);
        arrayList.add(Enchantment.FIRE_ASPECT);
        arrayList.add(Enchantment.FROST_WALKER);
        arrayList.add(Enchantment.IMPALING);
        arrayList.add(Enchantment.KNOCKBACK);
        arrayList.add(Enchantment.LOOT_BONUS_BLOCKS);
        arrayList.add(Enchantment.LOOT_BONUS_MOBS);
        arrayList.add(Enchantment.LOYALTY);
        arrayList.add(Enchantment.LUCK);
        arrayList.add(Enchantment.LURE);
        arrayList.add(Enchantment.MENDING);
        arrayList.add(Enchantment.MULTISHOT);
        arrayList.add(Enchantment.OXYGEN);
        arrayList.add(Enchantment.PIERCING);
        arrayList.add(Enchantment.PROTECTION_ENVIRONMENTAL);
        arrayList.add(Enchantment.PROTECTION_EXPLOSIONS);
        arrayList.add(Enchantment.PROTECTION_FALL);
        arrayList.add(Enchantment.PROTECTION_FIRE);
        arrayList.add(Enchantment.PROTECTION_PROJECTILE);
        arrayList.add(Enchantment.QUICK_CHARGE);
        arrayList.add(Enchantment.RIPTIDE);
        arrayList.add(Enchantment.SILK_TOUCH);
        arrayList.add(Enchantment.SOUL_SPEED);
        arrayList.add(Enchantment.SWEEPING_EDGE);
        arrayList.add(Enchantment.SWIFT_SNEAK);
        arrayList.add(Enchantment.THORNS);
        arrayList.add(Enchantment.VANISHING_CURSE);
        arrayList.add(Enchantment.WATER_WORKER);
        return arrayList;
    }
}
