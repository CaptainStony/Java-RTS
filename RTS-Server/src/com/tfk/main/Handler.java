package com.tfk.main;

import java.awt.Rectangle;
import java.sql.Timestamp;
import java.util.LinkedList;

import com.tfk.structures.BuildingObject;
import com.tfk.structures.TownCenter;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public LinkedList<BuildingObject> buildingObject = new LinkedList<BuildingObject>();
	public LinkedList<GameObject> resourceObject = new LinkedList<GameObject>();
	public LinkedList<GameObject> pointers = new LinkedList<GameObject>();
	public LinkedList<Coordinate> cords = new LinkedList<Coordinate>();
	private Server server;

	public GameObject findObject(ID id, String owner){

		GameObject returnObj = null;
		GameObject tempObject;
		for (int i = 0; i < object.size(); i++) {
			
		    tempObject = object.get(i);
		   
		    if(tempObject.getId() == id && tempObject.owner == owner){
		    	return object.get(i);
		    }
		}
		return returnObj;
	}
	public Handler(Server server){
		this.server = server;
	}
	public void tick(){
		for (int i = 0; i < buildingObject.size(); i++) {
			buildingObject.get(i).tick();
		}
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick();
		}
	}
	
	public Player getPlayer(String uniqueID){
		for(int i = 0; i < server.players.size(); i++){
			if(server.players.get(i).getID() == uniqueID){
				return server.players.get(i);
			}
		}
		return null;
	}
	
	public LinkedList<GameObject> getAllByIDGameObject(ID id){
		LinkedList<GameObject> all = new LinkedList<GameObject>();
		for(GameObject obj : object){
			if(obj.id == id){
				all.add(obj);
			}
		}
		return all;
	}
	
	public TownCenter getTownCenter(String owner){
		for(BuildingObject obj : buildingObject){
			if(obj instanceof TownCenter && obj.getOwner() == owner){
				return (TownCenter) obj;
			}
		}
		return null;
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
	public void addObject(Coordinate cord){
		cords.add(cord);
	}
	public void removeObject(Coordinate cord){
		cords.remove(cord);
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
			obj.setEndPoint(new Coordinate(worldMouseX, worldMouseY));
		}else{
			obj.setEndPoint(new Coordinate(worldMouseX, worldMouseY));
		}
	}
	public boolean intersects(Rectangle r){
		for(int i = 0; i < object.size(); i++){
			if(r.intersects(object.get(i).getBoundsTotal())){
				return true;
			}
		}
		return false;
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
	int pack = 0;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	public void updatePos(){
		for(int i = 0; i < object.size(); i++){
			for(Player p : server.players){
				if(object.get(i).getPath() != null){
					server.sendData(String.format("04Server: %s\n%d\nx: %d y: %d", p.serverID(), object.get(i).objID, (int) object.get(i).x, (int) object.get(i).y).getBytes(), p.getIP(), p.getPort());
					pack++;
					timestamp.setTime(System.currentTimeMillis());
					System.out.println(pack + " : " + timestamp.toString());
				}
			}
		}
	}
}
