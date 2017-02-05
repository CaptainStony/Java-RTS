package com.caps.main;

import java.awt.event.KeyAdapter;

import com.sun.glass.events.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private Game game;
	
	private int cameraSpeed = 7;
	private int camerXBound = 1500;
	private int camerYBound = 1500;

	private boolean[] keyPress = {false,false,false,false};
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		this.game = game;

	}
	
	public void keyPressed(java.awt.event.KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = true;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = true;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = true;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = true;
		if(camerXBound >= -game.cameraX){
			if(keyPress[3] == true){
				game.cameraX-=cameraSpeed;
			}
			
		}
		if (-game.cameraX>=0){
			
			if(keyPress[2] == true){
				game.cameraX+=cameraSpeed;
			}
		}
		if (-game.cameraY>=0){
			
			if(keyPress[0] == true){
				game.cameraY+=cameraSpeed;
			}
		}
		if(camerYBound >= -game.cameraY){
			if(keyPress[1] == true){
				game.cameraY-=cameraSpeed;
			}	
		}
		

		if(key == KeyEvent.VK_ESCAPE) {
			if(game.paused == false){
				game.paused = true;
			}else{
			game.paused = false;
			}
		}	
	}
	
	public void keyReleased(java.awt.event.KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = false;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = false;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = false;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = false;
	}
	
}
