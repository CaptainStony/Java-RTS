package com.caps.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.caps.main.Game;
import com.caps.main.Handler;
import com.caps.main.Location;

public abstract class BuildingObject {
    
	protected int x, y;
	
	protected int width, height;
	protected Game game;
	protected Handler handler;
	
	public abstract Rectangle getBoundsTotal();
	
	public abstract void render(Graphics g);
	public abstract void tick();
	protected BUILDINGTYPE type;
	
	public static enum BUILDINGTYPE{
		Military(), Ultility(), Wall()
	}
	
	public BuildingObject(int x, int y, Game game, Handler handler, BUILDINGTYPE type){
		this.x = x;
		this.y = y;
		this.game = game;
		this.handler = handler;
		this.type = type;
	}
	public BuildingObject(Location loc, Game game, Handler handler, BUILDINGTYPE type){
		this.x = loc.getX();
		this.y = loc.getY();
		this.game = game;
		this.handler = handler;
		this.type = type;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

}
