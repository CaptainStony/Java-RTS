package com.caps.resource;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;

public class Wood extends GameObject{

	private Image img;
	private int width;
	private int height;
	private Handler handler;
	
	public Wood(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		baseSpeed = 0;
		Health = 100;
		this.handler = handler;
		isResource = RESOURCE.Wood;
		try {
			img = ImageIO.read(this.getClass().getResource("/tree.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		if(Health <= 0){
			handler.removeObject(this);
		}
		collision();
	}
	@Override
	public void render(Graphics g) {
		width = img.getWidth(null) / 4;
	    height = img.getHeight(null) / 4;
	    g.drawImage(img,Math.round(x),Math.round(y), width, height, null);
		
		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, width, height);
		}
	}
	private void collision(){


	}
	@Override
	public Rectangle getBoundsUp() {
		return null;
	}
	@Override
	public Rectangle getBoundsDown() {
		return null;
	}
	@Override
	public Rectangle getBoundsLeft() {
		return null;
	}
	@Override
	public Rectangle getBoundsRight() {
		return null;
	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, width, height);

	}
}
