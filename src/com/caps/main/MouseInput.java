package com.caps.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.caps.entities.Tank;

public class MouseInput implements MouseListener{
	private Handler handler;
	private Game game;

	public MouseInput(Game game, Handler handler) {
		this.handler = handler;
		this.game = game;

	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX()-game.cameraX;
		int mouseY = e.getY()-game.cameraY;

		handler.addObject(new Tank(mouseX, mouseY, ID.Tank, handler));
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
