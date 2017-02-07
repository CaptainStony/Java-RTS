package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
//import java.util.Random;

import javax.imageio.ImageIO;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;

public class Sheep extends GameObject{

	private Image img = null;
	private int width = 0;
	private int height = 0;
	public Sheep(int x, int y, ID id, Handler handler){
		super(x, y, id);
		baseSpeed = 2;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		if(img == null){
			try {
				img = ImageIO.read(this.getClass().getResource("/sheep.png"));
	            width = img.getWidth(null) / 4;
	            height = img.getHeight(null) / 4;
	            g.drawImage(img,Math.round(x),Math.round(y), width, height, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(img, Math.round(x), Math.round(y), width, height, null);
		}
		if(selected){
			g.setColor(Color.white);
			g.drawRect((int) this.x, (int) this.y, width, height);
		}
	}

	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int) this.x, (int) this.y, width, height);
	}

	@Override
	public Rectangle getBoundsUp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsDown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}
	/*private static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}*/
}
