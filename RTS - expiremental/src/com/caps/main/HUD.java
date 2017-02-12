package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import com.caps.entities.Slave;
import com.caps.entities.TownCenter;
import com.caps.main.Button.TYPE;

public class HUD{
	public static int GOLD = 0;
	public static int WOOD = 0;
	public static int FOOD = 100;
	protected LinkedList<GameObject> b = new LinkedList<GameObject>();
	private TownCenter base;
	private Handler handler;
	private WorldGenerator OSN;
	public HUD(Game game){
		base = (TownCenter) game.handler.findObject(ID.Base);
		this.handler = game.handler;
		try {
			OSN = new WorldGenerator();
			OSN.run(handler, game.grid, new Random().nextInt(20));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		    g.drawString("Name: "+game.selectedObject.getLast().getId().toString(), x-1070, y-107);
		    g.drawString("Health: "+game.selectedObject.getLast().getHealth(), x-1070, y-90);

		}
		if(game.selectedObject.size() > 0 && game.selectedObject.get(0).id == ID.Slave){
			Slave slav = (Slave) game.selectedObject.get(0);
			if(slav.getCarry() > 0){
				g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
				g.drawString("Carrying: " + slav.getCarry() + " " + slav.interactedResource.isResource , x-1070, y-44);
			}
		}
		if(b == null && base.selected ){
			handler.addObject(new Button(x, y -105, ID.Button, TYPE.Slave, 1060, 105));
			handler.addObject(new Button(x, y - 105, ID.Button, TYPE.Tank, 1000, 105));
			base = (TownCenter) game.handler.findObject(ID.Base);
			b = handler.getAllByID(ID.Button);
			for(GameObject button : b){
				Button bbb = (Button) button;
				if(bbb.type == TYPE.Slave || bbb.type == TYPE.Tank){
				     bbb.render(g, x - bbb.posX, y - bbb.posY);
				}				
			}
		    g.setColor(Color.white);
		    g.drawRect(x - 600, y-800, 100, 30);
		}else if(base.selected){
			b = handler.getAllByID(ID.Button);
			for(GameObject button : b){
				Button bbb = (Button) button;
				if(bbb.type == TYPE.Slave || bbb.type == TYPE.Tank){
				     bbb.render(g, x - bbb.posX, y - bbb.posY);
				}				
			}
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