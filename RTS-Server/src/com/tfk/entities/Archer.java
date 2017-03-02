package com.tfk.entities;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import com.tfk.main.GameObject;
import com.tfk.main.Grid;
import com.tfk.main.Handler;
import com.tfk.main.ID;
import com.tfk.main.Server;


public class Archer extends GameObject{

	private Handler handler;
	
	protected int width = 0;
    protected int height = 0;
	private Grid grid;
	public GameObject target;
	private Server server;
	
	private long time = System.currentTimeMillis();
	private long future;
	private boolean first = true;
	public Archer(float x, float y, ID id, Handler handler, Grid grid, Server server,String owner) {
		super(x, y, id, owner);
		baseSpeed = 3;
		this.server = server;
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
					//Arrow here
				if(target.getHealth() <=0){
						target = null;
					}
				}	
			}else{
				//System.out.println("not in range retard");
			}
		}
		
	}
	
	public Area getRange() {
		return new Area(new Ellipse2D.Double(x-240,y-240,500,500));

	}
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 33, 33);

	}

}
