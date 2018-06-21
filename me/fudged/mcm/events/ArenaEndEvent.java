package me.fudged.mcm.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.fudged.mcm.arena.Arena;

public class ArenaEndEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	
	private Arena arena;
	
	private boolean isCancelled;
	
	public ArenaEndEvent(Arena arena) {
		this.arena = arena;
		
		this.isCancelled = false;
	}
	
	public Arena getArena(){
		return arena;
	}
	
	public boolean isCancelled(){
		return isCancelled;
	}
	
	public void setCancelled(boolean boo){
		this.isCancelled = boo;
	}
	
	public HandlerList getHandlers(){
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}

}
