package me.fudged.mcm.commands;

import org.bukkit.entity.Player;

public abstract class AdminCommand {
	
public abstract void onCommand(Player player, String[] args);
	
	private String usage, message;
	
	public AdminCommand(String usage, String message){
		this.usage = usage;
		this.message = message;
	}
	
	public final String getMessage(){
		return message;
	}
	
	public final String getUsage(){
		return usage;
	}
	

}
