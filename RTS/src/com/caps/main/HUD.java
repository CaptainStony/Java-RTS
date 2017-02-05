package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.caps.main.Button.TYPE;

public class HUD{
	public static int GOLD = 0;
	public static int WOOD = 0;
	public static int FOOD = 100;
	public void tick(){
		
		
	}
	public void render(Graphics g,Game game){
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
		if(game.handler.findObject(ID.Button) == null && game.handler.findObject(ID.Base).selected ){
			game.handler.addObject(new Button(x - 1070, y -155, ID.Button, TYPE.Slave));
			Button b = (Button) game.handler.findObject(ID.Button);
		    b.render(g, x - 1060, y -105);
		}else if(game.handler.findObject(ID.Base).selected){
			Button b = (Button) game.handler.findObject(ID.Button);
			b.render(g, x - 1060, y -105);
		}else{
			game.handler.removeByID(ID.Button);
		}
	}

}
