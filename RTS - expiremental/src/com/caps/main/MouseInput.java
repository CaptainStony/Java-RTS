package com.caps.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import com.caps.entities.Slave;
import com.caps.entities.Tank;
import com.caps.entities.TownCenter;
import com.caps.entities.Wall;
import com.caps.entities.mousePoint;
import com.caps.entities.BuildingObject.BUILDINGTYPE;
import com.caps.main.Button.TYPE;

public class MouseInput implements MouseListener,MouseMotionListener {
	private Handler handler;
	private Game game;
	private Grid grid;

	public MouseInput(Game game, Handler handler, Grid grid) {
		this.handler = handler;
		this.game = game;
		this.grid = grid;

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
			LinkedList<GameObject> allButtons = handler.getAllByID(ID.Button);
			Button tmp;
			TownCenter base = (TownCenter) handler.findObject(ID.Base);
			if(base.selected && allButtons != null){
				for(GameObject obj : allButtons){
					tmp = (Button) obj;
					if(HUD.FOOD >= 10 && tmp.getBoundsTotal().intersects(mouseBounds)){
						HUD.FOOD -= 10;
						boolean isEmpty;
						if(base.getQueue().getQueueSize() == 0){
							isEmpty = true;
						}else isEmpty = false;
						
						if(tmp.type == TYPE.Slave){
							if(base.getRallyPoint() != null){
								base.getQueue().addItemToQueue(new Slave(base.getRallyPoint().getX(), base.getRallyPoint().getY(), ID.Slave, handler, grid), 5);
							}else{
								base.getQueue().addItemToQueue(new Slave(base.x - 20, base.y - 20, ID.Slave, handler, grid), 5);
							}
							
							if(isEmpty){
								base.timer = base.getQueue().getTimeFromQueue(1)*60;
							}
						} else if(tmp.type == TYPE.Tank){
							if(base.getRallyPoint() != null){
								base.getQueue().addItemToQueue(new Tank(base.getRallyPoint().getX(), base.getRallyPoint().getY(), ID.Tank, handler, grid), 10);
							}else{
								base.getQueue().addItemToQueue(new Tank(base.x - 20, base.y - 20, ID.Tank, handler, grid), 10);
							}
							
							if(base.timer != null && base.timer <= 0){
								base.timer = base.getQueue().getFirstTime()*60;
							}
						}
					}else{
						game.selectedObject.clear();
						handler.object.forEach(gameObj->gameObj.selected = false);
					}
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

		}else if (e.getButton() == 3){
			for (int i = 0; i < game.selectedObject.size(); i++) {
				GameObject obj = game.selectedObject.get(i);
				if(obj.id == ID.Base){
					TownCenter town = (TownCenter) obj;
					town.setRallyPoint(new Location(worldMouseX, worldMouseY, grid));
					//System.out.println("Rallypoint set!");
				}
			}
			for (int i = 0; i < game.selectedObject.size(); i++) {
				GameObject obj = game.selectedObject.get(i);
				if (obj != null){
					GameObject endPoint = new mousePoint(worldMouseX, worldMouseY, ID.MousePointer, handler);
					if(obj.getId() == ID.Slave){
						Slave slave = (Slave) obj;
						
						for (int j = 0; j < handler.resourceObject.size(); j++) {
							GameObject resObj = handler.resourceObject.get(j);
								if(endPoint.getBoundsTotal().intersects(resObj.getBoundsTotal())){
									slave.interactedResource = resObj;
									break;
								}else{
									slave.interactedResource = null;
									slave.setCarry(0);
								}
						}
	
						LinkedList<GridCell> path = grid.calculatePath(grid.findGridCellByXAndY((int)obj.getX(), (int)obj.getY()), grid.findGridCellByXAndY(worldMouseX, worldMouseY),obj);
						slave.setPath(path);
						
					}

				}
			}
		}else if (e.getButton() == 2){
			handler.addObject(new Wall((int)grid.findGridCellByXAndY(worldMouseX, worldMouseY).getX(), (int)grid.findGridCellByXAndY(worldMouseX, worldMouseY).getY(), game, handler, BUILDINGTYPE.Wall));

		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
