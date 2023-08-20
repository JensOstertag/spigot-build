package de.korzhorz.build.configs;

import de.korzhorz.build.Main;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConfigFiles {
    public static ConfigFile config = new ConfigFile("config.yml");
    public static ConfigFile messages = new ConfigFile("messages.yml");
    public static ConfigFile updater = new ConfigFile("updater.yml");
    
    public static void initFileContents() {
        // Config
        config.setDefault("commands.build.enabled", true);
        config.setDefault("overrides.protected", new ArrayList<>());
        config.setDefault("overrides.unprotected", new ArrayList<>());

        config.save();

        // Messages
        messages.setDefault("prefix", "&6&lBuild &8»");
        messages.setDefault("commands.errors.no-player", "&cDu musst ein Spieler sein um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.no-permission", "&cDu hast keine Rechte um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.bad-usage", "&cBenutze: &7%usage%");
        messages.setDefault("commands.errors.save-failed", "&cDie Änderungen konnten nicht gespeichert werden.");
        messages.setDefault("commands.build.enable", "&7Der &6Baumodus &7wurde &aaktiviert&7.");
        messages.setDefault("commands.build.disable", "&7Der &6Baumodus &7wurde &cdeaktiviert&7.");
        messages.setDefault("commands.build.restricted", "&cDu darfst den Baumodus nicht aktivieren.");

        messages.setDefault("commands.restrict-build.enable", "&7Der &6Build-Command &7wurde &aaktiviert&7.");
        messages.setDefault("commands.restrict-build.disable", "&7Der &6Build-Command &7wurde &cdeaktiviert&7.");

        messages.save();

        // Updater
        updater.setDefault("latest", JavaPlugin.getPlugin(Main.class).getDescription().getVersion());
        updater.setDefault("last-checked", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        updater.save();
    }
}
