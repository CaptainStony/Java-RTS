package com.tfk.main;

import java.net.DatagramPacket;
import java.util.LinkedList;

public class EventHandler implements serverListener{
	private LinkedList<Player> allPlayers = new LinkedList<Player>();
	private Server server;
	public EventHandler(LinkedList<Player> allPlayers, Server server){
		this.allPlayers = allPlayers;
		this.server = server;
	}
	@Override
	public void playerConnected(Player player) {
		allPlayers.add(player);
		server.sendData(String.format("Server: %s\nWorldGenerator: base\nx: %d y: %d", player.serverID(), 0, 0).getBytes(), player.getIP(), player.getPort());
		server.sendData(String.format("Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
		server.addServerText("Connection packet sent.");
	}
	@Override
	public void packetReceived(DatagramPacket p) {
		
	}
}
