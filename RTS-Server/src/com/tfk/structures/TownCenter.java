package com.tfk.structures;

import java.awt.Rectangle;

import com.tfk.main.Coordinate;
import com.tfk.main.Handler;
import com.tfk.main.Queue;

public class TownCenter extends BuildingObject{

	private Queue queue;
	private Handler handler;
	public Integer timer = null;
	private Coordinate rallyPoint = null;
	public TownCenter(float x, float y, BUILDINGTYPE type, Handler handler) {
		super((int) x, (int) y, handler, type);
		this.handler = handler;
		this.queue = new Queue(this.handler);
	}

	
	public void tick() {

		if(queue.getQueueSize() > 0 && timer == null){
			timer = queue.getFirstTime()*60;
		}else if(queue.getQueueSize() > 0 && timer > 0){
			timer--;
		}else if(queue.getQueueSize() > 0 && timer <= 0){
			queue.removeFromQueue();
			if(queue.getQueueSize() > 0){
				timer = queue.getFirstTime()*60;
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
	public Coordinate getRallyPoint(){
		return rallyPoint;
	}
	public void setRallyPoint(Coordinate rallyPoint){
		this.rallyPoint = rallyPoint;
	}

}