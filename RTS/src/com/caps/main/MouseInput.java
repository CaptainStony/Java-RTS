package com.caps.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.caps.entities.mousePoint;

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
		int worldMouseX = e.getX()-game.cameraX;
		int worldMouseY = e.getY()-game.cameraY;
		Rectangle mouseBounds = new Rectangle(worldMouseX, worldMouseY, 1, 1);
		//handler.addObject(new Tank(mouseX, mouseY, ID.Tank, handler));
		if(e.getButton() == 1){
			game.selectedObject.clear();
			for (int j = 0; j < handler.object.size(); j++) {
				handler.object.get(j).selected = false;
			}
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getBoundsTotal().intersects(mouseBounds)){
						if (tempObject.selected == true){
							tempObject.selected = false;
							game.selectedObject.remove(tempObject);
						}else{
							tempObject.selected = true;
							game.selectedObject.add(tempObject);
	
						}	
				}
			}
				
		}
		if (e.getButton() == 3){
			for (int i = 0; i < game.selectedObject.size(); i++) {
				GameObject obj = game.selectedObject.get(i);
				if (obj != null){
					GameObject endPoint = new mousePoint(worldMouseX, worldMouseY, ID.MousePointer, handler);
					if(obj.getId() == ID.Slave){
						if(obj.interactedResource == null){
							for (int j = 0; j < handler.object.size(); j++) {
								GameObject resObj = handler.object.get(j);
								if(resObj.getId() == ID.Resource){
									if(endPoint.getBoundsTotal().intersects(resObj.getBoundsTotal())){
										obj.interactedResource = resObj;

									}	
								}
							}	
						}else{
							obj.interactedResource = null;
						}
					}
					handler.goToCords(worldMouseX, worldMouseY, obj);
					/*
					 * 
					 * is in handler nu :)
					 * 
					float difX = worldMouseX - obj.getX()-8;
					float difY = worldMouseY - obj.getY()-8;
					float angle = (float) Math.atan(difY/difX);
					
					if(difX>0 && difY<0 || difX>0 && difY>0){
						obj.velX = (float) (obj.baseSpeed * Math.cos(angle));
						obj.velY = (float) (obj.baseSpeed * Math.sin(angle));
						
					}else if (difX<0 && difY<0 || difX<0 && difY>0){
						obj.velX = (float) -(obj.baseSpeed * Math.cos(angle));
						obj.velY = (float) -(obj.baseSpeed * Math.sin(angle));
					}
					if (obj.getEndPoint() == null){
						handler.addObject(endPoint);
						obj.setEndPoint(endPoint);
					}else{
						handler.removeObject(obj.getEndPoint());
						obj.setEndPoint(endPoint);
						handler.addObject(endPoint);
					}*/
					
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
