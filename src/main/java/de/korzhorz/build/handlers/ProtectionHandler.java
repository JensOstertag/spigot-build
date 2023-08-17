package de.korzhorz.build.handlers;

import de.korzhorz.build.configs.ConfigFiles;

import java.util.List;

public class ProtectionHandler {
    public static boolean isProtected(String worldName) {
        List<String> protectedWorlds = ConfigFiles.config.getStringList("overrides.protected");
        List<String> unprotectedWorlds = ConfigFiles.config.getStringList("overrides.unprotected");

        if(protectedWorlds.size() == 0 && unprotectedWorlds.size() == 0) {
            // No overrides, all worlds are protected by default
            return true;
        } else if(protectedWorlds.size() != 0 && protectedWorlds.contains(worldName)) {
            // Overrides that protect among others this world
            return true;
        } else if(unprotectedWorlds.size() != 0 && !(unprotectedWorlds.contains(worldName))) {
            // Overrides that unprotect worlds, but not this one
            return true;
        } else {
            return false;
        }
    }
}
