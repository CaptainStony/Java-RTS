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
			server.handler.addObject(new TownCenter(0, 0, BUILDINGTYPE.Base, server.handler, player.getID()));
			server.sendData(String.format("01Server: %s\nbase\nx: %d y: %d\n%d", player.serverID(), 0, 0, server.handler.buildingObject.getLast().getObjID()).getBytes(), player.getIP(), player.getPort());
			for(int i = 0; i < server.handler.resourceObject.size(); i++){
				obj = server.handler.resourceObject.get(i);
				server.sendData(String.format("01Server: %s\ntree\nx: %d y: %d\n%d", player.serverID(), (int) obj.getX(), (int) obj.getY(), obj.objID).getBytes(), player.getIP(), player.getPort());
			}
			server.sendData(String.format("00Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			server.handler.addObject(new Slave(200,200, ID.Slave, server.handler, server.grid, player.getID()));
			server.sendData(String.format("01Server: %s\nslave\nx: %d y: %d\n%d", player.serverID(), 200, 200, server.handler.object.getLast().objID).getBytes(), player.getIP(), player.getPort());
			
		}else if(server.players.size() == 2){
			server.handler.addObject(new TownCenter(1300,1300,BUILDINGTYPE.Base, server.handler,player.getID()));
			server.sendData(String.format("01Server: %s\nbase\nx: %d y: %d\n%d", player.serverID(), 1300, 1300, server.handler.object.getLast().objID).getBytes(), player.getIP(), player.getPort());
			
			for(int i = 0; i < server.handler.resourceObject.size(); i++){
				obj = server.handler.resourceObject.get(i);
				server.sendData(String.format("01Server: %s\ntree\nx: %d y: %d\n%d", player.serverID(), (int) obj.getX(), (int) obj.getY(), obj.objID).getBytes(), player.getIP(), player.getPort());
			}
			
			server.sendData(String.format("00Server: %s\nConnected", player.serverID()).getBytes(), player.getIP(), player.getPort());
			
			server.handler.addObject(new Slave(1200, 1200, ID.Slave, server.handler, server.grid, player.getID()));
			server.sendData(String.format("01Server: %s\nslave\nx: %d y: %d\n%d", player.serverID(), 1200, 1200, server.handler.object.getLast().objID).getBytes(), player.getIP(), player.getPort());
			
			server.addServerText("Connection packet sent.");
		}
	}
	@Override
	public void packetReceived(DatagramPacket p) {
		String msg[] = new String(p.getData()).trim().split("\n");
		String id = msg[0].split(" ")[1].trim();
		Player play = null;
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
				int x = Integer.parseInt(msg[2].split(" ")[1]);
				int y = Integer.parseInt(msg[2].split(" ")[3]);
				int objID = Integer.parseInt(msg[3]);
				GameObject GO = server.handler.getByObjID(objID);
				LinkedList<GridCell> path = grid.calculatePath(grid.findGridCellByXAndY((int) GO.getX(), (int) GO.getY()), grid.findGridCellByXAndY(x, y), GO);
				GO.setPath(path);
				break;
			}
		}
	}
}
