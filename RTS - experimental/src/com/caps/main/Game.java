package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import com.caps.entities.Slave;
import com.caps.entities.Tank;
import com.caps.entities.TownCenter;
import com.caps.resource.Gold;
import com.caps.resource.Wood;

public class Game extends Canvas implements Runnable{

	protected Handler handler;
	public MouseInput mouseinput;

	//private Random r;
	private HUD hud;
	private WorldGeneration worldGenerator;
	private PathFinder pathFinder;
	
	private static final long serialVersionUID = 1L;
	
	public int cameraX = 0;
	public int cameraY = 0;

	public LinkedList<GameObject> selectedObject = new LinkedList<GameObject>();
	
	public static final int WIDTH = 1080, HEIGHT = WIDTH /12 * 9;
	private Thread thread;
	private boolean running = false;
	public boolean paused = false;
	
	private boolean first = true;
	
	public enum STATE{
		Game,
	};
	
	public STATE gameState = STATE.Game;
	
	public Game(){
		
		handler = new Handler();
		worldGenerator = new WorldGeneration(handler, this);
		pathFinder = new PathFinder();
		this.addKeyListener(new KeyInput(handler, this));

		mouseinput = new MouseInput(this, handler);
		this.addMouseListener(mouseinput);
		
		Window w = new Window(WIDTH, HEIGHT, "RTS shit game", this);
		handler.addObject(new Tank(WIDTH/2-40, HEIGHT/2-40, ID.Tank, handler));
		handler.addObject(new Slave(WIDTH/2+50, HEIGHT/2+50, ID.Slave, handler));
		handler.addObject(new Slave(WIDTH/2+30, HEIGHT/2+30, ID.Slave, handler));

		handler.addObject(new Wood(WIDTH/2+200, HEIGHT/2+200, ID.Resource, handler));
		handler.addObject(new Gold(WIDTH/2+200, HEIGHT/2+400, ID.Resource, handler));
		handler.addObject(new TownCenter(WIDTH/2, HEIGHT/2, ID.Base, handler));
		hud = new HUD(w);

		//worldGenerator.generateTerain();
		//worldGenerator.useTerrain();
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
	              }
	              if(running)
	                      render();
	              frames++;
	             
	              if(System.currentTimeMillis() - timer > 1000) {
	                      timer += 1000;
	                      System.out.println("FPS: " + frames);
	                      frames = 0;
	              }
	             
	      }
	      stop();
	}
	
	private void tick(){
		
		if(paused == false){
			handler.tick();
			hud.tick();

		}
	}
		
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		java.awt.Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.translate(cameraX, cameraY);

		if(gameState == STATE.Game){
			handler.render(g);
			hud.render(g,this);
			pathFinder.render(g);
		}
		if(paused == true){
			
		}
		if (first == true){
			first = false;
			for (int x = 0; x < pathFinder.grid.length; x++) {
				for (int y = 0; y < pathFinder.grid.length; y++) {
					handler.addCell(new Cell(x*20, y*20, x, y,handler,pathFinder));
					
				}
			}
			pathFinder.calculatePath(handler.cell.get(5),handler.cell.get(30),handler);
		}
		
		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
		
	public static void main(String args[]){
			new Game();
			
	}
	
}
