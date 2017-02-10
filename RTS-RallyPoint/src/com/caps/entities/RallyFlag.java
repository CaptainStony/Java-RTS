package com.caps.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.caps.main.Location;

public class RallyFlag {

	private int x, y, width, height;
	private Image img = null;
	public RallyFlag(int x, int y){
		this.x = x;
		this.y = y;
	}
	public RallyFlag(Location loc){
		this.x = loc.getX();
		this.y = loc.getY();
	}
	public void render(Graphics g, int x, int y){
		this.x = x;
		this.y = y;
		if(img == null){
			try {
				img = ImageIO.read(this.getClass().getResource("/rallyFlag.png"));
				this.width = img.getWidth(null) / 2;
				this.height = img.getHeight(null) / 2;
			} catch (IOException e) {
				//do jack shit #killmyself
			}
			g.drawImage(img, this.x, this.y, width, height, null);
		}else{
			g.drawImage(img, this.x, this.y, width, height, null);
		}
	}
	public void render(Graphics g, Location loc){
		this.x = loc.getX();
		this.y = loc.getY();
		if(img == null){
			try {
				img = ImageIO.read(this.getClass().getResource("/rallyFlag.png"));
				this.width = img.getWidth(null) / 2;
				this.height = img.getHeight(null) / 2;
			} catch (IOException e) {
				//do jack shit #killmyself
			}
			g.drawImage(img, this.x, this.y, width, height, null);
		}else{
			g.drawImage(img, this.x, this.y, width, height, null);
		}
	}
	public Rectangle getBoundsTotal(){
		return new Rectangle(this.x, this.y, this.width, this.height);
	}
}
