package me.fudged.mcm.runnable;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.arena.Arena.GameState;
import me.fudged.mcm.events.ArenaEndEvent;
import me.fudged.mcm.scoreboard.Board;
import me.fudged.mcm.storage.MurderConfig;

public class GameTimer extends BukkitRunnable {

	private Arena arena;
	private Board board;
	private int timeLeft;

	public GameTimer(Arena arena){
		this.arena = arena;
		this.timeLeft = MurderConfig.GAMETIME;

		if(MurderConfig.USEBOARD){
			this.board = new Board(arena);
		}
	}

	@Override
	public void run(){
		if(timeLeft == 0){
			Bukkit.getServer().getPluginManager().callEvent(new ArenaEndEvent(arena)); // Game ran out of time, ending arena
			
			for(Team team : board.getScoreboard().getTeams()){
				team.unregister();
			}
			
			this.cancel();
			return;
		}
		
		if(arena.getGameState() == GameState.LOBBY){ // Protection to avoid calling the ArenaEndEvent twice
			for(Team team : board.getScoreboard().getTeams()){
				team.unregister();
			}
			
			this.cancel();
			return;
		}
		

		timeLeft--;

		if(MurderConfig.USEBOARD){
			board.tick(formatInt(timeLeft));
		}

	}
	
	public String formatInt(int time) {
	    return String.format("%02d:%02d", time / 60, time % 60);
	}
	
}
