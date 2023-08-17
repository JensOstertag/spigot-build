package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import de.korzhorz.build.handlers.ProtectionHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EVT_BlockPlaceEvent implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(!(ProtectionHandler.isProtected(player.getWorld().getName()))) {
            return;
        }
        
        if(!(Data.inventories.containsKey(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}
