package com.tfk.structures;

import java.awt.Rectangle;

import com.tfk.main.Coordinate;
import com.tfk.main.Handler;


public abstract class BuildingObject {
    
	protected int x, y;
	
	protected int width, height;
	protected Handler handler;
	
	public abstract Rectangle getBoundsTotal();
	
	public abstract void tick();
	protected BUILDINGTYPE type;
	
	public static enum BUILDINGTYPE{
		Military(), Ultility(), Wall(), Base()
	}
	
	public BuildingObject(int x, int y, Handler handler, BUILDINGTYPE type){
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.type = type;
	}
	public BuildingObject(Coordinate cord, Handler handler, BUILDINGTYPE type){
		this.x = cord.getX();
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
