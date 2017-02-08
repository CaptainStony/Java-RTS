package com.caps.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.caps.entities.Slave;
import com.caps.entities.Tank;
import com.caps.entities.TownCenter;
import com.caps.entities.mousePoint;
import com.caps.main.Button.TYPE;

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
			Button bb = (Button) handler.findObject(ID.Button);
			TownCenter base = (TownCenter) handler.findObject(ID.Base);
			if(base.selected && bb != null){
				if(HUD.FOOD >= 10 && bb.getBoundsTotal().intersects(mouseBounds)){
					HUD.FOOD -= 10;
					boolean isEmpty;
					if(base.getQueue().getQueueSize() == 0){
						isEmpty = true;
					}else isEmpty = false;
					
					if(bb.type == TYPE.Slave){
						base.getQueue().addItemToQueue(new Slave(base.x - 20, base.y - 20, ID.Slave, handler), 5);
						if(isEmpty){
							base.timer = base.getQueue().getTimeFromQueue(1)*60;
							System.out.println(base.timer);
						}
					} else if(bb.type == TYPE.Tank){
						base.getQueue().addItemToQueue(new Tank(base.x - 20, base.y - 20, ID.Tank, handler), 10);
						if(base.timer != null && base.timer <= 0){
							base.timer = base.getQueue().getFirstTime()*60;
						}
					}
				}else{
					game.selectedObject.clear();
					handler.object.forEach(obj->obj.selected = false);
				}
				
			}else if(base.selected && !handler.intersects(mouseBounds)){
				game.selectedObject.clear();
				game.selectedObject.add(base);
			}else{
				game.selectedObject.clear();
				handler.object.forEach(obj->obj.selected = false);
			}

			
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getBoundsTotal().intersects(mouseBounds)){
						if (tempObject.selected){
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
										break;
									}	
								}
							}	
						}else{
							obj.interactedResource = null;
						}
					}
					handler.goToCords(worldMouseX, worldMouseY, obj);
					
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}
}
