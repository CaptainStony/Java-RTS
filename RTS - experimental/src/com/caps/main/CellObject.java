package com.caps.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class CellObject {

	protected int x, y;
	protected int row,col;
	protected int effort;
	
	protected boolean isWall = false;
	
	protected LinkedList<CellObject> parents;
	
	public CellObject(int x, int y, int row,int col){
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBoundsTotal();
	
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
