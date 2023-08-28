package de.korzhorz.build;

import de.korzhorz.build.configs.ConfigFiles;
import de.korzhorz.build.util.InventoryData;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Data {
    public static boolean buildRestricted = ConfigFiles.config.getBoolean("commands.build.enabled");
    public static HashMap<UUID, InventoryData> inventories = new HashMap<UUID, InventoryData>();
    public static ArrayList<Block> fertilizedBlocks = new ArrayList<>();
}
