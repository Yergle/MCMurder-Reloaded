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
	
	public void createArena(String name, Location location){
		arenas.add(new Arena(name, location));
	}
	
	public void createArena(String name){
		arenas.add(new Arena(name));
	}
	
	public Arena getArena(String name){
		for(Arena arena : arenas){
			if(arena.getName().equalsIgnoreCase(name)){
				return arena;
			}
		}
		return null;
	}
	
	public Arena getArena(UUID uuid){
		for(Arena arena : arenas){
			if(arena.getPlayers().contains(uuid)){
				return arena;
			}
		}
		return null;
	}
	
	public List<Arena> getArenas(){
		return arenas;
	}

}
