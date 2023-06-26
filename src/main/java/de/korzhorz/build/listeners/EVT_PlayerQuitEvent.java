package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import de.korzhorz.build.configs.Messages;
import de.korzhorz.build.util.ColorTranslator;
import de.korzhorz.build.util.InventoryData;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class EVT_PlayerQuitEvent implements Listener {
    Messages messages = new Messages();

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        if(!(Data.inventories.containsKey(player.getUniqueId()))) {
            return;
        }
        
        InventoryData inventoryData = Data.inventories.get(player.getUniqueId());
        Data.inventories.remove(player.getUniqueId());
        inventoryData.restore();
        
        player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.build.disable")));
    }
}
