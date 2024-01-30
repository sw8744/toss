package io.github.sw8744.toss.enchant;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.joml.Random;

import java.util.ArrayList;

public class Enchant {
    public static ItemStack randomEnchant(ItemStack items) {
        Random random = new Random();
        int maxLevel = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("maxEnchantLevel");
        int maxAmount = Bukkit.getPluginManager().getPlugin("Toss").getConfig().getInt("maxEnchantAmount");
        int amount = random.nextInt(maxAmount) + 1;
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
