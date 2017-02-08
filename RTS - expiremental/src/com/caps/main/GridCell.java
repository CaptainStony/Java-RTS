package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GridCell {

	protected int x, y;
	protected int row,col;
	protected int effort;
	
	protected boolean isWall = false;
	protected GridCell parent;
	
	public GridCell(int x, int y, int row,int col, boolean isWall){
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
		this.isWall = isWall;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect(this.x, this.y, 20, 20);
	}
	public Rectangle getBoundsTotal(){
		
		return new Rectangle(this.x, this.y, 20,20);
	}
	
	
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

	public void setParent(GridCell parent){
		this.parent = parent;
	}
	public GridCell getParent(){
		return parent;
	}
}