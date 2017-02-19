package com.caps.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame("rts shit game");
	public JButton b = new JButton("boi");
	public Window(int width, int height , Game game){		
		b.setSize(100, 50);
		b.setLocation(30, 710);
		frame.add(b);
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
