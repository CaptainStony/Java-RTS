package com.caps.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;

public class mousePoint extends GameObject{

	//private Handler handler;
	
	public mousePoint(float x, float y, ID id, Handler handler, int objID) {
		super(x, y, id, objID);
		baseSpeed = 0;
		//this.handler = handler;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;

		collision();
	}
	@Override
	public void render(Graphics g) {
			/*g.setColor(Color.black);
			g.fillRect((int)x, (int)y, 4, 4);*/
			
	}
	private void collision(){

		/*for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getEndPoint() == this){
				if(tempObject.getBoundsTotal().intersects(getBoundsTotal())){
					tempObject.setVelX(0);
					tempObject.setVelY(0);
					tempObject.setEndPoint(null);
					handler.removeObject(this);	
				}
			}
		}*/

	}

	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 4, 4);

	}
}
