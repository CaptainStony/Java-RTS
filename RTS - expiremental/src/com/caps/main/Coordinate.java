package com.caps.main;

import java.awt.Rectangle;

public class Coordinate {
	protected int x;
	protected int y;
	protected Rectangle rect;
	
	public Coordinate(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x, y, width, height);
	}
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
