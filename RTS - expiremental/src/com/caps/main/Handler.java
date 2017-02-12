package com.caps.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.entities.BuildingObject;
import com.caps.entities.Wall;
import com.caps.entities.mousePoint;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public LinkedList<BuildingObject> buildingObject = new LinkedList<BuildingObject>();
	public LinkedList<GameObject> resourceObject = new LinkedList<GameObject>();

	//private HUD hud = new HUD();
	public GameObject findObject(ID id){

		GameObject returnObj = null;
		GameObject tempObject;
		for (int i = 0; i < object.size(); i++) {
			
		    tempObject = object.get(i);
		   
		    if(tempObject.getId() == id){
		    	return object.get(i);
		    }
		}
		return returnObj;
	}
	
	public void tick(){
		for (int i = 0; i < buildingObject.size(); i++) {
			buildingObject.get(i).tick();
		}
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick();
		}
		/*for(int i = 0; i < resourceObject.size(); i++){
			resourceObject.get(i).tick();
		}*/


	}
	public LinkedList<GameObject> getAllByID(ID id){
		LinkedList<GameObject> all = new LinkedList<GameObject>();
		for(GameObject obj : object){
			if(obj.id == id){
				all.add(obj);
			}
		}
		return all;
	}
	public void render(Graphics g){
		for (int i = 0; i < buildingObject.size(); i++) {
			buildingObject.get(i).render(g);
		}
		for (int i = 0; i < object.size(); i++) {
			object.get(i).render(g);
		}
		for(int i = 0; i < resourceObject.size(); i++){
			resourceObject.get(i).render(g);
		}

	}
	
	public void addObject(GameObject object){
		if(object.id == ID.Resource){
			this.resourceObject.add(object);
		}else{
			this.object.add(object);
		}
	}
	public void addObject(BuildingObject object){
		this.buildingObject.add(object);
	}
	public void removeObject(GameObject object){
		if(object.getId() == ID.Resource){
			resourceObject.remove(object);
		}else{
			this.object.remove(object);
		}
	}
	public void removeObject(BuildingObject object){
		this.buildingObject.remove(object);
	}
	
	public void removeByID(ID id){
	     if(id == ID.Resource){
	      resourceObject.clear();
	     }else{
	      for(int i = 0; i < object.size(); i++){
	          if(object.get(i).id == id){
	           object.remove(i);
	          }
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
	/*public boolean exists(ID id){
		boolean doesExist = false;
		for(int i = 0; i < object.size(); i++){
			if(object.get(i).getId() == id){
				doesExist = true;
				break;
			}
		}
		return doesExist;
	}*/
	
	public boolean intersects(Rectangle r){
		for(int i = 0; i < object.size(); i++){
			if(r.intersects(object.get(i).getBoundsTotal())){
				return true;
			}
		}
		return false;
	}


}
