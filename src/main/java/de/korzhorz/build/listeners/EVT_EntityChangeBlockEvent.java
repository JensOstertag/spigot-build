package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import de.korzhorz.build.handlers.ProtectionHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EVT_EntityChangeBlockEvent implements Listener {
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        Block block = event.getBlock();

        if(!(ProtectionHandler.isProtected(block.getWorld().getName()))) {
            return;
        }

        if(!(event.getEntity() instanceof Player)) {
            event.setCancelled(true);
        }
        
        Player player = (Player) event.getEntity();
        
        if(!(Data.inventories.containsKey(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}
