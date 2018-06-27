package me.fudged.mcm.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.fudged.mcm.MCMurder;
import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.events.PlayerLeaveArenaEvent;

public class BukkitEvents implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(MCMurder.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId()) != null){ // Checks if player is in a game
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(MCMurder.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId()) != null){ // Checks if a player is in a game
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Arena arena = MCMurder.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId());
		if(arena != null){
			Bukkit.getServer().getPluginManager().callEvent(new PlayerLeaveArenaEvent(event.getPlayer(), arena));
		}
	}
	

}
