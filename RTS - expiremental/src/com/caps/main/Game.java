package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import com.caps.entities.BuildingObject;
import com.caps.entities.Sheep;
import com.caps.entities.Slave;
import com.caps.entities.Tank;
import com.caps.entities.TownCenter;
import com.caps.entities.Wall;
import com.caps.resource.Gold;
import com.caps.resource.Wood;

public class Game extends Canvas implements Runnable{

	protected Handler handler;
	public MouseInput mouseinput;

	//private Random r;
	protected HUD hud;
	private static final long serialVersionUID = 1L;
	
	public int cameraX = 0;
	public int cameraY = 0;

	public LinkedList<GameObject> selectedObject = new LinkedList<GameObject>();
	
	public static final int WIDTH = 1080, HEIGHT = WIDTH /12 * 9;
	private Thread thread;
	private boolean running = false;
	protected Grid grid;
	public boolean paused = false;
		
	public enum STATE{
		Game,
	};
	
	public STATE gameState = STATE.Game;
	
	public Game(){
		handler = new Handler();
		grid = new Grid(handler);

		this.addKeyListener(new KeyInput(handler, this));

		mouseinput = new MouseInput(this, handler,grid);
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		new Window(WIDTH, HEIGHT, "RTS shit game", this);
		handler.addObject(new TownCenter(WIDTH/2, HEIGHT/2-5, ID.Base ,this, handler));
		handler.addObject(new Tank(WIDTH/2-40, HEIGHT/2-40, ID.Tank, handler,grid));
		handler.addObject(new Slave(WIDTH/2+200, HEIGHT/2+50, ID.Slave, handler,grid));
		handler.addObject(new Slave(WIDTH/2+300, HEIGHT/2+30, ID.Slave, handler,grid));

		handler.addObject(new Sheep(WIDTH/2 - 100, HEIGHT/2+50, ID.Sheep, handler));
		
		
		hud = new HUD(this);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run (){
		//GAME LOOP 
	      this.requestFocus();
	      long lastTime = System.nanoTime();
	      double amountofTicks = 60.0;
	      double ns = 1000000000 / amountofTicks;
	      double delta = 0;
	      long timer = System.currentTimeMillis();
	      int frames = 0;
	      while(running) {
	              long now = System.nanoTime();
	              delta += (now - lastTime) / ns;
	              lastTime = now;
	              while(delta >= 1) {
	                      tick();
	                      delta--;
	                      render();
	                      frames++;
	              }
	             
	              if(System.currentTimeMillis() - timer > 1000) {
	                      timer += 1000;
	                      //System.out.println("FPS: " + frames);
	                      frames = 0;
	              }
	             
	      }
	      stop();
	}
	
	private void tick(){
		grid.loadGrid();

		if(!paused){
			handler.tick();
			//hud.tick(); <3
		}

	}
		
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);	
		g.translate(cameraX, cameraY);

		if(gameState == STATE.Game){
			for (int i = 0; i < 75; i++) {
				for (int j = 0; j < 75; j++) {
				grid.gridCells[i][j].render(g);
				}
			}

			handler.render(g);
			hud.render(g,this);

		}
		if(paused){
			
		}
		/*g.setColor(Color.black);
		g.drawLine(0, -9999, 0, 9999);
		g.drawLine(-9999, 0, 9999, 0);*/

		g.dispose();
		bs.show();
	}	
	public static void main(String args[]){
			new Game();	
	}
}
