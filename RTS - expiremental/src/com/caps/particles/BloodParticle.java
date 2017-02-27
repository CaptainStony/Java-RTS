package com.caps.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;

public class BloodParticle extends GameObject{

	private Handler handler;
	private Color color;
	public BloodParticle(float x, float y, ID id, Handler handler, int objID) {
		super(x, y, id, objID);
		baseSpeed = 0;
		Health = 100;
		this.handler = handler;
		velY = -1;
		velX = (float)randInt(-6, 6)/10;
		color = new Color(randInt(62, 255), 0, 0);
	}
	private static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	@Override
	public void tick() {
		x += velX;
		y += velY;
		velY +=0.1;
		if(Health <= 0){
			handler.removeObject(this);
		}else if(Health < 70){
			velX = 0;
			velY = 0;
		}
		Health--;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, 5, 5);
	}

	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 5, 5);

	}
}
