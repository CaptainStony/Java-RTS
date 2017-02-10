package com.caps.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.caps.main.Game;
import com.caps.main.GameObject;
import com.caps.main.Handler;
import com.caps.main.ID;
import com.caps.main.Location;
import com.caps.main.Queue;

public class TownCenter extends GameObject implements MouseListener{

	private Queue queue;
	private Handler handler;
	public Integer timer = null;
	private Location rallyPoint;
	private Game game;
	public TownCenter(float x, float y, ID id, Game game, Handler handler) {
		super(x, y, id);
		baseSpeed = 0;
		this.handler = handler;
		this.queue = new Queue(this.handler);
		this.game = game;
	}

	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		if(queue.getQueueSize() > 0 && timer == null){
			timer = queue.getFirstTime()*60;
			System.out.println("Timer initialized");
		}else if(queue.getQueueSize() > 0 && timer > 0){
			timer--;
		}else if(queue.getQueueSize() > 0 && timer <= 0){
			System.out.println("Spawning object");
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
				g.drawRect((int)x, (int)y, 105, 158);
			}

	}
	@Override
	public Rectangle getBoundsUp() {
		return new Rectangle((int)x+2, (int)y, 101, 2);
	}
	@Override
	public Rectangle getBoundsDown() {
		return new Rectangle((int)x+2, (int)y+156, 101, 2);
	}
	@Override
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y, 2, 154);
	}
	@Override
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+103, (int)y, 2, 154);
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


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int worldMouseX = e.getX()-game.cameraX;
		int worldMouseY = e.getY()-game.cameraY;
		System.out.println("test");
		if(this.selected && e.getButton() == 3){
			rallyPoint = new Location(worldMouseX, worldMouseY, game.getGrid());
			System.out.println(rallyPoint.toString());
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
