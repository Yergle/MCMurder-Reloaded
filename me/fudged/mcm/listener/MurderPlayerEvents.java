package me.fudged.mcm.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.events.ArenaEndEvent;
import me.fudged.mcm.events.PlayerJoinArenaEvent;
import me.fudged.mcm.events.PlayerLeaveArenaEvent;
import me.fudged.mcm.storage.MurderConfig;

public class MurderPlayerEvents implements Listener {

	@EventHandler
	public void onPlayerJoinArena(PlayerJoinArenaEvent event){
		Arena arena = event.getArena();
		Player player = event.getPlayer();
		
		if(event.isCancelled()){
			return;
		}
		
		arena.getActivePlayers().add(player.getUniqueId());
		arena.getAllPlayers().add(player.getUniqueId());
		MCMurder.getInst().getPlayerData().savePlayerData(player);
		
		for(UUID uuid : arena.getActivePlayers()){
			Bukkit.getServer().getPlayer(uuid).sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " " + player.getName() + 
					MurderConfig.SECONDARY + " has joined the arena " + MurderConfig.PRIMARY + "(" + arena.getActivePlayers().size() + "/" + arena.getMaxPlayers() + ")");
		}
		
		player.teleport(arena.getLobbySpawn());
		player.getInventory().clear();
		
	}
	
	@EventHandler
	public void onPlayerLeaveArena(PlayerLeaveArenaEvent event){
		Arena arena = event.getArena();
		Player player = event.getPlayer();
		
		if(event.isCancelled()){
			return;
		}
		
		arena.getActivePlayers().remove(player.getUniqueId());
		arena.getAllPlayers().remove(player.getUniqueId());
		arena.sendMessage("A player has left the game. " + MurderConfig.SECONDARY + arena.getActivePlayers().size() + MurderConfig.PREFIX + " players remain");
		
		if(MCMurder.getInst().getPlayerData().hasPlayerData(player)){
			MCMurder.getInst().getPlayerData().restorePlayerData(player);
		} else {
			Bukkit.getLogger().info("An error has occured with restoring the inventory of user " + player.getUniqueId().toString());
			player.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " An error has occured restoring your inventory. Contact an admin as soon as possible");
		}
		
		if(arena.getActivePlayers().isEmpty()){
			Bukkit.getServer().getPluginManager().callEvent(new ArenaEndEvent(arena));
		}
		
	}

}
