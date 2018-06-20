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
	private Arena a;
	private Score role, timeLeft, playersLeft, arena, spacer, spacer1, spacer2;
	
	public Board(Arena a){
		this.a = a;
		
		board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		obj = board.registerNewObjective(a.getName(), "dummy");
		obj.setDisplayName(MurderConfig.BOARDHEADER);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		spacer = obj.getScore(ChatColor.RED + "");
		spacer1 = obj.getScore(ChatColor.RED + " ");
		spacer2 = obj.getScore(ChatColor.RED + "  ");
		
		this.loadScoreboard();
	}
	
	public void loadScoreboard(){
		for(UUID uu : a.getPlayers()){
			role = obj.getScore(MurderConfig.PRIMARY + "Role: " + MurderConfig.SECONDARY + a.getRole(uu));
			timeLeft = obj.getScore(MurderConfig.PRIMARY + "Time Remaining: " + MurderConfig.GAMETIME);
			arena = obj.getScore(MurderConfig.PRIMARY + "Arena: " + MurderConfig.SECONDARY + a.getName());
			playersLeft = obj.getScore(MurderConfig.PRIMARY + "Players Alive: " + MurderConfig.SECONDARY + a.getPlayers().size());
			
			role.setScore(1);
			spacer.setScore(2);
			arena.setScore(3);
			spacer1.setScore(4);
			playersLeft.setScore(5);
			spacer2.setScore(6);
			timeLeft.setScore(7);
			
			Bukkit.getServer().getPlayer(uu).setScoreboard(board);
		}
	}
	
	public void tick(String time){
		for(UUID uu : a.getPlayers()){
			role = obj.getScore(MurderConfig.PRIMARY + "Role: " + MurderConfig.SECONDARY + a.getRole(uu));
			playersLeft = obj.getScore(MurderConfig.PRIMARY + "Players Alive: " + MurderConfig.SECONDARY + a.getPlayers().size());
			timeLeft = obj.getScore(MurderConfig.PRIMARY + "Time Remaining: " + MurderConfig.SECONDARY + time);
			
			for(String s : board.getEntries()){
				board.resetScores(s);
			}
			
			role.setScore(1);
			spacer.setScore(2);
			arena.setScore(3);
			spacer1.setScore(4);
			playersLeft.setScore(5);
			spacer2.setScore(6);
			timeLeft.setScore(7);
			
			Bukkit.getServer().getPlayer(uu).setScoreboard(board);
		}
	}
	
}
