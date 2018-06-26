package me.fudged.mcm.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ArenaCreatedEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	private String name;
	private Player player;
	
	private boolean isCancelled;

	public ArenaCreatedEvent(Player player, String name) {
		this.name = name;
		this.player = player;
		this.isCancelled = false;
	}
	
	public String getName(){
		return name;
	}
	
	public Player getPlayer(){
		return player;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean boo) {
		this.isCancelled = boo;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}

}
