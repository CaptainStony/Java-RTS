package com.caps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
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
	private Grid grid;
	
	public HUD(Game game,Grid grid,Window window){
		base = (TownCenter) game.handler.findObject(ID.Base);
		this.handler = game.handler;
		this.grid = grid;
		
		window.b.setVisible(false);
		window.b.setText("elle moema");
		
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
		if(b == null && base != null && base.selected ){
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
		}else if(base != null && base.selected){
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
		
		// mini map 1337
		for (int i = 0; i < 75; i++) {
			for (int j = 0; j < 75; j++) {
				GridCell cell = grid.gridCells[i][j];
				
				if(cell.isWall == true){
					g.setColor(Color.black);
				}else{
					g.setColor(Color.white);
				}
				g.fillRect(x-(i*2)-20, y-(j*2)-650, 2, 2);
			}
		}
		g.setColor(Color.black);
		g.drawRect(x-168, y-798, 75*2, 75*2);
		
	}

}