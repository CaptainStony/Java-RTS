package com.tfk.main;

import java.awt.Dimension;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.JFrame;

public class Server extends Thread{
	private DatagramSocket socket;
	public Server(){
		createWindow();
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		start();
	}
	private void createWindow(){
		JFrame frame = new JFrame("RTS server");
		frame.setPreferredSize(new Dimension(600, 600));
		frame.setMaximumSize(new Dimension(600, 600));
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		  
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
			if(message.trim().contains("")){
				System.out.println("CLIENT > " + message.trim());
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
