package io.github.sw8744.toss.money;

import io.github.sw8744.toss.util.PlayerDataManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Money {
    static PlayerDataManager playerDataManager = new PlayerDataManager();
    static FileConfiguration playerData = playerDataManager.getConfig();

    public static void resetMoney(Player p) {
        if(!playerData.getKeys(false).contains(p.getName())) {
            playerData.set(p.getUniqueId().toString() + ".money", 0);
        }
        playerDataManager.saveConfig();
    }

    public static void importMoney() {
        for (String uuid : playerData.getKeys(false)) {
            playerData.set(uuid + ".money", 0);
        }
        playerDataManager.saveConfig();
    }
}
