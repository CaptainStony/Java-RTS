package com.caps.main;

import java.awt.Graphics;

public class Grid {
	protected int[][] worldGrid = new int[75][75];
	protected GridCell[][] gridCells = new GridCell[75][75];
	public Grid(Handler handler){
		for(int i = 0; i < 75; i++){
			for(int j = 0; j < 75; j++){
		        gridCells[i][j] = new GridCell(i*20, j*20, i, j, false);
		        for(int k = 0; k < handler.object.size(); k++){
		    	    if(gridCells[i][j].getBoundsTotal().intersects(handler.object.get(k).getBoundsTotal())){
		    	    	worldGrid[i][j] = 1;
		    	    	gridCells[i][j].isWall = true;
		    	    	break;
		    	    }else{
		    	    	worldGrid[i][j] = 0;
		    	    	gridCells[i][j].isWall = false;
		    	    }
		        }
		    }
	    }
		
	}
	public void render(Graphics g){
		
	}
}