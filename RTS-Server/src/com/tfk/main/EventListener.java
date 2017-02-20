package com.tfk.main;

import java.util.LinkedList;

public class EventListener implements serverListener{
	private LinkedList<Player> allPlayers = new LinkedList<Player>();
	private Server server;
	public EventListener(LinkedList<Player> allPlayers, Server server){
		this.allPlayers = allPlayers;
		this.server = server;
	}
	@Override
	public void playerConnected(Player player) {
		allPlayers.add(player);
		server.sendData(String.format("Server: %s\nWorldGenerator: base\nx: %d y: %d", player.serverID(), 0, 0).getBytes(), player.getIP(), player.getPort());
	}
}
