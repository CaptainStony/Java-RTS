package com.caps.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected boolean selected = false;
	protected int baseSpeed;
	protected int objID;

	protected int step = 0;

	protected int Health;

	protected Coordinate endPoint;
	protected LinkedList<GridCell> path;
	protected LinkedList<GridCell> closedList = new LinkedList<GridCell>();
	protected LinkedList<GridCell> openList = new LinkedList<GridCell>();
	protected int cnt = 0;
	protected LinkedList<GridCell> tempPath = new LinkedList<GridCell>();

	protected GameObject interactedResource;
	
	
	public RESOURCE isResource;
	
	protected static enum RESOURCE{
		Wood,Gold,Food,
	};		
	public GameObject(float x, float y, ID id, int objID){
		this.x = x;
		this.y = y;
		this.id = id;
		this.objID = objID;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBoundsTotal();

	
	
	public LinkedList<GridCell> getPath() {
		return path;
	}
	public void setPath(LinkedList<GridCell> path) {
		step = 0;
		this.path = path;
	}
	
	public int getHealth() {
		return Health;
	}

	public void setHealth(int health) {
		Health = health;
	}
	public RESOURCE getResource(){
		return isResource;
	}
	
	public void setEndPoint(Coordinate endPoint){
		this.endPoint = endPoint;

	}
	public Coordinate getEndPoint(){
		return endPoint;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setId(ID id){
		this.id = id;
	}
	
	public ID getId(){
		return id;
	}
	
	public void setVelX(float velX){
		this.velX = velX;
	}
	
	public void setVelY(float velY){
		this.velY= velY;
	}
	
	public float getVelX(){
		return velX;
	}
	
	public float getVelY(){
		return velY;
	}
	public int getObjID() {
		return objID;
	}

	public void setObjID(int objID) {
		this.objID = objID;
	}
}
