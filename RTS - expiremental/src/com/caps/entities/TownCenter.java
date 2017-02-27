package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.caps.main.Game;
import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;
import com.caps.main.Location;
import com.caps.main.Queue;

public class TownCenter extends GameObject{

	private Queue queue;
	private Handler handler;
	public Integer timer = null;
	private Location rallyPoint = null;
	private RallyFlag rf = null;
	public TownCenter(float x, float y, ID id, Game game, Handler handler, int objID) {
		super(x, y, id, objID);
		this.handler = handler;
		this.queue = new Queue(this.handler);
	}

	
	@Override
	public void tick() {

		if(queue.getQueueSize() > 0 && timer == null){
			timer = queue.getFirstTime()*60;
			//System.out.println("Timer initialized");
		}else if(queue.getQueueSize() > 0 && timer > 0){
			timer--;
		}else if(queue.getQueueSize() > 0 && timer <= 0){
			//System.out.println("Spawning object");
			queue.removeFromQueue();
			if(queue.getQueueSize() > 0){
				timer = queue.getFirstTime()*60;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		try {
			Image image = ImageIO.read(this.getClass().getResource("/base.png"));
            int w = image.getWidth(null);
            int h = image.getHeight(null);
            g.drawImage(image,Math.round(x),Math.round(y), w/2, h/2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setColor(Color.white);
		if (selected){
			g.drawRect((int)x, (int)y, getBoundsTotal().width, getBoundsTotal().height);
			if(rallyPoint != null){
				if(rf == null){
					rf = new RallyFlag(rallyPoint);
					rf.render(g, rallyPoint);
				}else{
					rf.render(g, rallyPoint);
				}
			}
		}

	}
	
	public Rectangle getBoundsDrop() {
		return new Rectangle((int)x+56, (int)y+157, 20, 20);
	}
	public Rectangle getBoundsUp() {
		return new Rectangle((int)x+2, (int)y, 101, 2);
	}
	
	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 105, 158);
	}
	public Queue getQueue(){
		return this.queue;
	}
	public Location getRallyPoint(){
		return rallyPoint;
	}
	public void setRallyPoint(Location rallyPoint){
		this.rallyPoint = rallyPoint;
	}

}