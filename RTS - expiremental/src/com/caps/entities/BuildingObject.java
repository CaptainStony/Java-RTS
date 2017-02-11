package com.caps.entities;

import java.awt.Graphics;

import com.caps.main.Game;
import com.caps.main.Handler;
import com.caps.main.Location;

public abstract class BuildingObject {
    
	private int x, y;
	private int width, height;
	private Game game;
	private Handler handler;
	
	public abstract void render(Graphics g);
	public abstract void tick();
	protected TYPE type;
	
	protected static enum TYPE{
		Military(), Ultility()
	}
	
	public BuildingObject(int x, int y, Game game, Handler handler, TYPE type){
		this.x = x;
		this.y = y;
		this.game = game;
		this.handler = handler;
		this.type = type;
	}
	public BuildingObject(Location loc, Game game, Handler handler, TYPE type){
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
