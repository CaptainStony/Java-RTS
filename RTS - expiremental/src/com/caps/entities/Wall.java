package com.caps.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.caps.main.Game;
import com.caps.main.Handler;

public class Wall extends BuildingObject{

	private Game game;
	private Handler handler;
	private BUILDINGTYPE type;
	private Image img;
	
	public Wall(int x, int y, Game game, Handler handler, BUILDINGTYPE type) {
		super(x, y, game, handler, type);
		this.game = game;
		this.handler = handler;
		this.type = type;
	}

	@Override
	public void render(Graphics g) {
		if(img == null){
			try {
				img = ImageIO.read(this.getClass().getResource("/wall.png"));
	            width = img.getWidth(null);
	            height = img.getHeight(null);
	            g.drawImage(img,Math.round(x),Math.round(y), width, height, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(img, Math.round(x), Math.round(y), width, height, null);
		}		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle(x, y, 20, 20);
	}
	
	

	
}
