package com.caps.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.entities.BuildingObject;
import com.caps.main.GameObject;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public LinkedList<BuildingObject> buildingObject = new LinkedList<BuildingObject>();
	public LinkedList<GameObject> resourceObject = new LinkedList<GameObject>();
	public LinkedList<GameObject> pointers = new LinkedList<GameObject>();

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
		for (int i = 0; i < pointers.size(); i++) {
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
		}else if(object.id == ID.MousePointer){
			this.pointers.add(object);
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
		}else if(object.id == ID.MousePointer){
			this.pointers.remove(object);
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
		Coordinate endPoint = new Coordinate(worldMouseX, worldMouseY);
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
			obj.setEndPoint(endPoint);
		}else{
			obj.setEndPoint(endPoint);
		}
	}
	public GameObject getByPos(int x, int y){
		GameObject obj = null;
		for(int i = 0; i < object.size(); i++){
			obj = object.get(i);
			if( (int) obj.getX() == x && (int) obj.getY() == y){
				return obj;
			}
		}
		return obj;
	}
	public GameObject getByObjID(int objID){
		for(int i = 0; i < object.size(); i++){
			if(object.get(i).objID == objID){
				return object.get(i);
			}
		}
		for(int i = 0; i < resourceObject.size(); i++){
			if(resourceObject.get(i).objID == objID){
				return resourceObject.get(i);
			}
		}
		return null;
	}
	public boolean intersects(Rectangle r){
		for(int i = 0; i < object.size(); i++){
			if(r.intersects(object.get(i).getBoundsTotal())){
				return true;
			}
		}
		return false;
	}


}
