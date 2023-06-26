package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;

public class EVT_HangingBreakEvent implements Listener {
    // This Event has a LOWEST Priority because it should be the Default Handler, but other Plugins might want to handle it differently
    @EventHandler(priority = EventPriority.LOWEST)
    public void onHangingBreak(HangingBreakEvent event) {
        if(event.getCause() != HangingBreakEvent.RemoveCause.ENTITY) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onHangingBreakByEntity(HangingBreakByEntityEvent event) {
        if(!(event.getRemover() instanceof Player)) {
            event.setCancelled(true);
        }
        
        Player player = (Player) event.getRemover();
        
        if(player == null || !(Data.inventories.containsKey(player.getUniqueId()))) {
            event.setCancelled(true);
        }
    }
}
