package de.korzhorz.build.listeners;

import de.korzhorz.build.Data;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EVT_CropFertilizeEvent implements Listener {
    // This Event has a LOWEST Priority because it should be the Default Handler, but other Plugins might want to handle it differently
    @EventHandler(priority = EventPriority.LOWEST)
    public void onFertilize(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!(Data.inventories.containsKey(player.getUniqueId()))) {
            return;
        }

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getBlockData() instanceof Ageable) {
            Block fertilizedBlock = event.getClickedBlock();
            Data.fertilizedBlocks.add(fertilizedBlock);
            event.setCancelled(false);
        }
    }
}
