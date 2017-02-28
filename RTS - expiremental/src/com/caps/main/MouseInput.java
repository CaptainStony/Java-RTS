package com.caps.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import com.caps.entities.Archer;
import com.caps.entities.BuildingObject.BUILDINGTYPE;
import com.caps.entities.Slave;
import com.caps.entities.TownCenter;
import com.caps.entities.Wall;
import com.caps.main.Game.STATE;

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
		if(game.gameState == STATE.Game){
			Rectangle mouseBounds = new Rectangle(worldMouseX, worldMouseY, 1, 1);
			//handler.addObject(new Tank(mouseX, mouseY, ID.Tank, handler));
			if(e.getButton() == 1){
				TownCenter base = (TownCenter) handler.findObject(ID.Base);
				if(base.selected && !handler.intersects(mouseBounds)){
					game.selectedObject.clear();
					handler.object.forEach(obj->obj.selected = false);
					base.selected = false;
				}else if(!base.selected ){
					boolean intersects = false;
					for(int i = 0; i < handler.object.size(); i++){
						if(handler.object.get(i).getBoundsTotal().intersects(mouseBounds)){
							handler.object.get(i).selected = true;
							intersects = true;
							break;
						}
					}
					if(!intersects){
						for(int i = 0; i < handler.object.size(); i++){
							handler.object.get(i).selected = false;
						}
					}
				}else{
					game.selectedObject.clear();
					handler.object.forEach(obj->obj.selected = false);
				}
				
				/*for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getBoundsTotal().intersects(mouseBounds) 
							&& tempObject.id != ID.Particle && tempObject.id != ID.Projectile && tempObject.id != ID.MousePointer){
							if (tempObject.selected){
								tempObject.selected = false;
								game.selectedObject.remove(tempObject);
							}else{
								tempObject.selected = true;
								game.selectedObject.add(tempObject);
		
							}	
					}
				}*/

			}else if (e.getButton() == 3){
				GameObject obj;
				for (int i = 0; i < game.selectedObject.size(); i++) {
					obj = game.selectedObject.get(i);
					if(obj.id == ID.Base){
						TownCenter town = (TownCenter) obj;
						town.setRallyPoint(new Location(worldMouseX, worldMouseY, grid));
						//System.out.println("Rallypoint set!");
					}
				}
				for (int i = 0; i < game.handler.object.size(); i++) {
					obj = game.handler.object.get(i);
					if (obj.selected && obj != null){
						Coordinate endPoint = new Coordinate(worldMouseX, worldMouseY, 4, 4);
						if(obj.getId() == ID.Slave){
							Slave slave = (Slave) obj;
							
							switch((int) Math.round(endPoint.y/300)){
							case 0:
								for(int j = 0; j < handler.resourceSec0.size(); j++){
									GameObject resObj = handler.resourceSec0.get(j);
									if(endPoint.rect.intersects(resObj.getBoundsTotal())){
										slave.interactedResource = resObj;
										break;
									}else{
										slave.interactedResource = null;
										slave.setCarry(0);
									}
								}
								break;
							case 1:
								for(int j = 0; j < handler.resourceSec1.size(); j++){
									GameObject resObj = handler.resourceSec1.get(j);
									if(endPoint.rect.intersects(resObj.getBoundsTotal())){
										slave.interactedResource = resObj;
										break;
									}else{
										slave.interactedResource = null;
										slave.setCarry(0);
									}
								}
								break;
							case 2:
								for(int j = 0; j < handler.resourceSec2.size(); j++){
									GameObject resObj = handler.resourceSec2.get(j);
									if(endPoint.rect.intersects(resObj.getBoundsTotal())){
										slave.interactedResource = resObj;
										break;
									}else{
										slave.interactedResource = null;
										slave.setCarry(0);
									}
								}
								break;
							case 3:
								for(int j = 0; j < handler.resourceSec3.size(); j++){
									GameObject resObj = handler.resourceSec3.get(j);
									if(endPoint.rect.intersects(resObj.getBoundsTotal())){
										slave.interactedResource = resObj;
										break;
									}else{
										slave.interactedResource = null;
										slave.setCarry(0);
									}
								}
								break;
							case 4:
								for(int j = 0; j < handler.resourceSec4.size(); j++){
									GameObject resObj = handler.resourceSec4.get(j);
									if(endPoint.rect.intersects(resObj.getBoundsTotal())){
										slave.interactedResource = resObj;
										break;
									}else{
										slave.interactedResource = null;
										slave.setCarry(0);
									}
								}
								break;
							}
							game.client.sendData(String.format("04Server: %s\n%s\nx: %d y: %d\n%d", Game.uniqueID, "slave", (int) worldMouseX, (int) worldMouseY, slave.objID).getBytes()); 
						}else if (obj.getId() == ID.Archer){
							Archer archer = (Archer) obj;
							boolean noEnemy = true;
							for (int j = 0; j < handler.object.size(); j++) {
								GameObject enemy = handler.object.get(j);
									if(endPoint.rect.intersects(enemy.getBoundsTotal())){
										archer.target = enemy;
										noEnemy = false;
										archer.velX = 0;
										archer.velY = 0;
										archer.path = null;
										break;
									}else{
										archer.target = null;
										noEnemy = true;

									}
							}
							if(noEnemy == true){
								LinkedList<GridCell> path = grid.calculatePath(grid.findGridCellByXAndY((int)obj.getX(), (int)obj.getY()), grid.findGridCellByXAndY(worldMouseX, worldMouseY),obj);
								archer.setPath(path);	
							}
						}

					}
				}
			}else if (e.getButton() == 2){
				handler.addObject(new Wall((int)grid.findGridCellByXAndY(worldMouseX, worldMouseY).getX(), (int)grid.findGridCellByXAndY(worldMouseX, worldMouseY).getY(), game, handler, BUILDINGTYPE.Wall));

			}
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
