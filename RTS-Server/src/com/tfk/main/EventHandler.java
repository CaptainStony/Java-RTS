package com.tfk.main;

import java.net.DatagramPacket;

import com.tfk.structures.TownCenter;
import com.tfk.structures.BuildingObject.BUILDINGTYPE;

public class EventHandler implements serverListener{
	private Server server;
	public EventHandler(Server server){;
		this.server = server;
	}
	@Override
	public void playerConnected(Player player) {

		GameObject obj;
		
		if (server.players.size() == 1) {
			server.sendData(String.format("01Server: %s\nbase\nx: %d y: %d", player.serverID(), 0, 0).getBytes(), player.getIP(), player.getPort());
			server.handler.addObject(new TownCenter(0,0,BUILDINGTYPE.Base, server.handler));
			for(int i = 0; i < server.handler.resourceObject.size(); i++){
				obj = server.handler.resourceObject.get(i);
				server.sendData(String.format("01Server: %s\ntree\nx: %d y: %d", player.serverID(), (int) obj.getX(), (int) obj.getY()).getBytes(), player.getIP(), player.getPort());
			}
			server.sendData(String.format("00Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("01Server: %s\nslave\nx: %d y: %d", player.serverID(), 200, 200).getBytes(), player.getIP(), player.getPort());
			
		}else if(server.players.size() == 2){
			server.sendData(String.format("01Server: %s\nbase\nx: %d y: %d", player.serverID(), 1300, 1300).getBytes(), player.getIP(), player.getPort());
			server.handler.addObject(new TownCenter(1300,1300,BUILDINGTYPE.Base, server.handler));
			for(int i = 0; i < server.handler.resourceObject.size(); i++){
				obj = server.handler.resourceObject.get(i);
				server.sendData(String.format("01Server: %s\ntree\nx: %d y: %d", player.serverID(), (int) obj.getX(), (int) obj.getY()).getBytes(), player.getIP(), player.getPort());
			}
			server.sendData(String.format("00Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("01Server: %s\nslave\nx: %d y: %d", player.serverID(), 1200, 1200).getBytes(), player.getIP(), player.getPort());
			
			server.addServerText("Connection packet sent.");
		}
	}
	@Override
	public void packetReceived(DatagramPacket p) {
		
	}
}
