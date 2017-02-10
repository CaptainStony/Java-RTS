package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GridCell {

	protected int x, y;
	protected int row,col;
	
	protected int G=0,H,F;
	
	protected GridCell parent;
	protected boolean isWall;
	
	public GridCell(int x, int y, int row,int col){
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g){
		if(isWall == true){
			g.setColor(Color.blue);
			g.drawRect(x, y, 20, 20);
			
		}else{
			g.setColor(Color.red);
			g.drawRect(x, y, 20, 20);			
		}
	}
	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
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

}