package me.fudged.mcm.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.fudged.mcm.MCMurder;

public class MapProtection implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		if(MCMurder.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId()) != null){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event){
		if(MCMurder.getInst().getArenaManager().getArena(event.getPlayer().getUniqueId()) != null){
			event.setCancelled(true);
		}
	}
	

}
