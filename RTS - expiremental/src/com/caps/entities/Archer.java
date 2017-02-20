package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.caps.main.GameObject;
import com.caps.main.Grid;
import com.caps.main.Handler;
import com.caps.main.ID;

public class Archer extends GameObject{

	private Handler handler;
	
	private Image img = null;

	protected int width = 0;
    protected int height = 0;
	private Grid grid;
	public GameObject target;
	
	private long time = System.currentTimeMillis();
	private long future;
	private boolean first = true;
	public Archer(float x, float y, ID id, Handler handler,Grid grid) {
		super(x, y, id);
		baseSpeed = 3;
		this.handler = handler;
		this.grid = grid;
		Health = 150;
		
	}

	@Override
	public void tick() {
		time = System.currentTimeMillis();
		x += velX;
		y += velY;
		
		if(Health <= 0){
			handler.removeObject(this);
		}
		if(path != null){
			grid.followPath(path, this);
		}
		if(target != null){

			if(getRange().intersects(target.getBoundsTotal())){
				if(first == true){
					first = false;
					future = time + 1500;
				}
				if(time >= future){
					first = true;
					handler.addObject(new Projectile(x, y, (int)(target.getX()+target.getBoundsTotal().getMaxX())/2, (int)(target.getY()+target.getBoundsTotal().getMaxY())/2, ID.Projectile, handler,target,5));
					if(target.getHealth() <=0){
						target = null;
					}
				}	
			}else{
				//System.out.println("not in range retard");
			}
		}
		
	}
	
	public void render(Graphics g) {
	      Graphics2D g2 = (Graphics2D) g;

		if(path != null && selected == true){
			for (int i = 0; i < path.size(); i++) {
				g.setColor(Color.yellow);
				if(i+1 == path.size()){
					g.drawLine((int)path.get(i).getX(), (int)path.get(i).getY(), (int)path.get(i).getX(), (int)path.get(i).getY());
				}else{
					g.drawLine((int)path.get(i).getX(), (int)path.get(i).getY(), (int)path.get(i+1).getX(), (int)path.get(i+1).getY());

					
				}

			}
		}
		
		if(img == null){
			try {
				img = ImageIO.read(this.getClass().getResource("/archer.png"));
	            width = img.getWidth(null);
	            height = img.getHeight(null);
	            g.drawImage(img,Math.round(x),Math.round(y), width/8, height/8, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(img, Math.round(x), Math.round(y), width/8, height/8, null);
		}
		

		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, 33, 33);
			//g.drawOval((int)x-width/2-50, (int) y-height/2-50, 400, 400);
			g2.draw(getRange());

		}
		
	}
	public Area getRange() {
		return new Area(new Ellipse2D.Double(x-240,y-240,500,500));

	}
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 33, 33);

	}

}
