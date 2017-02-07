package com.caps.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected boolean selected = false;
	protected int baseSpeed;
	protected int Health;
	

	protected GameObject endPoint;
	protected GameObject interactedResource;
	
	protected RESOURCE isResource;
	
	protected static enum RESOURCE{
		Wood,Gold,Food,
	};		
	public GameObject(float x, float y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBoundsTotal();
	public abstract Rectangle getBoundsUp();
	public abstract Rectangle getBoundsDown();
	public abstract Rectangle getBoundsLeft();
	public abstract Rectangle getBoundsRight();

	
	public int getHealth() {
		return Health;
	}

	public void setHealth(int health) {
		Health = health;
	}
	public RESOURCE getResource(){
		return isResource;
	}
	
	public void setEndPoint(GameObject endPoint){
		this.endPoint = endPoint;

	}
	public GameObject getEndPoint(){
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

}
