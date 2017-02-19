package com.tfk.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread{
	private DatagramSocket socket;
	public Server(){
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		start();
	}
	public void run(){
		while (true){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try{
				socket.receive(packet);
			}catch(IOException e){
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			if(message.trim().equalsIgnoreCase("ping")){
				System.out.println("CLIENT > " + message);
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
			
		}
	}
	public void sendData(byte[] data, InetAddress ipAddress, int port){
		DatagramPacket packet = new DatagramPacket(data,  data.length, ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		new Server();
	}
}
