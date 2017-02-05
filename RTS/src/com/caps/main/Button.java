package com.caps.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button extends GameObject{

	protected static enum TYPE{
		Slave(), Tank(),
	}
	protected TYPE type;
	public Button(float x, float y, ID id, TYPE type) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.type = type;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g, int bx, int by) {
		g.setColor(Color.black);
		g.drawRect((int) bx, (int) by, 50, 50);
		this.x = bx;
		this.y = by;
	}

	@Override
	public Rectangle getBoundsTotal() {
		
		return new Rectangle((int) this.x, (int) this.y, 50, 50);
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

	@Override
	public void render(Graphics g) {
		
	}

	

}
