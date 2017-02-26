package com.caps.main;

import java.net.DatagramPacket;

import com.caps.entities.Slave;
import com.caps.entities.TownCenter;
import com.caps.main.Client.clientListener;
import com.caps.main.Game.STATE;
import com.caps.resource.Wood;

public class PacketHandler implements clientListener{

	public PacketHandler(){
		//kak
	}
	@Override
	public void packetRecieved(DatagramPacket p, Game game) {
		String[] msg = new String(p.getData()).split("\n");
		String serverID = msg[0].split(" ")[1];
		switch(msg[0].substring(0, 2)){
		case "00":
			if(Game.serverID == null){
				Game.serverID = serverID.trim();
			}else if(serverID.equals(Game.serverID) && msg[1].trim().equalsIgnoreCase("connected")){
				game.gameState = STATE.Game;
			}else{
				System.out.println(Game.serverID);
				System.out.println(serverID);
				System.out.println(new String(p.getData()).trim());
			}
			break;
		case "01":
			if(serverID.equals(Game.serverID)){
				String obj = msg[1].toLowerCase();
				int x;
				int y;
				switch(obj){
				case "tree":
					x = Integer.parseInt(msg[2].split(" ")[1].trim());
					y = Integer.parseInt(msg[2].split(" ")[3].trim());
					game.handler.addObject(new Wood(x, y, ID.Resource, game.handler));
					break;
				case "slave":
					x = Integer.parseInt(msg[2].split(" ")[1].trim());
					y = Integer.parseInt(msg[2].split(" ")[3].trim());
					game.handler.addObject(new Slave(x, y, ID.Resource, game.handler, game.grid));
					break;
				case "base":
					x = Integer.parseInt(msg[2].split(" ")[1].trim());
					y = Integer.parseInt(msg[2].split(" ")[3].trim());
					game.handler.addObject(new TownCenter(x, y, ID.Base, game, game.handler));
					break;
				}
			}
			break;
		default:
			System.out.println(new String(p.getData()).trim());
			break;
		}
	}

}
