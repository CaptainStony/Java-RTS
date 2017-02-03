package com.caps.main;

import java.awt.Color;
import java.awt.Font;

public class HUD {
	
	public void tick(){


	}
	
	public void render(java.awt.Graphics g,Game game){
		g.setFont(new Font("default", Font.PLAIN, 15));

		//World coordinates = camera position + mouse position

		g.setColor(Color.orange);
		g.fillRect(Game.WIDTH-1070-game.cameraX, Game.HEIGHT-90-game.cameraY, 1050, 50);

	}

}
