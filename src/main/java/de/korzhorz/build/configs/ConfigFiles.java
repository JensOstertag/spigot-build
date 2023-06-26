package de.korzhorz.build.configs;

import de.korzhorz.build.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFiles {
    public static ConfigFile config;
    public static ConfigFile messages;
    
    public static void loadFiles(Main plugin) {
        config = new ConfigFile("config.yml");
        messages = new ConfigFile("messages.yml");
    }
    
    public static void initFileContents() {
        config.setDefault("commands.build.enabled", true);
        
        // Messages
        messages.setDefault("prefix", "&6&bBuild &8»");
        messages.setDefault("commands.errors.no-player", "&cDu musst ein Spieler sein um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.no-permission", "&cDu hast keine Rechte um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.bad-usage", "&cBenutze: &7%usage%");
        messages.setDefault("commands.errors.save-failed", "&cDie Änderungen konnten nicht gespeichert werden.");
        messages.setDefault("commands.build.enable", "&7Der &6Baumodus &7wurde &aaktiviert&7.");
        messages.setDefault("commands.build.disable", "&7Der &6Baumodus &7wurde &cdeaktiviert&7.");
        messages.setDefault("commands.build.restricted", "&cDu darfst den Baumodus nicht aktivieren.");
        
        messages.setDefault("commands.restrict-build.enable", "&7Der &6Build-Command &7wurde &aaktiviert&7.");
        messages.setDefault("commands.restrict-build.disable", "&7Der &6Build-Command &7wurde &cdeaktiviert&7.");
    }
}
