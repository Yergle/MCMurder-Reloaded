package me.fudged.mcm;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.fudged.mcm.arena.ArenaManager;
import me.fudged.mcm.listener.MapProtection;
import me.fudged.mcm.storage.PlayerData;

public class MCMurder extends JavaPlugin {
	
	private static MCMurder instance;
	private ArenaManager arenaManager;
	private PlayerData playerData;
	
	public void onEnable(){
		instance = this;
		
		arenaManager = new ArenaManager();
		
		registerEvents();
	}
	
	public void registerEvents(){
		PluginManager manager = getServer().getPluginManager();
		
		manager.registerEvents(new MapProtection(), this);
	}
	
	public static MCMurder getInst(){
		return instance;
	}
	
	public ArenaManager getArenaManager(){
		return arenaManager;
	}
	
	public PlayerData getPlayerData(){
		return playerData;
	}

}
