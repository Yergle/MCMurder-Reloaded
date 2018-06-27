package me.fudged.mcm.nms;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.fudged.mcm.arena.Arena;
import net.minecraft.server.v1_12_R1.Packet;

/**
 * 
 * @author Sjalfsvig
 *
 */

public class PacketHandler {
	
	public static void sendPacket(Player player, Packet<?> packet) {
		if (player != null) {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);;
		}
	}
	
	public static void sendPacket(Arena arena, Packet<?> packet) {
		for (UUID uuid : arena.getActivePlayers()) {
			if (Bukkit.getPlayer(uuid) != null) {
				sendPacket(Bukkit.getPlayer(uuid), packet);
			}
		}
	}
	
	public static void sendPacketToAll(Packet<?> packet) {
		for (Player online : Bukkit.getOnlinePlayers()) {
			PacketHandler.sendPacket(online, packet);
		}
	}
}
