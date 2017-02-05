package com.caps.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter{
	
	private Game game;

	private boolean[] keyPress = {false,false,false,false};
	
	public KeyInput(Handler handler, Game game){
		this.game = game;

	}
	
	public void keyPressed(java.awt.event.KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyPress[0] = true;
		if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyPress[1] = true;
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyPress[2] = true;
		if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyPress[3] = true;
		
		if(keyPress[0] == true){
			game.cameraY+=5;
		}
		if(keyPress[1] == true){
			game.cameraY-=5;
		}
		if(keyPress[2] == true){
			game.cameraX+=5;
		}
		if(keyPress[3] == true){
			game.cameraX-=5;
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
