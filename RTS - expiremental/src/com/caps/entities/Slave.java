package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.caps.main.GameObject;
import com.caps.main.Grid;
import com.caps.main.GridCell;
import com.caps.main.HUD;
import com.caps.main.Handler;
import com.caps.main.ID;
import com.caps.particles.MiningParticle;

public class Slave extends GameObject{

	private Handler handler;
	private boolean first = true;
	private int carry = 0;
	private Image img = null;
	protected int width = 0;
    protected int height = 0;
	private Grid grid;

	public Slave(float x, float y, ID id, Handler handler,Grid grid) {
		super(x, y, id);
		baseSpeed = 2;
		this.handler = handler;
		this.grid = grid;
	}
	long time = System.currentTimeMillis();
	long future;
	@Override
	public void tick() {
		time = System.currentTimeMillis();
		x += velX;
		y += velY;
		if(interactedResource != null){
			GameObject base = handler.findObject(ID.Base);
			LinkedList<GridCell> pathToResource = grid.calculatePath(grid.findGridCellByXAndY((int)x, (int)y), grid.findGridCellByXAndY((int)interactedResource.getX(), (int)interactedResource.getY()), this);
			//LinkedList<GridCell> pathToBase = grid.calculatePath(grid.findGridCellByXAndY((int)x, (int)y), grid.findGridCellByXAndY((int)base.getX(), (int)base.getY()), this);

			if(getBoundsTotal().intersects(interactedResource.getBoundsTotal())){
				if(first == true){
					first = false;
					future = time + 1500;
				}
				if(time >= future){
					first = true;
					interactedResource.setHealth(interactedResource.getHealth()-1);
					for (int i = 0; i < 15; i++) {
						handler.addObject(new MiningParticle(x+10+randInt(-5, 5), y+20+randInt(-5, 5), ID.Particle, handler,interactedResource.getResource()));
						
					}
					System.out.println(carry);
					if(carry >= 15){
						//setPath(pathToBase);
					}else if(interactedResource.getHealth() <= 0){
						//setPath(pathToBase);
						path = null;
						interactedResource = null;
					}else{
						carry++;
					}
				}
			}else if(getBoundsTotal().intersects(handler.findObject(ID.Base).getBoundsTotal())){
				if(isResource == RESOURCE.Wood){
					HUD.WOOD += carry;
					carry = 0;
				}else if (isResource == RESOURCE.Food){
					HUD.FOOD += carry;
					carry = 0;
				}else if (isResource == RESOURCE.Gold){
					HUD.GOLD += carry;
					carry = 0;
				}
				setPath(pathToResource);

			}else{
				setPath(pathToResource);
			}
		}
		
		if(path != null){
			grid.followPath(path,this);

		}
		
	}
	
	private static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public void render(Graphics g) {
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
				img = ImageIO.read(this.getClass().getResource("/peasant.png"));
	            width = img.getWidth(null);
	            height = img.getHeight(null);
	            g.drawImage(img,Math.round(x),Math.round(y), width/2, height/2, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			g.drawImage(img, Math.round(x), Math.round(y), width/2, height/2, null);
		}
		

		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, this.width/2, this.height/2);

		}
		
	}
	private void collision(){

		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Base){
				if(getBoundsUp().intersects(tempObject.getBoundsTotal())){
					y = tempObject.getY()+158;
				}else if (getBoundsDown().intersects(tempObject.getBoundsTotal())){
					y = tempObject.getY()-40;
				}else if (getBoundsLeft().intersects(tempObject.getBoundsTotal())){
					x = tempObject.getX()+106;
				}else if (getBoundsRight().intersects(tempObject.getBoundsTotal())){
					x = tempObject.getX()-20;
				}
			}
		}

	}
	@Override
	public Rectangle getBoundsUp() {
		return new Rectangle((int)x+2, (int)y, 16, 2);
	}
	@Override
	public Rectangle getBoundsDown() {
		return new Rectangle((int)x+2, (int)y+38, 16, 2);
	}
	@Override
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y, 2, 40);
	}
	@Override
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+18, (int)y, 2, 40);
	}
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 20, 40);

	}
	public int getCarry(){
		return carry;
	}
}
