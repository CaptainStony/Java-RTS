package com.caps.main;

import java.util.Arrays;
import java.util.Random;

import com.caps.resource.Gold;

public class WorldGeneration {
	
	private Handler handler;
	private Game game;
	public 	int[][] terrain = new int[1500/20][1500/20];
	public WorldGeneration(Handler handler,Game game){
		this.handler = handler;
		this.game = game;
	}
	private static int randInt(int min, int max) {
		
	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public void generateTerain(){

	      for(int x = 0; x < terrain.length; x++){
	          for(int y = 0; y < terrain.length; y++){
	        	  if(randInt(0, 1000) == 1){
	        		  terrain[x][y]=1;
	        	  }else{
	        		  terrain[x][y]=0;
	        	  }
	          }
	       }
	}
	public void useTerrain(){
	      for(int x = 0; x < terrain.length; x++){
	          for(int y = 0; y < terrain.length; y++){
	        	  if(terrain[x][y] == 1){
	        		  handler.addObject(new Gold(x*20, y*20, ID.Resource, handler));
	        		  handler.addObject(new Gold(x+1*20, y+1*20, ID.Resource, handler));

	        	  }
	          }
	      }
	      System.out.println(Arrays.deepToString(terrain));
	}
}
