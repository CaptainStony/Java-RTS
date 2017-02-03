package com.caps.main;

import java.util.LinkedList;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	//private HUD hud = new HUD();
	
	public void tick(){
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			
		}
	}
	
	public void render(java.awt.Graphics g){
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

	public void clearEnemysAndPlayer(){
		for (int i = 0; i < object.size(); i++) {
			object.clear();
		}
	}
}
