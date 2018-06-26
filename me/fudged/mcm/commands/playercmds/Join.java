package me.fudged.mcm.commands.playercmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.arena.Arena.GameState;
import me.fudged.mcm.commands.MurderCommand;
import me.fudged.mcm.events.PlayerJoinArenaEvent;
import me.fudged.mcm.storage.MurderConfig;

public class Join extends MurderCommand {

	public Join() {
		super("join <arena>", "Join an arena");
	}

	@Override 
	public void onCommand(Player player, String[] args) {
		if(MCMurder.getInst().getArenaManager().getArena(player.getUniqueId()) != null){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " You are already in a game. You must leave before joining another");
			return;
		}
		
		if(!(player.hasPermission("murder.arena.join"))){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " You do not have permission to do this");
			return;
		}
		
		if(args.length == 0){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " Please specify the arena you would like to join");
			return;
		}
		
		Arena arena = MCMurder.getInst().getArenaManager().getArena(args[0]);
		
		if(arena == null){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " No arena exists with the name " + MurderConfig.SECONDARY + args[0]);
			return;
		}
		
		if(arena.getGameState() == GameState.STARTED){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " This arena is already in game");
			return;
		}
		
		if(arena.getGameState() == GameState.DISABLED){
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " This arena is currently disabled");
			return;
		}
		
		if(arena.getActivePlayers().size() >= arena.getMaxPlayers() && !(player.hasPermission("murder.arena.join.full"))){ // Arena is full
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " This arena is currently full" );
			return;
		}
		
		Bukkit.getServer().getPluginManager().callEvent(new PlayerJoinArenaEvent(arena, player));
		
	}

}
