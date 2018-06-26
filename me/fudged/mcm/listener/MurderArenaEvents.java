package me.fudged.mcm.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.arena.Arena.GameState;
import me.fudged.mcm.events.ArenaCreatedEvent;
import me.fudged.mcm.events.ArenaEndEvent;
import me.fudged.mcm.storage.MurderConfig;

public class MurderArenaEvents implements Listener {
	
	@EventHandler
	public void onArenaCreation(ArenaCreatedEvent event){
		if(event.isCancelled()){
			return;
		}
		
		Location loc = event.getPlayer().getLocation();
		
		MCMurder.getInst().getArenaManager().createArena(event.getName(), loc); // Create the arena in game
		
		//saveLocation("arenas." + event.getName() + ".lobbySpawn", loc); // Save lobby spawn to where player is standing
		//saveLocation("arenas." + event.getName() + ".arenaSpawn", loc); // Save arena spawn to where player is standing
		
		event.getPlayer().sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " A new arena named " 
				+ MurderConfig.SECONDARY + event.getName() + MurderConfig.PRIMARY + " has been created");
		
	}

	@EventHandler
	public void onArenaEnd(ArenaEndEvent event){
		Arena arena = event.getArena();
		
		for(UUID uuid : arena.getAllPlayers()){ // Let all players that were in the game know that the arena has ended
			Bukkit.getServer().getPlayer(uuid).sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " " + arena.getName() + MurderConfig.SECONDARY 
					+ " has come to an end");
		}
		
		for(UUID uuid : arena.getActivePlayers()){ // Restore player's inventory/armor/xp/location back to normal before they joined the arena
			if(MCMurder.getInst().getPlayerData().hasPlayerData(getPlayer(uuid))){
				MCMurder.getInst().getPlayerData().restorePlayerData(getPlayer(uuid));
			} else {
				Bukkit.getLogger().info("An error has occured with restoring the inventory of user " + uuid.toString());
			}
		}
		
		arena.setGameState(GameState.LOBBY); // Open arena back up to all players to join
		
	}
	
	public Player getPlayer(UUID uuid){
		return Bukkit.getServer().getPlayer(uuid);
	}
	
	public void saveLocation(String path, Location location){
		MCMurder.getInst().getArenaFile().getConfig().set(path, location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() +
				";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch());
	}

}
