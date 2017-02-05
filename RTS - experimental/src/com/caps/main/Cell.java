package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cell extends CellObject{
	
	private PathFinder pathFinder;
	private Handler handler;

	public Cell(int x, int y,int row,int col,Handler handler,PathFinder pathFinder) {
		super(x, y, row,col);
		this.handler = handler;
		this.pathFinder = pathFinder;
	}
	
	@Override
	public void tick() {

		collision();
	}
	@Override
	public void render(Graphics g) {
			g.setColor(Color.red);
			g.drawRect(x, y, 20, 20);
			 
	}
	private void collision(){
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getBoundsTotal().intersects(getBoundsTotal()) && tempObject.getId() == ID.Base){
				pathFinder.grid[row][col] = 1;
				isWall = true;
			}else{
				pathFinder.grid[row][col] = 0;
			}
		}
	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 20, 20);

	}
	private void setPartents(){
		
	}
}
