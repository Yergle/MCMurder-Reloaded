package me.fudged.mcm;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.fudged.mcm.arena.ArenaManager;
import me.fudged.mcm.commands.CommandManager;
import me.fudged.mcm.listener.BukkitEvents;
import me.fudged.mcm.listener.MurderArenaEvents;
import me.fudged.mcm.listener.MurderPlayerEvents;
import me.fudged.mcm.storage.MurderFile;
import me.fudged.mcm.storage.PlayerData;

public class MCMurder extends JavaPlugin {
	
	private static MCMurder instance;
	
	private MurderFile arenaFile;
	
	private ArenaManager arenaManager;
	private PlayerData playerData;
	private CommandManager commandManager;
	
	public void onEnable(){
		instance = this;
		
		loadFiles();
		
		arenaManager = new ArenaManager();
		playerData = new PlayerData();
		
		registerEvents();
		setupCommands();
	}
	
	public void registerEvents(){
		PluginManager manager = getServer().getPluginManager();
		
		manager.registerEvents(new BukkitEvents(), this);
		manager.registerEvents(new MurderArenaEvents(), this);
		manager.registerEvents(new MurderPlayerEvents(), this);
	}
	
	public void loadFiles(){
		saveDefaultConfig();
		
		arenaFile = new MurderFile("arenas");
	}
	
	public void setupCommands(){
		commandManager = new CommandManager(); 
		
		getCommand("murder").setExecutor(commandManager);
		getCommand("mcmadmin").setExecutor(commandManager);
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
	
	public MurderFile getArenaFile(){ // "arenas.yml"
		return arenaFile;
	}

}
