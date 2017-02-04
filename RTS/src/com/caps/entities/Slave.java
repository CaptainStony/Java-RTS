package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.caps.main.GameObject;
import com.caps.main.HUD;
import com.caps.main.Handler;
import com.caps.main.ID;
import com.caps.particles.MiningParticle;

public class Slave extends GameObject{

	private Handler handler;
	private boolean isHandling = false;
	private boolean goToResource = false;
	private boolean goToBase = false;
	private boolean first = true;
	
	private int carry = 0;
	
	public Slave(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		baseSpeed = 2;
		this.handler = handler;
	}
	long time = System.currentTimeMillis();
	long future;
	@Override
	public void tick() {
		time = System.currentTimeMillis();
		x += velX;
		y += velY;

		if(getBoundsTotal().intersects(handler.findObject(ID.Base).getBoundsTotal())){
			if(isResource == RESOURCE.Wood){
				HUD.WOOD += carry;
				carry = 0;
				isHandling = false;
			}else if (isResource == RESOURCE.Food){
				HUD.FOOD += carry;
				carry = 0;
				isHandling = false;
			}else if (isResource == RESOURCE.Gold){
				HUD.GOLD += carry;
				carry = 0;
				isHandling = false;
			}
		}
		if(goToBase == true){
			handler.goToCords(Math.round(handler.findObject(ID.Base).getX()), Math.round(handler.findObject(ID.Base).getY()), this);
		}
		if(goToResource == true){
			handler.goToCords(Math.round(interactedResource.getX()), Math.round(interactedResource.getY()), this);
			isHandling = true;
		}
		if(interactedResource != null && isHandling == false){
			goToBase = false;
			goToResource = true;
		}else if (interactedResource == null){
			goToBase = false;
			goToResource = false;
			isHandling = false;
			first = true;
		}
		if(interactedResource != null && getBoundsTotal().intersects(interactedResource.getBoundsTotal())){
			if(first == true){
				isResource = interactedResource.getResource();
				first = false;
				future = time + 1500;
			}
			if(time >= future){
				first = true;
				carry++;
				interactedResource.setHealth(interactedResource.getHealth()-1);
				for (int i = 0; i < 20; i++) {
					handler.addObject(new MiningParticle(x+10+randInt(-5, 5), y+20+randInt(-5, 5), ID.Particle, handler,interactedResource.getResource()));
					
				}
				if(carry >= 15 || interactedResource.getHealth() <= 0){
					goToResource = false;
					goToBase = true;
				}
			}
		}
		/*if(interactedResource != null && goToBase == false){
			goToResource = true;
			isHandling = true;
			handler.goToCords(Math.round(interactedResource.getX()), Math.round(interactedResource.getY()), this);
		
		}else goToResource = false;
		if(isHandling == true){
			if (getBoundsTotal().intersects(interactedResource.getBoundsTotal())){
				isMining = true;
				time = System.currentTimeMillis();
				if(first == true){
					isResource = interactedResource.getResource();
					first = false;
					future = time + 1000;
				}
				if(time > future){
					first = true;
					carry++;
					System.out.println(carry);
					if(carry >= 5){
						goToResource = false;
						goToBase = true;
						isMining = false;
						first = true;
					}
				}
			}
		}*/
		collision();
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
		g.setColor(Color.black);
		
		try {
			Image image = ImageIO.read(this.getClass().getResource("/slave.png"));
            int w = image.getWidth(null);
            int h = image.getHeight(null);
            g.drawImage(image,Math.round(x),Math.round(y), w/7, h/7, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Made by ThaFartKnight©
		//Peasant
		//Body
	/*
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 18, 28);
		g.setColor(Color.black);
		g.fillRect((int) x, (int) y, -4, 28);
		g.fillRect((int) x+18, (int) y, 4, 28);
		g.fillRect((int) x, (int) y-4, 18, 4);
		g.fillRect((int) x, (int) y + 28, 18, 4);
		g.setColor(new Color(102, 65, 32, 255));
		g.fillRect((int) x, (int) y + 14, 8, 5);
		g.fillRect((int) x + 4, (int) y + 18, 8, 8);
		g.fillRect((int) x + 18, (int) y + 2, -6, 10);
		g.fillRect((int) x + 18, (int) y + 2, -6, 10);
		g.fillRect((int) x + 12, (int) y + 6, -3, 4);
		g.fillRect((int) x + 18, (int) y + 12, -3, 6);
		//Left Hand
		g.setColor(Color.white);
		g.fillRect((int) x-13, (int) y + 22, 6, 6);
		g.setColor(Color.black);
		g.fillRect((int) x-13, (int) y + 19, 6, 3);
		g.fillRect((int) x-13, (int) y + 27, 6, 3);
		g.fillRect((int) x-13, (int) y + 22, -3, 6);
		g.fillRect((int) x-7, (int) y + 22, 3, 6);
		//Right hand
		g.setColor(Color.white);
		g.fillRect((int) x+25, (int) y + 22, 6, 6);
		g.setColor(Color.black);
		g.fillRect((int) x+25, (int) y + 19, 6, 3);
		g.fillRect((int) x+25, (int) y + 27, 6, 3);
		g.fillRect((int) x+25, (int) y + 22, -3, 6);
		g.fillRect((int) x+31, (int) y + 22, 3, 6);
		//Head
		g.setColor(Color.white);
		g.fillRect((int) x-8, (int) y -20, 15, 15);
		g.setColor(Color.black);
		g.fillRect((int) x-8, (int) y -14, 4, 4);
		g.fillRect((int) x , (int) y -14, 4, 4);
		g.fillRect((int) x-8, (int) y -20, 3, 3);
		g.fillRect((int) x-8, (int) y -20, -3, 13);
		g.fillRect((int) x-8, (int) y -7, 8, 4);
		g.fillRect((int) x-8, (int) y -20, 12, -3);
		g.fillRect((int) x+4, (int) y -20, 3, 3);
		g.fillRect((int) x+7, (int) y -20, 3, 16);
		//Left foot
		g.fillRect((int) x+2, (int) y + 32, 4, 6);
		g.fillRect((int) x+4, (int) y + 38, -6, 3);
		//Right foot
		g.fillRect((int) x+13, (int) y + 32, 4, 6);
		g.fillRect((int) x+15, (int) y + 38, -6, 3);*/
		if (selected == true){
			g.setColor(Color.white);
			g.drawRect((int)x, (int)y, 20, 40);

		}
		
	}
	private void collision(){

		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Base){
				if(getBoundsUp().intersects(tempObject.getBoundsTotal())){
					y = tempObject.getY()+33;
				}else if (getBoundsDown().intersects(tempObject.getBoundsTotal())){
					y = tempObject.getY()-40;
				}else if (getBoundsLeft().intersects(tempObject.getBoundsTotal())){
					x = tempObject.getX()+33;
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
}
