package me.fudged.mcm.nms;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

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
	
	public static void sendPacketToAll(Packet<?> packet) {
		for (Player online : Bukkit.getOnlinePlayers()) {
			PacketHandler.sendPacket(online, packet);
		}
	}
}
