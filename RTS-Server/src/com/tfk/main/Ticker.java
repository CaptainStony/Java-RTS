package com.tfk.main;

public class Ticker extends Thread{

	private Server server;
	private int i;
	public Ticker(Server server){
		this.server = server;
		this.start();
		i = 0;
	}
	public void tick(){
		i++;
		server.handler.tick();
		if(i % 3 == 0){
			i = 0;
			
		}
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
