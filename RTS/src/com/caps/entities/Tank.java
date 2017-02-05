package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;

public class Tank extends GameObject{

	private Handler handler;
	
	public Tank(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		baseSpeed = 2;
		this.handler = handler;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;

		collision();
	}
	@Override
	public void render(Graphics g) {
		//Made by ThaFartKnight©
		//Graphics2D g2d = (Graphics2D)g;
		//AffineTransform old = g2d.getTransform();
		//AffineTransform newt = new AffineTransform();
		//newt.rotate(Math.toRadians(10),x+45/2,y+70/2);
		//g2d.transform(newt);
		//Made by ThaFartKnight©
		g.setColor(new Color(17, 71, 17, 255));
		//body
		g.fillRect((int) x, (int) y, 26, 48);
		g.fillRect((int) x+2, (int) y + 48, 22, 2);
		g.fillRect((int) x+2, (int) y -2, 22, 2);
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x-6, (int) y+1, 6, 46);
		g.fillRect((int) x+26, (int) y+1, 6, 46);
		g.setColor(Color.black);
		g.fillRect((int) x-8, (int) y+1, 2, 46);
		g.fillRect((int) x+32, (int) y+1, 2, 46);
		//tracks left
		g.fillRect((int) x - 6, (int)y + 4, 6, 2);
		g.fillRect((int) x - 6, (int)y + 8, 6, 2);
		g.fillRect((int) x - 6, (int)y + 12, 6, 2);
		g.fillRect((int) x - 6, (int)y + 16, 6, 2);
		g.fillRect((int) x - 6, (int)y + 20, 6, 2);
		g.fillRect((int) x - 6, (int)y + 24, 6, 2);
		g.fillRect((int) x - 6, (int)y + 28, 6, 2);
		g.fillRect((int) x - 6, (int)y + 32, 6, 2);
		g.fillRect((int) x - 6, (int)y + 36, 6, 2);
		g.fillRect((int) x - 6, (int)y + 40, 6, 2);
		g.fillRect((int) x - 6, (int)y + 44, 6, 2);
		g.fillRect((int) x - 7, (int)y , 7, 2);
		g.fillRect((int) x - 7, (int)y + 46, 7, 2);
		//tracks right
		g.fillRect((int) x + 26, (int)y + 4, 6, 2);
		g.fillRect((int) x + 26, (int)y + 8, 6, 2);
		g.fillRect((int) x + 26, (int)y + 12, 6, 2);
		g.fillRect((int) x + 26, (int)y + 16, 6, 2);
		g.fillRect((int) x + 26, (int)y + 20, 6, 2);
		g.fillRect((int) x + 26, (int)y + 24, 6, 2);
		g.fillRect((int) x + 26, (int)y + 28, 6, 2);
		g.fillRect((int) x + 26, (int)y + 32, 6, 2);
		g.fillRect((int) x + 26, (int)y + 36, 6, 2);
		g.fillRect((int) x + 26, (int)y + 40, 6, 2);
		g.fillRect((int) x + 26, (int)y + 44, 6, 2);
		g.fillRect((int) x + 26, (int)y , 7, 2);
		g.fillRect((int) x + 26, (int)y + 46, 7, 2);
		//Canon
		g.fillRect((int) x + 6, (int) y + 40, 14, 2);
		g.fillRect((int) x + 4, (int) y + 40, 2, -15);
		g.fillRect((int) x + 6, (int) y + 23, 14, 2);
		g.fillRect((int) x + 20, (int) y + 40, 2, -15);
		g.fillRect((int) x + 9, (int) y + 23, 2, -35);
		g.fillRect((int) x + 15, (int) y + 23, 2, -35);
		g.fillRect((int) x + 10, (int) y - 12, -3, -2);
		g.fillRect((int) x + 16, (int) y - 12, +3, -2);
		g.fillRect((int) x + 7, (int) y - 14, -2, -4);
		g.fillRect((int) x + 19, (int) y - 14, 2, -4);
		g.fillRect((int) x + 19, (int) y - 18, -12, -2);
		g.setColor(new Color(17, 71, 17, 255));
		g.fillRect((int) x + 15, (int) y - 18, -4, 20);
		g.fillRect((int) x + 19, (int) y - 14, -12, -4);
		g.fillRect((int) x + 16, (int) y - 12, -6, -2);
		//g2d.setTransform(old);

		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x-10, (int)y-20, 45, 70);
		}
	}
	private void collision(){

		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Base){
				if(getBoundsUp().intersects(tempObject.getBoundsTotal())){
					y = tempObject.getY()+178;
				}else if (getBoundsDown().intersects(tempObject.getBoundsTotal())){
					y = tempObject.getY()-50;
				}else if (getBoundsLeft().intersects(tempObject.getBoundsTotal())){
					x = tempObject.getX()+116;
				}else if (getBoundsRight().intersects(tempObject.getBoundsTotal())){
					x = tempObject.getX()-36;
				}
			}
		}

	}
	@Override
	public Rectangle getBoundsUp() {
		return new Rectangle((int)x-8, (int)y-20, 41, 2);
	}
	@Override
	public Rectangle getBoundsDown() {
		return new Rectangle((int)x-8, (int)y+50, 41, 2);
	}
	@Override
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x-10, (int)y-20, 2, 70);
	}
	@Override
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+33, (int)y-20, 2, 70);
	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x-10, (int)y-20, 45, 70);

	}
}
