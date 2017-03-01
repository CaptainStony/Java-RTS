package com.tfk.main;

public class Ticker extends Thread{

	private Server server;
	private Grid grid;
	private Handler handler;
	int i = 0;
	public Ticker(Server server){
		this.server = server;
		this.grid = server.grid;
		this.handler = server.handler;
		this.start();
	}
	public void tick(){
		handler.tick();
		handler.updatePos();
		grid.loadGrid();
		
	}
	
	public void run(){
	      long lastTime = System.nanoTime();
	      double amountofTicks = 60.0;
	      double ns = 1000000000 / amountofTicks;
	      double delta = 0;
	      long timer = System.currentTimeMillis();
	      while(true) {
	              long now = System.nanoTime();
	              delta += (now - lastTime) / ns;
	              lastTime = now;
	              while(delta >= 1) {
	                      tick();
	                      delta--;
	              }
	              if(System.currentTimeMillis() - timer > 1000) {
	                      timer += 1000;
	              }
	      }

	}
}
