package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.caps.entities.Archer;
import com.caps.entities.Sheep;
import com.caps.entities.Slave;
import com.caps.entities.Tank;

public class Game extends Canvas implements Runnable{

	protected Handler handler;
	public MouseInput mouseinput;

	//private Random r;
	protected HUD hud;
	private static final long serialVersionUID = 1L;
	private static final String uniqueID = UUID.randomUUID().toString();
	protected static String serverID;
	
	public int cameraX = 0;
	public int cameraY = 0;

	public LinkedList<GameObject> selectedObject = new LinkedList<GameObject>();
	
	public static final int WIDTH = 1080, HEIGHT = WIDTH /12 * 9;
	private Thread thread;
	private boolean running = false;
	protected Grid grid;
	protected Window window;
	public boolean paused = false;
	private Client client;

	
	public enum STATE{
		Game,
		Connecting,
	};
	
	public STATE gameState = STATE.Connecting;
	
	public Game(){

		handler = new Handler();
		grid = new Grid(handler);
        
		this.addKeyListener(new KeyInput(handler, this));

		mouseinput = new MouseInput(this, handler,grid);
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
		window = new Window(WIDTH, HEIGHT, this);
		handler.addObject(new Tank(WIDTH/2-40, HEIGHT/2-40, ID.Tank, handler,grid));
		handler.addObject(new Slave(WIDTH/2+200, HEIGHT/2+50, ID.Slave, handler,grid));
		handler.addObject(new Slave(WIDTH/2+300, HEIGHT/2+30, ID.Slave, handler,grid));

		handler.addObject(new Sheep(WIDTH/2 - 100, HEIGHT/2+50, ID.Sheep, handler));
		handler.addObject(new Archer(WIDTH/2 - 500, HEIGHT/2+50, ID.Archer, handler, grid));

		hud = new HUD(this,grid,window);
		client.sendData(String.format("Player: %s", uniqueID).getBytes());
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
		client = new Client(this, "127.0.0.1");
		client.start();
		
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
	      while(running) {
	              long now = System.nanoTime();
	              delta += (now - lastTime) / ns;
	              lastTime = now;
	              while(delta >= 1) {
	                      tick();
	                      delta--;
	                      render();
	              }
	             
	              if(System.currentTimeMillis() - timer > 1000) {
	                      timer += 1000;
	              }
	             
	      }
	      stop();
	}
	
	private void tick(){
		if(gameState == STATE.Game){
			grid.loadGrid();
			handler.tick();	
		}
		//hud.tick(); <3
		

	}
	private Image img = null;

	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.translate(cameraX, cameraY);

		if(gameState == STATE.Game){
			if(img == null){
				try {
					img = ImageIO.read(this.getClass().getResource("/grass.png"));
		            g.drawImage(img, 0,0, 1500, 1500, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				g.drawImage(img, 0, 0, 1500, 1500, null);
			}
			handler.render(g);
			hud.render(g,this);

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
