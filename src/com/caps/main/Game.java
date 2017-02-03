package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;

import com.caps.entities.Tank;
import com.caps.entities.TownCenter;

public class Game extends Canvas implements Runnable{

	private Handler handler;
	public MouseInput mouseinput;

	//private Random r;
	private HUD hud;
	private Menu menu;

	private static final long serialVersionUID = 1L;
	
	public int cameraX = 0;
	public int cameraY = 0;

	public static final int WIDTH = 1080, HEIGHT = WIDTH /12 * 9;
	private Thread thread;
	private boolean running = false;
	public boolean paused = false;
	
	public enum STATE{
		Game,
	};
	
	public STATE gameState = STATE.Game;
	
	public Game(){
		
		handler = new Handler();
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler, this));

		mouseinput = new MouseInput(this, handler);
		this.addMouseListener(mouseinput);

		if(!(gameState == STATE.Game))	this.addMouseListener(menu);	

		new Window(WIDTH, HEIGHT, "Needs Awesome Title", this);
		handler.addObject(new Tank(WIDTH/2, HEIGHT/2, ID.Tank, handler));
		handler.addObject(new TownCenter(WIDTH/2, HEIGHT/2, ID.Building, handler));
		hud = new HUD();

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
		}
		if(paused == true){
			
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
