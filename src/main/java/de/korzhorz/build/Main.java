package de.korzhorz.build;

import de.korzhorz.build.commands.CMD_Build;
import de.korzhorz.build.commands.CMD_RestrictBuild;
import de.korzhorz.build.configs.ConfigFiles;
import de.korzhorz.build.configs.Messages;
import de.korzhorz.build.listeners.*;
import de.korzhorz.build.util.ColorTranslator;
import de.korzhorz.build.util.GitHubUpdater;
import de.korzhorz.build.util.InventoryData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.stream.Collectors;

public final class Main extends JavaPlugin {
    Messages messages = new Messages();

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &7Enabling"));
        
        this.getDataFolder().mkdir();
        
        // Configuration Files
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &7Loading files"));
        ConfigFiles.initFileContents();
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &aFiles loaded"));
        
        // Commands
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &7Loading commands"));
        loadCommands();
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &aCommands loaded"));
        
        // Events
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &7Loading events"));
        loadEvents();
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &aEvents loaded"));
        
        // Update Checker
        if(GitHubUpdater.updateAvailable()) {
            this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7]"));
            this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &9A new update for this plugin is available"));
            this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7]"));
        }
        
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &aPlugin enabled &7- Version: &6v" + this.getDescription().getVersion()));
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Build&7] &aDeveloped by &6KorzHorz"));
    }

    @Override
    public void onDisable() {
        for(Player buildPlayer : Data.inventories.keySet().stream().map(Bukkit::getPlayer).collect(Collectors.toList())) {
            InventoryData inventoryData = Data.inventories.get(buildPlayer.getUniqueId());
            Data.inventories.remove(buildPlayer.getUniqueId());
            inventoryData.restore();
            
            buildPlayer.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.build.disable")));
        }
    }
    
    public void loadCommands() {
        Objects.requireNonNull(this.getCommand("build")).setExecutor(new CMD_Build());
        Objects.requireNonNull(this.getCommand("restrictbuild")).setExecutor(new CMD_RestrictBuild());
    }
    
    public void loadEvents() {
        Bukkit.getPluginManager().registerEvents(new EVT_PlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_BlockBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_BlockPlaceEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_HangingBreakEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_HangingPlaceEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_SignChangeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_PlayerInteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_InteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_BlockBurnEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_BlockExplodeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_BlockGrowEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_CropFertilizeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_BlockSpreadEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_LeavesDecayEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_EntityExplodeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_EntityChangeBlockEvent(), this);
    }
}
