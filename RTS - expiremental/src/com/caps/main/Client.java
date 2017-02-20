package com.caps.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.caps.resource.Wood;

public class Client extends Thread{
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	private boolean isConnected = false;
	
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
			if(packet.getAddress().toString().contains("" + ipAddress.toString()) && message[0].split(" ")[0].equalsIgnoreCase("server:")){
				if(Game.serverID == null && message[0].split(" ")[0].equalsIgnoreCase("server:")){
					Game.serverID = message[0].split(" ")[1];
					isConnected = true;
				}else if(message[0].split(" ")[1].equals(Game.serverID) && message[1].split(" ")[0].equalsIgnoreCase("worldgenerator:")){
					System.out.println("test");
					if(message[1].split(" ")[1].equalsIgnoreCase("tree")){
						String[] obj = message[2].split(" ");
						game.handler.addObject(new Wood(Float.parseFloat(obj[1]), Float.parseFloat(obj[3]), ID.Resource, game.handler));
					}
				}
			}
		}while(isConnected);
	}
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data,  data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}