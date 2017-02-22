package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Window extends Canvas{

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame("rts shit game");
	public JButton serverEnter = new JButton("Connect");
	public JTextField serverIP = new JTextField();
	public JFormattedTextField serverPort = new JFormattedTextField();

	
	public Window(int width, int height , Game game){
		serverIP.setVisible(true);
		serverPort.setVisible(true);
		serverIP.setBackground(Color.white);
		serverIP.setSize(200, 50);
		serverIP.setLocation(450,200);
		serverIP.setText("127.0.0.1");
		
		serverPort.setBackground(Color.white);
		serverPort.setSize(200, 50);
		serverPort.setLocation(450,400);
		serverPort.setText("15504");
		serverEnter.setSize(200, 50);
		serverEnter.setLocation(450, 470);
		frame.add(serverEnter);
		frame.add(serverIP);
		frame.add(serverPort);
		frame.setPreferredSize(new Dimension(width , height));
		frame.setMaximumSize(new Dimension(width , height));
		frame.setMinimumSize(new Dimension(width , height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
		game.start();


	}
	public void addButton(int x, int y, int width, int height,String text){
		JButton button = new JButton(text);
		button.setSize(width,height);
		button.setLocation(x, y);
		button.setVisible(true);
		frame.add(button);
		frame.repaint();
		frame.revalidate();
	}
}
