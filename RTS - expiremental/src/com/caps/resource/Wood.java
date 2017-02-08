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
		if(img == null){
			
			try {
				img = ImageIO.read(this.getClass().getResource("/tree.png"));
				width = img.getWidth(null);
	            height = img.getHeight(null);
	            g.drawImage(img,Math.round(x),Math.round(y), width/4, height/4, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
            
		}else{
			g.drawImage(img,Math.round(x),Math.round(y), width/4, height/4, null);
		}
		
		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, 53,40);
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
		return new Rectangle((int)x, (int)y, 32,62);

	}
}
