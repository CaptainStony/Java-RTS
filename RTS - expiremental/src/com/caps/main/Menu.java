package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import com.caps.main.Game.STATE;
	

public class Menu{
	private Image back = null;

	public Menu(Game game,Window window){
		window.serverEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					game.serverIP = InetAddress.getByName(window.serverIP.getText().trim());
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
				
				game.serverPort = Integer.parseInt(window.serverPort.getText().trim());
				game.client = new Client(game, game.serverIP.getHostAddress());
				game.client.start();
				game.client.socket.connect(game.serverIP, game.serverPort);
				game.client.sendData(String.format("00Player: %s \nConnecting", Game.uniqueID).getBytes());
				
				game.gameState = STATE.Connecting;

				//game.grid.loadGrid();
				
			}
		});
	}
	
	public void tick(){
		
	}

	public void render(Graphics g){
		if(back == null){
			try {
				back = ImageIO.read(this.getClass().getResource("/menuBack.png"));
	            g.drawImage(back, 0,0, 1500, 1500, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(back, 0, 0, 1100, 800, null);
		}
		
		Font orgFont = g.getFont();
		g.setColor(Color.black);
		g.setFont(new Font("Verdana", Font.BOLD, 30));
		g.drawString("Server IP", 470, 190);
		g.drawString("Server Port", 450, 380);
		g.setFont(orgFont);
	}

}
