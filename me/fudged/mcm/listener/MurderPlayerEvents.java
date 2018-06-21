package me.fudged.mcm.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.events.PlayerJoinArenaEvent;
import me.fudged.mcm.storage.MurderConfig;

public class MurderPlayerEvents implements Listener {

	@EventHandler
	public void onPlayerJoinArena(PlayerJoinArenaEvent event){
		Arena arena = event.getArena();
		Player player = event.getPlayer();
		
		if(event.isCancelled()){
			return;
		}
		
		//Check if theres enough space for the player to join
		if(arena.getActivePlayers().size() >= arena.getMaxPlayers()){ // Arena is full
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " " + arena.getName() + MurderConfig.SECONDARY + " is currently full" );
			
			event.setCancelled(true);
			return;
		}
		
		arena.getActivePlayers().add(player.getUniqueId());
		arena.getAllPlayers().add(player.getUniqueId());
		MCMurder.getInst().getPlayerData().savePlayerData(player);
		
		for(UUID uuid : arena.getActivePlayers()){
			Bukkit.getServer().getPlayer(uuid).sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " " + player.getName() + 
					MurderConfig.SECONDARY + " has joined the arena " + MurderConfig.PRIMARY + "(" + arena.getActivePlayers().size() + "/" + arena.getMaxPlayers() + ")");
		}
		
	}

}
