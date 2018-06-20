package me.fudged.mcm.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.events.PlayerJoinArenaEvent;

public class McmEventListener implements Listener {

	@EventHandler
	public void onPlayerJoinArena(PlayerJoinArenaEvent event){
		Arena arena = event.getArena();
		Player player = event.getPlayer();
		//Check if theres enough space for the player to join
		if(arena.getPlayers().size() >= arena.getMaxPlayers()){ // Arena is full
			player.sendMessage("Arena is full please try again later...");
			
			event.setCancelled(true);
			return;
		}
		
		arena.getPlayers().add(player.getUniqueId());
		
		for(UUID uuid : arena.getPlayers()){
			Bukkit.getServer().getPlayer(uuid).sendMessage(player.getName() + " has joined the arena (" + arena.getPlayers().size() + "/" + arena.getMaxPlayers() + ")");
		}
		
	}

}
