package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;
import com.caps.particles.BloodParticle;

public class Projectile extends GameObject{

	private Handler handler;
	
	protected int width = 0;
    protected int height = 0;
	public GameObject target;
	private int timer = 400;
	private boolean hit = false;
	private LinkedList<GameObject> pointers = new LinkedList<GameObject>();
	
	private int damage;
	
	public Projectile(float x, float y,int endX,int endY, ID id, Handler handler,GameObject target,int damage) {
		super(x, y, id);
		baseSpeed = 6;
		this.handler = handler;
		this.target = target;
		this.damage = damage;
		goToCords(endX, endY);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		timer--;

		if(timer <= 0){
			for (int i = 0; i < pointers.size(); i++) {
				handler.removeObject(pointers.get(i));
				
			}
			handler.removeObject(this);

		}
		
		if(getBoundsTotal().intersects(target.getBoundsTotal()) && hit == false){
			hit = true;
			target.setHealth(target.getHealth()-damage);
			for (int i = 0; i < 20; i++) {
				handler.addObject(new BloodParticle(target.getX() + randInt(-10, 10), target.getY() + randInt(-10, 10), ID.Particle, handler));				
			}
			handler.removeObject(this);

			for (int i = 0; i < pointers.size(); i++) {
				handler.removeObject(pointers.get(i));
				
			}

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
	public void goToCords(int endX,int endY){
		GameObject endPoint = new mousePoint(endX, endY, ID.MousePointer, handler);
		pointers.add(endPoint);
		float difX = endX - getX();
		float difY = endY - getY();
		float angle = (float) Math.atan(difY/difX);

		velX = (float) (baseSpeed * Math.cos(angle));
		velY = (float) (baseSpeed * Math.sin(angle));
		
		if(difX>0 && difY<0 || difX>0 && difY>0){
			velX = (float) (baseSpeed * Math.cos(angle));
			velY = (float) (baseSpeed * Math.sin(angle));
			
		}else if (difX<0 && difY<0 || difX<0 && difY>0){
			velX = (float) -(baseSpeed * Math.cos(angle));
			velY = (float) -(baseSpeed * Math.sin(angle));
		}
		if (getEndPoint() == null){
			handler.addObject(endPoint);
			setEndPoint(endPoint);
		}else{
			handler.removeObject(getEndPoint());
			setEndPoint(endPoint);
			handler.addObject(endPoint);
		}
	}
	public void render(Graphics g) {

		/*if(img == null){
			try {
				img = ImageIO.read(this.getClass().getResource("/arrow.png"));
	            width = img.getWidth(null);
	            height = img.getHeight(null);
	            g.drawImage(img,Math.round(x),Math.round(y), width/10, height/10, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			g.drawImage(img, Math.round(x), Math.round(y), width/10, height/10, null);
			
		}*/
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, 5, 5);
	}
	
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 5, 5);

	}

}
