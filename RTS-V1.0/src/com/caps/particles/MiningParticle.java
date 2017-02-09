package com.caps.particles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;

public class MiningParticle extends GameObject{

	private Handler handler;
	
	public MiningParticle(float x, float y, ID id, Handler handler, RESOURCE resource) {
		super(x, y, id);
		isResource = resource;
		baseSpeed = 0;
		Health = 30;
		this.handler = handler;
		velY = -1;
		velX = (float)randInt(-6, 6)/10;
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
		}
		Health--;
	}
	
	@Override
	public void render(Graphics g) {
		if(isResource == RESOURCE.Wood){
			g.setColor(new Color(102, 65, 32, 255));			
		}else if (isResource == RESOURCE.Food){
			g.setColor(new Color(135, 69, 64, 255));			
		}else if (isResource == RESOURCE.Gold){
			g.setColor(new Color( 226, 166, 68, 255));			
		}
		g.fillRect((int)x, (int)y, 5, 5);
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
		return new Rectangle((int)x, (int)y, 5, 5);

	}
}
