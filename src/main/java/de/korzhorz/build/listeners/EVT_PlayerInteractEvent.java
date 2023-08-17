package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import de.korzhorz.build.handlers.ProtectionHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EVT_PlayerInteractEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!(ProtectionHandler.isProtected(player.getWorld().getName()))) {
            return;
        }
        
        if(!(Data.inventories.containsKey(player.getUniqueId())) && event.getAction() == Action.PHYSICAL) {
            event.setCancelled(true);
        }
    }
}
