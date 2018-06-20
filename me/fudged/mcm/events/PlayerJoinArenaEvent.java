package me.fudged.mcm.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.fudged.mcm.arena.Arena;

public class PlayerJoinArenaEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	private Arena arena;
	private Player player;
	private boolean isCancelled;

	public PlayerJoinArenaEvent(Arena arena, Player player){
		this.arena = arena;
		this.player = player;
		
		this.isCancelled = false;
	}
	
	public Arena getArena(){
		return arena;
	}
	
	public Player getPlayer(){
		return player;
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
