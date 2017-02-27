package com.tfk.main;

import java.net.DatagramPacket;
import java.util.LinkedList;

import com.tfk.entities.Slave;
import com.tfk.structures.BuildingObject.BUILDINGTYPE;
import com.tfk.structures.TownCenter;

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
			for(int i = 0; i < server.handler.resourceObject.size(); i++){
				obj = server.handler.resourceObject.get(i);
				server.sendData(String.format("01Server: %s\ntree\nx: %d y: %d", player.serverID(), (int) obj.getX(), (int) obj.getY()).getBytes(), player.getIP(), player.getPort());
			}
			server.sendData(String.format("00Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("01Server: %s\nslave\nx: %d y: %d", player.serverID(), 200, 200).getBytes(), player.getIP(), player.getPort());
			server.handler.addObject(new Slave(200,200, ID.Slave, server.handler, server.grid, player.getID()));
			
		}else if(server.players.size() == 2){
			server.sendData(String.format("01Server: %s\nbase\nx: %d y: %d", player.serverID(), 1300, 1300).getBytes(), player.getIP(), player.getPort());
			server.handler.addObject(new TownCenter(1300,1300,BUILDINGTYPE.Base, server.handler,player.getID()));
			for(int i = 0; i < server.handler.resourceObject.size(); i++){
				obj = server.handler.resourceObject.get(i);
				server.sendData(String.format("01Server: %s\ntree\nx: %d y: %d", player.serverID(), (int) obj.getX(), (int) obj.getY()).getBytes(), player.getIP(), player.getPort());
			}
			server.sendData(String.format("00Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			server.sendData(String.format("01Server: %s\nslave\nx: %d y: %d", player.serverID(), 1200, 1200).getBytes(), player.getIP(), player.getPort());
			server.handler.addObject(new Slave(1200, 1200, ID.Slave, server.handler, server.grid, player.getID()));
			server.addServerText("Connection packet sent.");
		}
	}
	@Override
	public void packetReceived(DatagramPacket p) {
		String msg[] = new String(p.getData()).trim().split("\n");
		String id = msg[0].split(" ")[1].trim();
		Player play = null;
		System.out.println(new String(p.getData()).trim());
		for(Player ply : server.players){
			if(ply.getID().equals(id)){
				play = ply;
				break;
			}
		}
		if(play != null){
			switch(msg[0].substring(0,2)){
			case "04":
				Grid grid = server.grid;
				int x1 = Integer.parseInt(msg[2].split(" ")[1]);
				int x2 = Integer.parseInt(msg[2].split(" ")[2]);
				int y1 = Integer.parseInt(msg[2].split(" ")[4]);
				int y2 = Integer.parseInt(msg[2].split(" ")[5]);
				//Slave 
				LinkedList<GridCell> path = grid.calculatePath(grid.findGridCellByXAndY(x1, y1), grid.findGridCellByXAndY(x2, y2), server.handler.getByPos(x1, y1));
				
				break;
			}
		}
	}
}
