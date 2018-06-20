package me.fudged.mcm;

import org.bukkit.plugin.java.JavaPlugin;

import me.fudged.mcm.arena.ArenaManager;

public class MCMurder extends JavaPlugin {
	
	private static MCMurder instance;
	private ArenaManager arenaManager;
	
	public void onEnable(){
		instance = this;
		
		arenaManager = new ArenaManager();
		
		
		
	}
	
	public static MCMurder getInst(){
		return instance;
	}
	
	public ArenaManager getArenaManager(){
		return arenaManager;
	}

}
