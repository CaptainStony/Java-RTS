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
		this.type = type;
	}

	@Override
	public void tick() {
		
	}

	public void render(Graphics g, int bx, int by) {
		g.setColor(Color.black);
		g.drawRect((int) bx, (int) by, 50, 50);
		this.x = bx;
		this.y = by;
		if(this.type == TYPE.Slave){
			g.drawString("Buy a",(int) bx + 8, (int) by + 20);
			g.drawString("slave",(int) bx + 8, (int) by + 40);
		}
	}

	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int) this.x, (int) this.y, 50, 50);
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
	public void render(Graphics g) {
		
	}

	

}
