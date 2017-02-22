package com.caps.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.caps.entities.TownCenter;
import com.caps.main.Game.STATE;
import com.caps.resource.Wood;

public class Client extends Thread{
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	
	public Client(Game game, String ipAdress){
		this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAdress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	public void run(){
		do{
			
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try{
				socket.receive(packet);
			}catch(IOException e){
				e.printStackTrace();
			}
			String[] message = new String(packet.getData()).trim().split("\n");
			String servID = message[0].split(" ")[1];
			if(packet.getAddress().toString().contains("" + ipAddress.toString()) && message[0].split(" ")[0].equalsIgnoreCase("server:")){
				if(servID.equals(Game.serverID) && game.gameState == STATE.Connecting){
					if(message[1].trim().contains("Connected")){
						game.gameState = STATE.Game;
					}
				}
				if(Game.serverID == null && message[0].split(" ")[0].equalsIgnoreCase("server:")){
					Game.serverID = message[0].split(" ")[1];
				}else if(servID.equals(Game.serverID) && message[1].split(" ")[0].equalsIgnoreCase("worldgenerator:")){
					if(message[1].split(" ")[1].equalsIgnoreCase("tree")){
						String[] obj = message[2].split(" ");
						game.handler.addObject(new Wood(Float.parseFloat(obj[1]), Float.parseFloat(obj[3]), ID.Resource, game.handler));
					}else if(message[1].split(" ")[1].equalsIgnoreCase("base")){
						String[] obj = message[2].split(" ");
						game.handler.addObject(new TownCenter(Float.parseFloat(obj[1]), Float.parseFloat(obj[3]), ID.Base, game, game.handler));
					}
				}
			}
		}while(true);
	}
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data,  data.length, ipAddress, game.serverPort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
