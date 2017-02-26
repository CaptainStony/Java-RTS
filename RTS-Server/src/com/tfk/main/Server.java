package com.tfk.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

interface serverListener{
	void playerConnected(Player player);
	void packetReceived(DatagramPacket p);
}

public class Server extends Thread{
	
	private DatagramSocket socket;
	
	private final String[] serverID = new String[4];
	protected LinkedList<Player> players = new LinkedList<Player>();
	
	protected static long map = new Random().nextInt(25);
	
	private String serverOut = "";
	private JTextField textField = new JTextField();
	private JTextArea viewField = new JTextArea();
	protected Handler handler;
	protected Grid grid;
	
	private List<serverListener> listeners = new ArrayList<serverListener>();
	public void addListener(serverListener listener){
		listeners.add(listener);
	}
	public Server(){
		createWindow();
		addServerText("Server Starting");
		addListener(new EventHandler(this));
		try {
			this.socket = new DatagramSocket(15504, InetAddress.getLocalHost());
			System.out.println(InetAddress.getLocalHost().toString());
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		handler = new Handler();
		grid = new Grid(handler);
		try {
			new WorldGenerator().run(map, this);
		} catch (IOException e) {
			e.printStackTrace();
		};
		start();
	}
	private void createWindow(){
		JFrame frame = new JFrame("RTS server");
		frame.setPreferredSize(new Dimension(600, 600));
		frame.setMaximumSize(new Dimension(600, 600));
		frame.setMinimumSize(new Dimension(600, 600));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		viewField.setEnabled(false);
		viewField.setVisible(true);

		viewField.setFont(new Font("Courier", Font.BOLD,12));
		viewField.setDisabledTextColor(Color.BLACK);
		viewField.setBorder(null);
		
		JScrollPane scroll = new JScrollPane (viewField, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		textField.setText("");
		textField.setVisible(true);

		frame.add(textField,BorderLayout.SOUTH);
		frame.add(scroll);

		frame.setVisible(true);
		textField.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          executeCommand(textField.getText());
	          textField.setText("");
	        }
	      });
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
			String message[] = new String(packet.getData()).trim().split(" ");
			for(String msg : message){
				msg = msg.trim();
			}
			if(message[0].equals("Player:")){
				if(players.size() > 0){
					if(message.length >= 3 && message[2].trim().contains("Connecting")){
						Boolean playerExists = false;
						for(int i = 0; i > players.size(); i++){
							if(players.get(i).getID().equals(message[1])){
								playerExists = true;
								break;
							}else{
								playerExists = false;
							}
						}
						if(!playerExists){
							serverID[players.size()-1] = UUID.randomUUID().toString();
							players.add(new Player(message[1], packet.getAddress(), packet.getPort(), serverID[players.size()-1]));
							String str = new String( "00Server: " + serverID[players.size()-1]);
							sendData(str.getBytes(), packet.getAddress(), packet.getPort());
							addServerText("[" + packet.getAddress() +"]Player "+ players.size() +" connected");
							for(serverListener sl : listeners){
								sl.playerConnected(players.getLast());
							}
						}
						
					}else{
						for(serverListener sl : listeners){
							sl.packetReceived(packet);
						}
					}
				}else{
					serverID[0] = UUID.randomUUID().toString();
					players.add(new Player(message[1], packet.getAddress(), packet.getPort(), serverID[0]));
					String str = new String( "00Server: " + serverID[0]);
					sendData(str.getBytes(), packet.getAddress(), packet.getPort());
					addServerText("[" + packet.getAddress() +"]Player " + players.size() +" connected");
					for(serverListener sl : listeners){
						sl.playerConnected(players.get(0));
					}
				}
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
	public void sendData(DatagramPacket packet){
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public void addServerText(String str){
		serverOut+="\n";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		serverOut+=timestamp.toLocaleString()+" : "+str;
		viewField.setText(serverOut);

	}
	@SuppressWarnings("deprecation")
	public void executeCommand(String str){
		serverOut+="\n";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		serverOut+=timestamp.toLocaleString()+" : "+str;
		viewField.setText(serverOut);
		commands(str);

	}
	private void commands(String str){
		String cmd = str.toLowerCase().toString().trim();

		if(cmd.equals("exit")|| cmd.equals("quit")){
			System.exit(1);
		}else if(cmd.equals("help")){
			addServerText("quit/exit - Stop the server and close window.");
		}else if(cmd.equals("players")){
			addServerText("There are " + players.size() + " players on the server");
			for(Player p : players){
				addServerText(p.getIP().toString());
			}
		}else if(cmd.equals("reset")){
			listeners.clear();
			addListener(new EventHandler(this));
			map = new Random().nextInt(25);
			players.clear();
			serverOut="";
			viewField.setText(serverOut);
			addServerText("Server reset!");
		}else{
			viewField.setDisabledTextColor(Color.red);
			addServerText("Unknown command!");
			viewField.setDisabledTextColor(Color.black);
		}
	}
	public static void main(String args[]){
		new Server();
	}
}
