package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.caps.main.Game.STATE;
	

public class Menu extends MouseAdapter{
	private Game game;


	public Menu(Game game, Handler handler){		
		this.game = game;

	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();


	}
	private boolean mouseOver(int mx, int my, int x,  int y,int width , int height){
		if(mx > x && mx < x + width){
			if (my > y && my < y + width) {
				return true;
			}return false;
		}return false;
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	public void tick(){
		
	}

	public void render(Graphics g){

/*
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuGuy(300, 50, ID.MenuGuy , handler));

			}*/


	}

}
