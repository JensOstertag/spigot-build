package de.korzhorz.build.listeners;

import de.korzhorz.build.handlers.ProtectionHandler;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class EVT_LeavesDecayEvent implements Listener {
    // This Event has a LOWEST Priority because it should be the Default Handler, but other Plugins might want to handle it differently
    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeavesDecay(LeavesDecayEvent event) {
        Block block = event.getBlock();

        if(!(ProtectionHandler.isProtected(block.getWorld().getName()))) {
            return;
        }

        event.setCancelled(true);
    }
}
