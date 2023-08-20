package de.korzhorz.build.commands;

import de.korzhorz.build.Data;
import de.korzhorz.build.configs.Messages;
import de.korzhorz.build.handlers.InventoryHandler;
import de.korzhorz.build.util.ColorTranslator;
import de.korzhorz.build.util.InventoryData;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Build implements CommandExecutor {
    Messages messages = new Messages();
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.errors.no-player")));
            return false;
        }
        
        Player player = (Player) sender;
        
        if(!(player.hasPermission("build.toggle"))) {
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.errors.no-permission")));
            return false;
        }
        
        if(args.length != 0) {
            String message = messages.get("commands.errors.bad-usage");
            message = message.replaceAll("%usage%", cmd.getUsage());
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));
            return false;
        }
        
        if(Data.buildRestricted && !(player.hasPermission("build.restrict"))) {
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.build.restricted")));
            return false;
        }

        if(Data.inventories.containsKey(player.getUniqueId())) {
            InventoryData inventoryData = Data.inventories.get(player.getUniqueId());
            Data.inventories.remove(player.getUniqueId());
            inventoryData.restore();

            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.build.disable")));
        } else {
            InventoryData inventoryData = new InventoryData(player);
            Data.inventories.put(player.getUniqueId(), inventoryData);

            new InventoryHandler().clearInventory(player);
            player.setGameMode(GameMode.CREATIVE);

            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.build.enable")));
        }
        
        return true;
    }
}
