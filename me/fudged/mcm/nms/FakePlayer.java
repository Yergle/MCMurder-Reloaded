package me.fudged.mcm.nms;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PacketPlayOutBed;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import net.minecraft.server.v1_12_R1.WorldServer;

/**
 * 
 * @author Sjalfsvig
 *
 */

public class FakePlayer {

	EntityPlayer fakePlayer;
	
	public FakePlayer(Player player, Location location) {
		/**
		 * @param player Used for the fake player's skin and display name (display name should be invis).
		 * @param location Used for spawn location of the fake player (shouldn't matter).
		 */
		MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorldServer = ((CraftWorld) location.getWorld()).getHandle();
		
		GameProfile profile = new GameProfile(UUID.randomUUID(), player.getDisplayName());
		profile.getProperties().putAll(((CraftPlayer) player).getHandle().getProfile().getProperties()); // Copies the player's skin.
		
		fakePlayer = new EntityPlayer(nmsServer, nmsWorldServer, profile, new PlayerInteractManager(nmsWorldServer));
		fakePlayer.getDataWatcher().set(new DataWatcherObject<>(12, DataWatcherRegistry.a), (byte) 0xFF); // Loads the outer skin layer.
		fakePlayer.setLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), 0, 0);
		
		PacketPlayOutPlayerInfo packetPlayerInfo = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, fakePlayer);
		PacketPlayOutNamedEntitySpawn packetSpawnEntity = new PacketPlayOutNamedEntitySpawn(fakePlayer);
		PacketHandler.sendPacketToAll(packetPlayerInfo);
		PacketHandler.sendPacketToAll(packetSpawnEntity);
	}
	
	public void setSleeping(Location bedLocation, Location bodyLocation) {
		/**
		 * @param bedLocation The location of the bed head.
		 * @param bodyLocation Where the body should be laying down.
		 */
		fakePlayer.setCustomNameVisible(false);
		PacketPlayOutBed packetBed = new PacketPlayOutBed(fakePlayer, new BlockPosition(bedLocation.getBlockX(), bedLocation.getBlockY(), bedLocation.getBlockZ()));
		PacketHandler.sendPacketToAll(packetBed);
		
		fakePlayer.setLocation(bodyLocation.getBlockX(), bodyLocation.getBlockY(), bodyLocation.getBlockZ(), 0.0f, 0.0f);
		PacketPlayOutEntityTeleport packetTeleport = new PacketPlayOutEntityTeleport(fakePlayer);
		PacketHandler.sendPacketToAll(packetTeleport);
	}
	
	public void remove() {
		// Removes (kills) the fake player.
		fakePlayer.die();
	}
	
	public EntityPlayer getFakePlayer() {
		return fakePlayer;
	}
}
