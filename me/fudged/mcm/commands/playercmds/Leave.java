package me.fudged.mcm.commands.playercmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.commands.MurderCommand;
import me.fudged.mcm.events.PlayerLeaveArenaEvent;
import me.fudged.mcm.storage.MurderConfig;

public class Leave extends MurderCommand {

	public Leave() {
		super("leave", "Leave a game");
	}
	
	public void onCommand(Player player, String[] args){
		Arena arena = MCMurder.getInst().getArenaManager().getArena(player.getUniqueId());
		
		if(arena == null){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " You are not currently in a game");
			return;
		}
		
		Bukkit.getServer().getPluginManager().callEvent(new PlayerLeaveArenaEvent(player, arena));
		
	}

}
