package me.fudged.mcm.storage;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.fudged.mcm.MCMurder;

/**
 * @author Sjalfsvig
 **/

public class MurderFile {

	private MCMurder plugin;
	private File file;
	private FileConfiguration config;
	
	public MurderFile(String fileName) {
		this.plugin = MCMurder.getInst();
		
		if (!this.plugin.getDataFolder().exists()) {
			this.plugin.getDataFolder().mkdir();
		}
		
		this.file = new File(plugin.getDataFolder(), fileName + ".yml");
		
		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.config = YamlConfiguration.loadConfiguration(file);
		}
	}
	
	// This allows you to access all the regular config methods.
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void save() {
		try {
			this.config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}