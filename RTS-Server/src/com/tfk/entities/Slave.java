package com.tfk.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import com.tfk.main.GameObject;
import com.tfk.main.Grid;
import com.tfk.main.GridCell;
import com.tfk.main.Handler;
import com.tfk.main.ID;
import com.tfk.structures.TownCenter;

import javax.imageio.ImageIO;

public class Slave extends GameObject{

	private Handler handler;
	private boolean first = true;
	
	private int carry = 0;
	protected int width = 0;
    protected int height = 0;
	private Grid grid;

	public Slave(float x, float y, ID id, Handler handler,Grid grid, String owner) {
		super(x, y, id, owner);
		baseSpeed = 2;
		this.handler = handler;
		this.grid = grid;
		Health = 100;
	}
	long time = System.currentTimeMillis();
	long future;
	
	private LinkedList<GridCell> pathToResource;
	private LinkedList<GridCell> pathToBase;
	
	public boolean goToResource;
	public boolean goToBase;
	
	@Override
	public void tick() {
		time = System.currentTimeMillis();
		x += velX;
		y += velY;
		if(Health <= 0){
			handler.removeObject(this);
		}
		//RESOURCE
		if(interactedResource != null){

			if(getBoundsTotal().intersects(base.getBoundsDrop())){
				if(interactedResource.isResource == RESOURCE.Wood){
					HUD.WOOD += carry;
					carry = 0;
				}else if (interactedResource.isResource == RESOURCE.Food){
					HUD.FOOD += carry;
					carry = 0;
				}else if (interactedResource.isResource == RESOURCE.Gold){
					HUD.GOLD += carry;
					carry = 0;
				}
			}
			//System.out.println("handling resource");
			if(goToResource == true){
				int resourceX = (int) ((interactedResource.getX() + interactedResource.getBoundsTotal().getMaxX())/2);
				int resourceY = (int) ((interactedResource.getY() + interactedResource.getBoundsTotal().getMaxY())/2);
				
				int thisX = (int) ((getX() + getBoundsTotal().getMaxX())/2);
				int thisY = (int) ((getY() + getBoundsTotal().getMaxY())/2);

				pathToResource = grid.calculatePath(grid.findGridCellByXAndY(thisX, thisY), grid.findGridCellByXAndY(resourceX, resourceY), this);
				setPath(pathToResource);
				goToResource = false;
			}else if (goToBase == true){
				pathToBase = grid.calculatePath(grid.findGridCellByXAndY((int)x, (int)y), grid.findGridCellByXAndY((int)base.getBoundsDrop().getMaxX(), (int)base.getBoundsDrop().getMaxY()), this);
				setPath(pathToBase);
				goToBase = false;
			}
			if(interactedResource.getBoundsTotal().intersects(getBoundsTotal())){
				if(first == true){
					first = false;
					future = time + 1500;
				}
				if(time >= future && goToBase == false){
					first = true;
					interactedResource.setHealth(interactedResource.getHealth()-1);
					if(carry >= 3){
						goToBase = true;
					}else if(interactedResource.getHealth() <= 0){
						goToBase = true;
						interactedResource = null;
					}else{
						carry++;
					}
				}
			}else if(base.getBoundsDrop().intersects(getBoundsTotal())){
				goToResource = true;
			}

			
		}
		if(path != null){
			grid.followPath(path, this);
		}
		
	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 20, 40);

	}
	public int getCarry(){
		return carry;
	}
	public void setCarry(int carry){
		this.carry = carry;
	}
}
