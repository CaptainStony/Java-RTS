package com.caps.main;

import java.awt.Rectangle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Grid {
	protected int[][] worldGrid = new int[75][75];
	protected Rectangle[][] worldRectGrid = new Rectangle[75][75];
	protected GridCell[][] gridCells = new GridCell[75][75];
	public Grid(Handler handler){
		for(int i = 0; i < 75; i++){
			for(int j = 0; j < 75; j++){
		        worldRectGrid[i][j] = new Rectangle(i*20,j*20,20,20);
		        gridCells[i][j] = new GridCell(i*20, j*20, i, j, false);
		        for(int k = 0; k < handler.object.size(); k++){
		    	    if(worldRectGrid[i][j].intersects(handler.object.get(k).getBoundsTotal())){
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
		try {
		      
		      PrintStream out = new PrintStream(new FileOutputStream("output.txt"));

		      //output to the file a line
		      for(int i = 0; i < 75; i++){
		    	  out.println(worldGrid[i][0] + " " + worldGrid[i][1] + " "+ worldGrid[i][2] + " "+ worldGrid[i][3] + " "+ worldGrid[i][4] + " "+ worldGrid[i][5] + " "+ worldGrid[i][6] + " "+ worldGrid[i][7] + " "+ worldGrid[i][8] + " "+ worldGrid[i][9] + " "+ worldGrid[i][10] + " "+ worldGrid[i][11] + " "+ worldGrid[i][12] + " "+ worldGrid[i][13] + " "+ worldGrid[i][14] + " "+ worldGrid[i][15] + " "+ worldGrid[i][16] + " "+ worldGrid[i][17] + " "+ worldGrid[i][18] + " "+ worldGrid[i][19] + " "+ worldGrid[i][20] + " "+ worldGrid[i][21] + " "+ worldGrid[i][22] + " "+ worldGrid[i][23] + " "+ worldGrid[i][24] + " "+ worldGrid[i][25] + " "+ worldGrid[i][26] + " "+ worldGrid[i][27] + " "+ worldGrid[i][28] + " "+ worldGrid[i][29] + " "+ worldGrid[i][30] + " "+ worldGrid[i][31] + " "+ worldGrid[i][32] + " "+ worldGrid[i][33] + " "+ worldGrid[i][34] + " "+ worldGrid[i][35] + " "+ worldGrid[i][36] + " "+ worldGrid[i][37] + " "+ worldGrid[i][38] + " "+ worldGrid[i][39] + " "+ worldGrid[i][40] + " "+ worldGrid[i][41] + " "+ worldGrid[i][42] + " "+ worldGrid[i][43]);
		      }

		      System.setOut(out);
		      //close the file (VERY IMPORTANT!)
		      out.close();
		   }
		      catch(IOException e1) {
		        System.out.println("Error during reading/writing");
		   }
	}
}
