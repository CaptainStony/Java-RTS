package com.tfk.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Date;

public class EventHandler implements serverListener{
	private Server server;
	public EventHandler(Server server){;
		this.server = server;
	}
	@Override
	public void playerConnected(Player player) {
		try {
			new WorldGenerator().run(Server.map, server, player);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (server.players.size() == 1) {
			server.sendData(String.format("Server: %s\nWorldGenerator: base\nx: %d y: %d", player.serverID(), 0, 0).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("Server: %s\nAddGameObject: slave\nx: %d y: %d", player.serverID(), 200, 200).getBytes(), player.getIP(), player.getPort());
			
			server.addServerText("Connection packet sent.");
		}else if(server.players.size() == 2){
			server.sendData(String.format("Server: %s\nWorldGenerator: base\nx: %d y: %d", player.serverID(), 1300, 1300).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("Server: %s\nAddGameObject: slave\nx: %d y: %d", player.serverID(), 1200, 1200).getBytes(), player.getIP(), player.getPort());
			
			server.addServerText("Connection packet sent.");
		}
	}
	@Override
	public void packetReceived(DatagramPacket p) {
		Player ply = null;
		String[] message = new String(p.getData()).trim().split(" ");
		for(Player player : server.players){
			if(player.getID().equals(message[1].trim())){
				ply = player;
				break;
			}
		}
		if(ply != null){
			ply.lastPacketRec = new Date().getTime();
		}
	}
}
