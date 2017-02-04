package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;

public class TownCenter extends GameObject{

	private Handler handler;
	
	public TownCenter(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		baseSpeed = 0;
		this.handler = handler;

	}

	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		
	}

	@Override
	public void render(Graphics g) {
			g.setColor(Color.black);
			g.fillRect((int)x, (int)y, 32, 32);
			g.setColor(Color.white);
			if (selected == true){
				g.drawRect((int)x, (int)y, 32, 32);
			}
		/*	g.drawRect((int)x, (int)y+1, 2, 30);//left
			g.drawRect((int)x+30, (int)y+1, 2, 30);//right
			g.drawRect((int)x+2, (int)y, 28, 2);//up
			g.drawRect((int)x+2, (int)y+30, 28, 2);//down
		 */
	}
	@Override
	public Rectangle getBoundsUp() {
		return new Rectangle((int)x+2, (int)y, 28, 2);
	}
	@Override
	public Rectangle getBoundsDown() {
		return new Rectangle((int)x+2, (int)y+30, 28, 2);
	}
	@Override
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+1, 2, 30);
	}
	@Override
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+30, (int)y+1, 2, 30);
	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
