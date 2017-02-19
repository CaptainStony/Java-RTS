package com.caps.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GridCell {

	protected int x, y;
	protected int row,col;
	
	protected int G=0,H,F;
	
	//protected GridCell parent;
	protected boolean isWall;
	protected boolean render;
	
	private Image img = null;

	protected int width = 0;
    protected int height = 0;
    
	public GridCell(int x, int y, int row,int col){
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g){
		if(img == null){
			try {
				img = ImageIO.read(this.getClass().getResource("/grass.png"));
	            width = img.getWidth(null);
	            height = img.getHeight(null);
	            g.drawImage(img,Math.round(x),Math.round(y), width, height, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(img, Math.round(x), Math.round(y), width, height, null);
		}
		/*if(isWall == true){
			g.setColor(Color.blue);
			g.drawRect(x, y, 20, 20);
			
		}else{
			g.setColor(Color.red);
			g.drawRect(x, y, 20, 20);			
		}*/
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
	
	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
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
	
	public boolean intersects(Handler handler, GridCell cell){
		for(int i = 0; i < handler.object.size(); i++){
			if(cell.getBoundsTotal().intersects(handler.object.get(i).getBoundsTotal())){
				return true;
			}
		}
		return false;
	}
}