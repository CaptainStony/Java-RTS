package com.caps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.UUID;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{

	protected Handler handler;
	public MouseInput mouseinput;

	//private Random r;
	protected HUD hud;
	private static final long serialVersionUID = 1L;
	static final String uniqueID = UUID.randomUUID().toString();
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
	protected Client client;
	private Menu menu;
	
	public InetAddress serverIP;
	public int serverPort;
	
	public enum STATE{
		Game,
		Connecting,
		Menu,
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game(){
		window = new Window(WIDTH, HEIGHT, this);

		handler = new Handler();
		menu = new Menu(this,window);
		grid = new Grid(handler);
        
		this.addKeyListener(new KeyInput(handler, this));

		mouseinput = new MouseInput(this, handler,grid);

		hud = new HUD(this,grid,window);
		
		this.addMouseListener(mouseinput);
		this.addMouseMotionListener(mouseinput);
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
		grid.loadGrid();

		if(gameState == STATE.Game){
			handler.tick();
		}else if(gameState == STATE.Menu){
			menu.tick();
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

		if(gameState == STATE.Game){
			window.serverEnter.setVisible(false);
			window.serverIP.setVisible(false);
			window.serverPort.setVisible(false);

			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			g.translate(cameraX, cameraY);
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

		}else if(gameState == STATE.Menu){
			window.serverEnter.setVisible(true);
			window.serverIP.setVisible(true);
			window.serverPort.setVisible(true);
			menu.render(g);
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
