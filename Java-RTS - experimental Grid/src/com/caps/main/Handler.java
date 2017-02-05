package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.caps.entities.mousePoint;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	//public LinkedList<CellObject> cell = new LinkedList<CellObject>();
	//private HUD hud = new HUD();
	public GameObject findObject(ID id){

		  for (int i = 0; i < object.size(); i++) {
		   GameObject tempObject = object.get(i);
		   
		   if(tempObject.getId() == id){
		    
		    return tempObject;
		    }
		   }
		  return null;
		 }
	
	public void tick(){
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick();
		}
		/*for (int i = 0; i < cell.size(); i++) {
			CellObject tempObject = cell.get(i);
			
			tempObject.tick();
			
		}*/
	}
	
	public void render(Graphics g){
		for (int i = 0; i < object.size(); i++) {
			object.get(i).render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
    public void removeByID(ID id){
    	for(int i = 0; i < object.size(); i++){
    		if(object.get(i).id == id){
    			object.remove(i);
    		}
    	}
    }
	public void goToCords(int worldMouseX,int worldMouseY,GameObject obj){
		GameObject endPoint = new mousePoint(worldMouseX, worldMouseY, ID.MousePointer, this);
		float difX = worldMouseX - obj.getX();
		float difY = worldMouseY - obj.getY();
		float angle = (float) Math.atan(difY/difX);
		
		if(difX>0 && difY<0 || difX>0 && difY>0){
			obj.velX = (float) (obj.baseSpeed * Math.cos(angle));
			obj.velY = (float) (obj.baseSpeed * Math.sin(angle));
			
		}else if (difX<0 && difY<0 || difX<0 && difY>0){
			obj.velX = (float) -(obj.baseSpeed * Math.cos(angle));
			obj.velY = (float) -(obj.baseSpeed * Math.sin(angle));
		}
		if (obj.getEndPoint() == null){
			addObject(endPoint);
			obj.setEndPoint(endPoint);
		}else{
			removeObject(obj.getEndPoint());
			obj.setEndPoint(endPoint);
			addObject(endPoint);
		}
	}
	/*public CellObject cordsToCell(int x, int y){
		CellObject obj = null;
		for(int i = 0; i < cell.size(); i++){
			if(cell.get(i).x == x && cell.get(i).y == y){
				return cell.get(i);
			} else {
				return obj;
			}
		}
		return obj;
		
	}*/
}
