package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import de.korzhorz.build.handlers.ProtectionHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;

public class EVT_HangingPlaceEvent implements Listener {
    @EventHandler
    public void onHangingPlace(HangingPlaceEvent event) {
        Entity entity = event.getEntity();

        if(!(ProtectionHandler.isProtected(entity.getWorld().getName()))) {
            return;
        }

        Player player = event.getPlayer();
        
        if(player == null || !(Data.inventories.containsKey(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}
