package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.caps.entities.TownCenter;
import com.caps.main.Button.TYPE;

public class HUD{
	public static int GOLD = 0;
	public static int WOOD = 0;
	public static int FOOD = 100;
	private int time = 0;
	private Button b = null;
	private TownCenter base;
	private Game game;
	public HUD(Game game){
		this.game = game;
		base = (TownCenter) game.handler.findObject(ID.Base);
	}
	public void tick(){
		if(base.getQueue().getQueueSize() > 0 && time == 0){
			time =  base.getQueue().getTimeFromQueue(1)*60;
		}else if(base.getQueue().getQueueSize() > 0){
			time--;
		}else if(base.getQueue().getQueueSize() > 0 && time == 1){
			this.game.handler.addObject(base.getQueue().getItemFromQueue(1));
			base.getQueue().removeFromQueue();
		}
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
		if(b == null && base.selected ){
			game.handler.addObject(new Button(x - 1070, y -155, ID.Button, TYPE.Slave));
			b = (Button) game.handler.findObject(ID.Button);
		    b.render(g, x - 1060, y -105);
		    g.setColor(Color.white);
		    g.drawRect(x - 600, y-800, 100, 30);
		}else if(base.selected){
			b.render(g, x - 1060, y -105);
			
			if(base.getQueue().getQueueSize() > 0){
				float fac = (float) (time/(base.getQueue().getFirstTime()*60.00));
				float width = 100 * fac;
				if(time == 0){
					
					base.getQueue().removeFromQueue();
				}
				g.setColor(Color.red);
				g.fillRect(x-600, y-800, (int) width, 30);
			}
			g.setColor(Color.white);
			g.drawRect(x - 600, y-800, 100, 30);
			
		}else{
			game.handler.removeByID(ID.Button);
		}
	}

}
