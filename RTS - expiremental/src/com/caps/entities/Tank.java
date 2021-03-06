package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.caps.main.GameObject;
import com.caps.main.Grid;
import com.caps.main.Handler;
import com.caps.main.ID;

public class Tank extends GameObject{

	private Grid grid;
	
	public Tank(float x, float y, ID id, Handler handler, Grid grid, int objID) {
		super(x, y, id, objID);
		baseSpeed = 2;
		this.grid = grid;
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		if(path != null){
			grid.followPath(path,this);

		}
	}
	@Override
	public void render(Graphics g) {
		if(path != null && selected == true){
			for (int i = 1; i < path.size(); i++) {

				g.setColor(Color.yellow);
				g.drawLine((int)path.get(i).getX(), (int)path.get(i).getY(), (int)path.get(i-1).getX(), (int)path.get(i-1).getY());

			}
		}
		
		//Made by ThaFartKnightİ
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


	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x-10, (int)y-20, 45, 70);

	}
}
