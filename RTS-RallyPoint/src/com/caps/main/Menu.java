package com.caps.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
	

public class Menu extends MouseAdapter{
	private Game game;


	public Menu(Game game, Handler handler){		
		this.game = game;

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
