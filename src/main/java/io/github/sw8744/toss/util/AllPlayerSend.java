package io.github.sw8744.toss.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AllPlayerSend {
    public static void sendAllPlayer(String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }
}
