package com.caps.main;

import java.net.DatagramPacket;
import java.sql.Timestamp;

import com.caps.entities.Slave;
import com.caps.entities.TownCenter;
import com.caps.main.Client.clientListener;
import com.caps.main.Game.STATE;
import com.caps.resource.Wood;

public class PacketHandler implements clientListener{

	int pack = 0;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public PacketHandler(){
		//kak
	}
	@Override
	public void packetRecieved(DatagramPacket p, Game game) {
		String[] msg = new String(p.getData()).split("\n");
		System.out.println(new String(p.getData()).trim());
		for(int i = 0; i < msg.length; i++){
			msg[i] = msg[i].trim();
		}
		String serverID = msg[0].split(" ")[1];
		switch(msg[0].substring(0, 2)){
		case "00":
			if(Game.serverID == null){
				Game.serverID = serverID.trim();
			}else if(serverID.equals(Game.serverID) && msg[1].trim().equalsIgnoreCase("connected")){
				game.gameState = STATE.Game;
			}
			break;
		case "01":
			if(serverID.equals(Game.serverID)){
				String obj = msg[1].toLowerCase();
				int x;
				int y;
				int objID;
				switch(obj){
				case "tree":
					x = Integer.parseInt(msg[2].split(" ")[1].trim());
					y = Integer.parseInt(msg[2].split(" ")[3].trim());
					objID = Integer.parseInt(msg[3]);
					game.handler.addObject(new Wood(x, y, ID.Resource, game.handler, objID));
					break;
				case "slave":
					x = Integer.parseInt(msg[2].split(" ")[1].trim());
					y = Integer.parseInt(msg[2].split(" ")[3].trim());
					objID = Integer.parseInt(msg[3]);
					game.handler.addObject(new Slave(x, y, ID.Slave, game.handler, game.grid, objID));
					break;
				case "base":
					x = Integer.parseInt(msg[2].split(" ")[1].trim());
					y = Integer.parseInt(msg[2].split(" ")[3].trim());
					objID = Integer.parseInt(msg[3].trim());
					game.handler.addObject(new TownCenter(x, y, ID.Base, game, game.handler, objID));
					break;
				}
			}
			break;
		case "04":
			if(serverID.equals(Game.serverID)){
				int objID = Integer.parseInt(msg[1].trim());
				int x = Integer.parseInt(msg[2].split(" ")[1]);
				int y = Integer.parseInt(msg[2].split(" ")[3]);
				GameObject obj = game.handler.getByObjID(objID);
				obj.setX(x);
				obj.setY(y);
				pack++;
				timestamp.setTime(System.currentTimeMillis());
				System.out.println(pack + " : " + timestamp.toString());
			}
			break;
		default:
			System.out.println("Junk packet");
			System.out.println(new String(p.getData()).trim());
			break;
		}
	}
}
