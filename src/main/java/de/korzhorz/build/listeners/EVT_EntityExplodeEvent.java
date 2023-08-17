package de.korzhorz.build.listeners;

import de.korzhorz.build.handlers.ProtectionHandler;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EVT_EntityExplodeEvent implements Listener {
    // This Event has a LOWEST Priority because it should be the Default Handler, but other Plugins might want to handle it differently
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        if(!(ProtectionHandler.isProtected(entity.getWorld().getName()))) {
            return;
        }

        event.setCancelled(true);
    }
}
