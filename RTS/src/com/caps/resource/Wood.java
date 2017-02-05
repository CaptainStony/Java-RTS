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
		try {
			Image image = ImageIO.read(this.getClass().getResource("/tree.png"));
            int w = image.getWidth(null);
            int h = image.getHeight(null);
            g.drawImage(image,Math.round(x),Math.round(y), w/4, h/4, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
			/*g.setColor(Color.black);
			g.drawRect((int)x, (int)y, 2, 16);//left
			g.drawRect((int)x+14, (int)y, 2, 16);//right
			g.drawRect((int)x+2, (int)y, 12, 2);//up
			g.drawRect((int)x+2, (int)y+14, 12, 2);//down
			 */
		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, 32,62);
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
