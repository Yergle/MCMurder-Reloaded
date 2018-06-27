package me.fudged.mcm.commands;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.fudged.mcm.commands.admincmds.Create;
import me.fudged.mcm.commands.playercmds.Join;
import me.fudged.mcm.commands.playercmds.Leave;
import me.fudged.mcm.storage.MurderConfig;


public class CommandManager implements CommandExecutor {

	public ArrayList<MurderCommand> playerCommands = new ArrayList<MurderCommand>();
	public ArrayList<AdminCommand> adminCommands = new ArrayList<AdminCommand>();
	
	public CommandManager(){
		playerCommands.add(new Join());
		playerCommands.add(new Leave());
		
		adminCommands.add(new Create());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args){
		if(!(sender instanceof Player)){
			sender.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " This commands can only be sent in game");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("murder")){ // Player command management
			if(args.length == 0){
				for(MurderCommand mc : playerCommands){
					p.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " /murder " + mc.getUsage() + " " + MurderConfig.SECONDARY + mc.getMessage());
				}
				return true;
			}
			
			MurderCommand playerCommand = getPlayerCommand(args[0]);
			
			if(playerCommand == null){
				return true;
			}
			
			Vector<String> a = new Vector<String>(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			playerCommand.onCommand(p, args);
			
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("mcmadmin")){ // Admin command management 
			if(args.length == 0){
				for(AdminCommand ac : adminCommands){
					p.sendMessage(MurderConfig.PREFIX + MurderConfig.PRIMARY + " /mcmadmin " + ac.getUsage() + " " + MurderConfig.SECONDARY + ac.getMessage());
				}
				return true;
			}
			
			AdminCommand adminCommand = getAdminCommand(args[0]);
			
			if(adminCommand == null){
				return true;
			}
			
			Vector<String> a = new Vector<String>(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			adminCommand.onCommand(p, args);
			
			return true;
		}
		
		return false;
	}

	public MurderCommand getPlayerCommand(String name){
		 for(MurderCommand cmd : playerCommands){
			 if(cmd.getClass().getSimpleName().equalsIgnoreCase(name)){
				 return cmd;
			 }
		 }
		 return null;
	}
	
	public AdminCommand getAdminCommand(String name){
		for(AdminCommand cmd : adminCommands){
			if(cmd.getClass().getSimpleName().equalsIgnoreCase(name)){
				return cmd;
			}
		}
		return null;
	}
	
}