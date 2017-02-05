package com.caps.main;

import java.awt.Color;
import java.awt.Font;

public class HUD{
	public static int GOLD = 0;
	public static int WOOD = 0;
	public static int FOOD = 0;

	public void tick(){
		
		
	}
	
	public void render(java.awt.Graphics g,Game game){
		int x = Game.WIDTH-game.cameraX;
		int y = Game.HEIGHT-game.cameraY;
			
	    g.setFont(new Font("default", Font.PLAIN, 15));

		g.setColor(Color.orange);
		g.fillRect(x-1070, y-120, 1050, 80);
		
		g.setColor(Color.black);
	    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("GOLD: "+GOLD, x-1070, y-780);
		g.drawString("WOOD: "+WOOD, x-1070, y-760);
		g.drawString("FOOD: "+FOOD, x-1070, y-740);
		if(!game.selectedObject.isEmpty()){
		    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			g.drawString("Name: "+game.selectedObject.getLast().getId().toString(), x-1070, y-130);
			
		}
	}

}
