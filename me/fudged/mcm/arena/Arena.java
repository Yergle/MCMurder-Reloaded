package me.fudged.mcm.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;

import me.fudged.mcm.storage.MurderConfig;

public class Arena {
	
	private String name;
	private List<UUID> players;
	private Location lobbySpawn, arenaSpawn;
	private Integer maxPlayers = MurderConfig.MAXPLAYERS;
	private UUID murderer, detective;
	private GameState gameState;
	
	public enum GameState {
		LOBBY, COUNTING_DOWN, STARTED, DISABLED;
	}

	public Arena(String name) { //  Used for creating arena from storage file arena.yml
		this.name = name;
		this.players = new ArrayList<>();
	//	this.lobbySpawn = Grab from config
	//	this.arenaSpawn = Grab from config
		this.gameState = GameState.LOBBY;
	}
	
	public Arena(String name, Location location){ // Used for creating arena in game based on player location
		this.name = name;
		this.players = new ArrayList<>();
		this.lobbySpawn = location;
		this.arenaSpawn = location;
		this.gameState = GameState.LOBBY;
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
	
	public List<UUID> getPlayers(){
		return players;
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
	
}
