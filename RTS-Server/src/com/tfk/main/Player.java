package com.tfk.main;

import java.net.InetAddress;

public class Player {

	private final String uniqueID;
	private InetAddress ipAddress;
	private int port;
	private String serverID;
	protected int WOOD = 0, FOOD = 0, GOLD = 0;
	
	
	public Player(String uniqueID, InetAddress ipAddress, int port, String serverID){
		this.uniqueID = uniqueID;
		this.ipAddress = ipAddress;
		this.port = port;
		this.serverID = serverID;
	}
	protected String getID(){
		return uniqueID;
	}
	protected InetAddress getIP(){
		return ipAddress;
	}
	protected int getPort(){
		return port;
	}
	protected String serverID(){
		return serverID;
	}
	public int getWOOD() {
		return WOOD;
	}
	public void setWOOD(int wOOD) {
		WOOD = wOOD;
	}
	public int getFOOD() {
		return FOOD;
	}
	public void setFOOD(int fOOD) {
		FOOD = fOOD;
	}
	public int getGOLD() {
		return GOLD;
	}
	public void setGOLD(int gOLD) {
		GOLD = gOLD;
	}
}
