package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.caps.entities.Slave;
import com.caps.entities.TownCenter;
import com.caps.main.Button.TYPE;

public class HUD{
	public static int GOLD = 0;
	public static int WOOD = 0;
	public static int FOOD = 100;
	protected Button b = null;
	private TownCenter base;
	public HUD(Game game){
		base = (TownCenter) game.handler.findObject(ID.Base);
	}
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
		if(game.selectedObject.size() > 0 && game.selectedObject.get(0).id == ID.Slave){
			Slave slav = (Slave) game.selectedObject.get(0);
			if(slav.getCarry() > 0){
				g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
				g.drawString("Carrying: " + slav.getCarry() + " " +  slav.isResource.toString(), x-1060, y-100);
			}
		}
		if(b == null && base.selected ){
			game.handler.addObject(new Button(x - 1070, y -155, ID.Button, TYPE.Slave));
			b = (Button) game.handler.findObject(ID.Button);
			base = (TownCenter) game.handler.findObject(ID.Base);
		    b.render(g, x - 1060, y -105);
		    g.setColor(Color.white);
		    g.drawRect(x - 600, y-800, 100, 30);
		}else if(base.selected){
			b.render(g, x - 1060, y -105);
			if(base.getQueue().getQueueSize() > 0 && base.timer != null && base.timer > 0){
				float fac = (float) (base.timer/(base.getQueue().getFirstTime()*60.00));
				float width = 100 * fac;
				g.setColor(Color.red);
				g.fillRect(x-600, y-800, (int) width, 30);
			}
			g.setColor(Color.white);
			g.drawRect(x - 600, y-800, 100, 30);
			
		}else{
			game.handler.removeByID(ID.Button);
			b = null;
		}
	}

}
