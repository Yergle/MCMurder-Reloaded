package me.fudged.mcm.scoreboard;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import me.fudged.mcm.arena.Arena;
import me.fudged.mcm.storage.MurderConfig;


public class Board {
	
	private Scoreboard board;
	private Objective obj;
	private Arena arena;
	private Score role, timeLeft, playersLeft, arenaName, spacer, spacer1, spacer2;
	
	public Board(Arena arena){
		this.arena = arena;
		
		board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		obj = board.registerNewObjective(arena.getName(), "dummy");
		obj.setDisplayName(MurderConfig.BOARDHEADER);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		spacer = obj.getScore(ChatColor.RED + "");
		spacer1 = obj.getScore(ChatColor.RED + " ");
		spacer2 = obj.getScore(ChatColor.RED + "  ");
		
		this.loadScoreboard();
		
	}
	
	public void loadScoreboard(){
		for(UUID uu : arena.getActivePlayers()){
			role = obj.getScore(MurderConfig.PRIMARY + "Role: " + MurderConfig.SECONDARY + arena.getRole(uu));
			timeLeft = obj.getScore(MurderConfig.PRIMARY + "Time Remaining: " + MurderConfig.GAMETIME);
			arenaName = obj.getScore(MurderConfig.PRIMARY + "Arena: " + MurderConfig.SECONDARY + arena.getName());
			playersLeft = obj.getScore(MurderConfig.PRIMARY + "Players Alive: " + MurderConfig.SECONDARY + arena.getActivePlayers().size());
			
			role.setScore(1);
			spacer.setScore(2);
			arenaName.setScore(3);
			spacer1.setScore(4);
			playersLeft.setScore(5);
			spacer2.setScore(6);
			timeLeft.setScore(7);
			
			Bukkit.getServer().getPlayer(uu).setScoreboard(board);
		}
	}
	
	public void tick(String time){
		for(UUID uu : arena.getActivePlayers()){
			role = obj.getScore(MurderConfig.PRIMARY + "Role: " + MurderConfig.SECONDARY + arena.getRole(uu));
			playersLeft = obj.getScore(MurderConfig.PRIMARY + "Players Alive: " + MurderConfig.SECONDARY + arena.getActivePlayers().size());
			timeLeft = obj.getScore(MurderConfig.PRIMARY + "Time Remaining: " + MurderConfig.SECONDARY + time);
			
			for(String s : board.getEntries()){
				board.resetScores(s);
			}
			
			role.setScore(1);
			spacer.setScore(2);
			arenaName.setScore(3);
			spacer1.setScore(4);
			playersLeft.setScore(5);
			spacer2.setScore(6);
			timeLeft.setScore(7);
			
			Bukkit.getServer().getPlayer(uu).setScoreboard(board);
		}
	}
	
	public Scoreboard getScoreboard(){
		return board;
	}
	
}
