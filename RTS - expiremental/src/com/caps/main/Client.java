package com.caps.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Client extends Thread{
	private InetAddress ipAddress;
	protected DatagramSocket socket;
	private Game game;
	
	interface clientListener{
		void packetRecieved(DatagramPacket p, Game game);
	}
	private LinkedList<clientListener> packetHandlers = new LinkedList<clientListener>();
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
		addListener(new PacketHandler());
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
			for(clientListener cl : packetHandlers){
				cl.packetRecieved(packet, game);
			}
		}while(true);
	}
	public void addListener(clientListener listener){
		packetHandlers.add(listener);
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
