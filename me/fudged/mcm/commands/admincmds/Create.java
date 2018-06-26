package me.fudged.mcm.commands.admincmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.commands.AdminCommand;
import me.fudged.mcm.events.ArenaCreatedEvent;
import me.fudged.mcm.storage.MurderConfig;

public class Create extends AdminCommand {

	public void onCommand(Player player, String[] args){
		if(!(player.hasPermission("murder.admin.create"))){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " You do not have permission to do this");
			return;
		}
		
		if(MCMurder.getInst().getArenaManager().getArena(args[0]) != null){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " An arena with the name " + MurderConfig.SECONDARY + args[0]
					+ MurderConfig.PRIMARY + " already exists");
			return;
		}
		
		Bukkit.getServer().getPluginManager().callEvent(new ArenaCreatedEvent(player, args[0]));
		
	}
	
	public Create() {
		super("create <name>", "Create a new arena");
	}

}
