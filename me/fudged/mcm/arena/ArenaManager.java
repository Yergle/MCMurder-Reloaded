package me.fudged.mcm.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;

public class ArenaManager {

	public ArenaManager() {
		this.loadArenas();
	}
	
	private List<Arena> arenas = new ArrayList<Arena>();
	
	public void loadArenas(){ // Load all arenas from config
		if(!arenas.isEmpty()){
			arenas.clear();
		}
		
		// Load from files
		
	}
	
	public void createArena(String name, Location location){ // Used to create an arena in game
		arenas.add(new Arena(name, location));
	}
	
	public void createArena(String name){ // Create the arena from storage
		arenas.add(new Arena(name));
	}
	
	public Arena getArena(String name){ // Find the arena by the arena's name
		for(Arena arena : arenas){
			if(arena.getName().equalsIgnoreCase(name)){
				return arena;
			}
		}
		return null;
	}
	
	public Arena getArena(UUID uuid){ // Find the arena a player is in 
		for(Arena arena : arenas){
			if(arena.getPlayers().contains(uuid)){
				return arena;
			}
		}
		return null;
	}
	
	public List<Arena> getArenas(){ // Get a list of all the arenas
		return arenas;
	}

}
