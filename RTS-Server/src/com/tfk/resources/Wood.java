package com.tfk.resources;

import java.awt.Rectangle;

import com.tfk.main.GameObject;
import com.tfk.main.Handler;
import com.tfk.main.ID;

public class Wood extends GameObject{
	
	public Wood(float x, float y, ID id, Handler handler) {
		super(x, y, id, "server");
		baseSpeed = 0;
		Health = 100;
		isResource = RESOURCE.Wood;

	}

	@Override
	public Rectangle getBoundsTotal() {
		return new Rectangle((int)x, (int)y, 32,62);
	}

	@Override
	public void tick() {
		
	}
}
