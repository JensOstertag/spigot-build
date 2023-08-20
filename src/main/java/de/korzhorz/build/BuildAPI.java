package de.korzhorz.build;

import de.korzhorz.build.configs.Messages;
import de.korzhorz.build.handlers.InventoryHandler;
import de.korzhorz.build.util.ColorTranslator;
import de.korzhorz.build.util.InventoryData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class BuildAPI {
    private static final Messages messages = new Messages();

    /**
     * Check if a player is in build mode
     * @param player Player that should be checked
     * @return true if player is in build mode, false if not
     */
    public static boolean isInBuildMode(Player player) {
        return Data.inventories.containsKey(player.getUniqueId());
    }

    /**
     * Set the build mode of a player
     * @param player Player whose build mode should be set
     * @param enable true to enable build mode, false to disable
     */
    public static void setBuildMode(Player player, boolean enable) {
        if((enable && BuildAPI.isInBuildMode(player)) || (!enable && !BuildAPI.isInBuildMode(player))) {
            return;
        }

        if(enable) {
            InventoryData inventoryData = new InventoryData(player);
            Data.inventories.put(player.getUniqueId(), inventoryData);

            new InventoryHandler().clearInventory(player);
            player.setGameMode(GameMode.CREATIVE);

            player.sendMessage(ColorTranslator.translate(BuildAPI.messages.get("prefix") + "&r " + BuildAPI.messages.get("commands.build.enable")));
        } else {
            InventoryData inventoryData = Data.inventories.get(player.getUniqueId());
            Data.inventories.remove(player.getUniqueId());
            inventoryData.restore();

            player.sendMessage(ColorTranslator.translate(BuildAPI.messages.get("prefix") + "&r " + BuildAPI.messages.get("commands.build.disable")));
        }
    }
}
