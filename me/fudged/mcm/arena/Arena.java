package me.fudged.mcm.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.storage.MurderConfig;

public class Arena {
	
	private String name;
	private List<UUID> activePlayers, allPlayers;
	private Location lobbySpawn, arenaSpawn;
	private Integer maxPlayers = MurderConfig.MAXPLAYERS;
	private UUID murderer, detective;
	private GameState gameState;
	
	public enum GameState {
		WAITING, LOBBY, STARTED, DISABLED;
	}

	public Arena(String name) { //  Used for creating arena from storage file arena.yml
		this.name = name;
		this.activePlayers = new ArrayList<>();
		this.allPlayers = new ArrayList<>();
		this.lobbySpawn = getLocationFromStorage("arenas." + name + ".lobbySpawn");
		this.arenaSpawn = getLocationFromStorage("arenas." + name + ".arenaSpawn");
		this.gameState = GameState.WAITING;
	}
	
	public Arena(String name, Location location){ // Used for creating arena in game based on player location
		this.name = name;
		this.activePlayers = new ArrayList<>();
		this.allPlayers = new ArrayList<>();
		this.lobbySpawn = location;
		this.arenaSpawn = location;
		this.gameState = GameState.WAITING;
	}
	
	public String getName(){
		return name;
	}
	
	public GameState getGameState(){
		return gameState;
	}
	
	public void setGameState(GameState gameState){
		this.gameState = gameState;
	}
	
	public Location getLobbySpawn(){
		return lobbySpawn;
	}
	
	public void setLobbySpawn(Location location){
		this.lobbySpawn = location;
	}
	
	public Location getArenaSpawn(){
		return arenaSpawn;
	}
	
	public void setArenaSpawn(Location location){
		this.arenaSpawn = location;
	}
	
	public List<UUID> getActivePlayers(){
		return activePlayers;
	}
	
	public List<UUID> getAllPlayers(){
		return allPlayers;
	}
	
	public UUID getMurderer(){
		return murderer;
	}
	
	public UUID getDetective(){
		return detective;
	}
	
	public int getMaxPlayers(){
		return maxPlayers;
	}
	
	public String getRole(UUID uuid){
		if(this.getMurderer() == uuid){
			return "Murderer";
		}
		if(this.getDetective() == uuid){
			return "Detective";
		}
		return "Bystander";
		
	}
	
	public Location getLocationFromStorage(String path){
		String[] split = MCMurder.getInst().getArenaFile().getConfig().get(path).toString().split(";");
		
		return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]),
				Float.parseFloat(split[4]), Float.parseFloat(split[5]));
	}
	
	public void sendMessage(String message){
		for(UUID uuid : this.getActivePlayers()){
			Bukkit.getServer().getPlayer(uuid).sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " ");
		}
	}
	
}
