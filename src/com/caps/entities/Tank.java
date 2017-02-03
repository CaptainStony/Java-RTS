package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Rectangle;

import com.caps.main.GameObject;
import com.caps.main.HUD;
import com.caps.main.Handler;
import com.caps.main.ID;

public class Tank extends GameObject{

	private Handler handler;
	
	public Tank(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;

	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		velY = 1;
		collision();
		
	}

	@Override
	public void render(Graphics g) {
			g.setColor(Color.green);
			g.fillRect((int)x, (int)y, 16, 16);
			g.setColor(Color.black);
			/*g.drawRect((int)x, (int)y, 2, 16);//left
			g.drawRect((int)x+14, (int)y, 2, 16);//right
			g.drawRect((int)x+2, (int)y, 12, 2);//up
			g.drawRect((int)x+2, (int)y+14, 12, 2);//down
			 */
	}
	private void collision(){

		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Building){
				if(tempObject.getBoundsTotal().intersects(getBoundsUp())){
					y--;
				}else if (tempObject.getBoundsTotal().intersects(getBoundsDown())){
					y++;
				}else if (tempObject.getBoundsTotal().intersects(getBoundsLeft())){
					x--;
				}else if (tempObject.getBoundsTotal().intersects(getBoundsUp())){
					x++;
				}
			}
		}

	}
	@Override
	public Rectangle getBoundsUp() {
		return new Rectangle((int)x+2, (int)y, 12, 2);
	}
	@Override
	public Rectangle getBoundsDown() {
		return new Rectangle((int)x+2, (int)y+14, 12, 2);
	}
	@Override
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y, 2, 16);
	}
	@Override
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+14, (int)y, 2, 16);
	}

	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 16, 16);

	}
}
