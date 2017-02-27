package com.tfk.structures;

import java.awt.Rectangle;

import com.tfk.main.Coordinate;
import com.tfk.main.Handler;
import com.tfk.main.Server;


public abstract class BuildingObject {
    
	protected int x, y;

	protected int width, height;
	protected Handler handler;
	protected String owner;
	public abstract Rectangle getBoundsTotal();
	protected int objID;
	
	protected BUILDINGTYPE type;
	
	public static enum BUILDINGTYPE{
		Military(), Ultility(), Wall(), Base()
	}
	
	public abstract void tick();
	public BuildingObject(int x, int y, Handler handler, BUILDINGTYPE type, String owner){
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.type = type;
		this.owner = owner;
		objID = Server.at.getAndIncrement();
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
	public BUILDINGTYPE getType() {
		return type;
	}

	public void setType(BUILDINGTYPE type) {
		this.type = type;
	}
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getObjID() {
		return objID;
	}
	public void setObjID(int objID) {
		this.objID = objID;
	}
}
