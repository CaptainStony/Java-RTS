package com.tfk.main;

public class Ticker extends Thread{

	private Server server;
	int i = 0;
	public Ticker(Server server){
		this.server = server;
		this.start();
	}
	public void tick(){
		server.handler.tick();
		if(i % 2 == 0){
			server.handler.updatePos();
		}
		
	}
	/*public void run(){
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
	      this.sl
	}*/
	
	public void run(){
		tick();
		i++;
		try {
			Thread.sleep(17);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		run();
	}
}
