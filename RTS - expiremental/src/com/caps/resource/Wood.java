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

	private static int width;
	private static int height;
	private static Image img = null;
	static {
	    try {
	      img = ImageIO.read(Wood.class.getResource("/tree.png"));
	    } catch (Exception e) {
	      // catch exception - do other stuff
	    	System.exit(0);
	    } finally {
	      if (img != null) {
	        width = img.getWidth(null);
	        height = img.getHeight(null);
	      } else {
	        // initialise default values
	        width = 0;
	        height = 0;
	      }
	    }
	  }
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

		if(Health <= 0){
			handler.removeObject(this);
		}

	}
	@Override
	public void render(Graphics g) {
		g.drawImage(img,Math.round(x),Math.round(y), width/4, height/4, null);

		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, 32,62);
		}
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
