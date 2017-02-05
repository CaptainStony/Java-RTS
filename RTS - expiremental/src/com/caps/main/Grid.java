package com.caps.main;

import java.util.Arrays;
import java.util.LinkedList;

public class Grid {
	protected int[][] worldGrid = new int[75][75];
	protected GridCell[][] gridCells = new GridCell[75][75];
	protected LinkedList<GridCell> closedList = new LinkedList<GridCell>();
	public LinkedList<GridCell> path = new LinkedList<GridCell>();

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
	

	public LinkedList<GridCell> findPath(GridCell startCell,GridCell endCell){
		while (startCell != endCell) {
			
			LinkedList<GridCell> allAdjCells = new LinkedList<GridCell>();
			int startRow = startCell.getRow();
			int startCol = startCell.getCol();
			closedList.add(startCell);
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					GridCell adjCell = findGridCellByRowAndCol(startRow+i, startCol+j);
					if(i != 0 && j != 0 && !closedList.contains(adjCell)){
						adjCell.parent = startCell;
						if(Math.abs(i) == Math.abs(j)){
							adjCell.G = startCell.G + 28;
						}else{
							adjCell.G = startCell.G + 20;
						}
						adjCell.H = Math.abs(adjCell.getRow() - endCell.getRow()) + Math.abs(adjCell.getCol() - endCell.getCol());
						adjCell.F = adjCell.G + adjCell.H;
						allAdjCells.add(adjCell);
						
					}
				}
			}
			int lowest = 99999999;
			GridCell lowestFCell = endCell;
			for (int i = 0; i < allAdjCells.size(); i++) {
				int cur =  allAdjCells.get(i).F;
				if (cur < lowest){
					lowest = cur;
					lowestFCell = allAdjCells.get(i);
				}
			}
			path.add(lowestFCell);
			//findPath(lowestFCell, endCell);
			startCell = lowestFCell;

		}
		return path;
	}
	public GridCell findGridCellByRowAndCol(int row,int col){
		for(int i = 0; i < 75; i++){
			for(int j = 0; j < 75; j++){
		        for(int k = 0; k < gridCells.length; k++){
		        	GridCell curCell = gridCells[i][j];
		        	if(curCell.getRow() == row && curCell.getCol() == col){
		        		return curCell;
		        	}
		        }
			}
		}
		return null;
	}
}
