package de.korzhorz.build.commands;

import de.korzhorz.build.Data;
import de.korzhorz.build.configs.Messages;
import de.korzhorz.build.handlers.InventoryHandler;
import de.korzhorz.build.util.ColorTranslator;
import de.korzhorz.build.util.InventoryData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class CMD_RestrictBuild implements CommandExecutor {
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
        
        if(args.length != 1) {
            String message = messages.get("commands.errors.bad-usage");
            message = message.replaceAll("%usage%", cmd.getUsage());
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));
            return false;
        }
        
        Data.buildRestricted = Boolean.parseBoolean(args[0]);
        if(Data.buildRestricted) {
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.restrict-build.enable")));
            return true;
        }

        for(Player buildPlayer : Data.inventories.keySet().stream().map(Bukkit::getPlayer).collect(Collectors.toList())) {
            if(buildPlayer.hasPermission("build.restrict")) {
                continue;
            }

            InventoryData inventoryData = Data.inventories.get(buildPlayer.getUniqueId());
            Data.inventories.remove(buildPlayer.getUniqueId());
            inventoryData.restore();
            
            buildPlayer.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.build.disable")));
        }

        player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.restrict-build.disable")));
        
        return true;
    }
}
