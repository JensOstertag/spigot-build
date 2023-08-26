package de.korzhorz.build.util;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryData {
    private final Player player;
    private final ItemStack[] inventory;
    private final ItemStack[] armor;
    private final GameMode gameMode;
    public final int level;
    public final float exp;
    
    public InventoryData(Player player) {
        this.player = player;
        this.inventory = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();
        this.gameMode = player.getGameMode();
        this.level = player.getLevel();
        this.exp = player.getExp();
    }
    
    public void restore() {
        this.player.getInventory().setContents(this.inventory);
        this.player.getInventory().setArmorContents(this.armor);
        this.player.setGameMode(this.gameMode);
        this.player.setLevel(this.level);
        this.player.setExp(this.exp);
    }
}
