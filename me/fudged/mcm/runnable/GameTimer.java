package me.fudged.mcm.runnable;

import org.bukkit.scheduler.BukkitRunnable;

import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.scoreboard.Board;
import me.fudged.mcm.storage.MurderConfig;

public class GameTimer extends BukkitRunnable {

	private Arena a;
	private Board b;
	private int timeLeft;

	public GameTimer(Arena arena){
		this.a = arena;
		timeLeft = MurderConfig.GAMETIME;

		if(MurderConfig.USEBOARD){
			b = new Board(a);
		}
	}

	@Override
	public void run(){
		if(timeLeft == 0){
			//a.stop();
			this.cancel();
		}

		timeLeft--;

		if(MurderConfig.USEBOARD){
			b.tick(formatInt(timeLeft));
		}

	}
	
	public String formatInt(int time) {
	    return String.format("%02d:%02d", time / 60, time % 60);
	}
	
}
