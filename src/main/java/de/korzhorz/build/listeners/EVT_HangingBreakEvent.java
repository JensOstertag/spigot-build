package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import de.korzhorz.build.handlers.ProtectionHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class EVT_HangingBreakEvent implements Listener {
    // This Event has a LOWEST Priority because it should be the Default Handler, but other Plugins might want to handle it differently
    @EventHandler(priority = EventPriority.LOWEST)
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
        Entity entity = event.getEntity();

        if(!(ProtectionHandler.isProtected(entity.getWorld().getName()))) {
            return;
        }

        if(!(event.getRemover() instanceof Player)) {
            event.setCancelled(true);
        }
        
        Player player = (Player) event.getRemover();
        
        if(player == null || !(Data.inventories.containsKey(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}
