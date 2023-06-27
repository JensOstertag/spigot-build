package de.korzhorz.build.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import de.korzhorz.build.Main;
import de.korzhorz.build.configs.ConfigFiles;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GitHubUpdater {
    private static final String user = "JensOstertag";
    private static final String repository = "spigot-build";
    private static String latestVersion = null;
    private static final String currentVersion = JavaPlugin.getPlugin(Main.class).getDescription().getVersion();

    public static boolean updateAvailable() {
        GitHubUpdater.checkUpdates();
        if(latestVersion == null) {
            return false;
        }
        
        return !(currentVersion.equals(latestVersion));
    }
    
    public static void checkUpdates() {
        try {
            Date lastChecked = new SimpleDateFormat("yyyy-MM-dd").parse(ConfigFiles.updater.getString("last-checked"));

            if(lastChecked.after(new Date(System.currentTimeMillis() - 86400000L))) {
                latestVersion = ConfigFiles.updater.getString("latest");
                return;
            }
        } catch(ParseException e) {
            throw new RuntimeException(e);
        }
        
        try {
            String url = "https://api.github.com/repos/" + user + "/" + repository + "/releases";

            try(InputStream inputStream = new URL(url).openStream()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                int cp;
                while((cp = bufferedReader.read()) != -1) {
                    stringBuilder.append((char) cp);
                }
                String json = stringBuilder.toString();
                JsonArray jsonObject = new Gson().fromJson(json, JsonArray.class);
                latestVersion = jsonObject.get(0).getAsJsonObject().get("tag_name").getAsString();
                
                ConfigFiles.updater.set("latest", latestVersion);
                ConfigFiles.updater.set("last-checked", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                ConfigFiles.updater.save();
            } catch(FileNotFoundException e) {
                JavaPlugin.getPlugin(Main.class).getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&9GitHub&7] &cUpdate Checker unavailable"));
                latestVersion = ConfigFiles.updater.getString("latest");
            }
        } catch(IOException e) {
            JavaPlugin.getPlugin(Main.class).getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&9GitHub&7] &cUpdate Checker unavailable"));
            latestVersion = ConfigFiles.updater.getString("latest");
        }
    }
}
