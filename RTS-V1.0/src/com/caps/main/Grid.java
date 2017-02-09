package com.caps.main;

import java.awt.Rectangle;
import java.util.LinkedList;

import com.caps.entities.mousePoint;

public class Grid {
	protected int[][] worldGrid = new int[75][75];
	protected GridCell[][] gridCells = new GridCell[75][75];
	//public LinkedList<GridCell> path = new LinkedList<GridCell>();
	private Handler handler;
	
	public Grid(Handler handler){
		this.handler = handler;
		
		
	}
	public void loadGrid(){
		for(int i = 0; i < 75; i++){
			for(int j = 0; j < 75; j++){
		        gridCells[i][j] = new GridCell(i*20, j*20, i, j);
		        for(int k = 0; k < handler.object.size(); k++){
		        	if(gridCells[i][j].getBoundsTotal().intersects(handler.object.get(k).getBoundsTotal())){
		        		
		        		if(handler.object.get(k).getId() == ID.Base){
				    	    worldGrid[i][j] = 1;
				    	    gridCells[i][j].setWall(true);
				    	}else{
				    	   	worldGrid[i][j] = 0;
				    	    gridCells[i][j].setWall(false);
		        		}
			    	 
		        	}
		        }
		    }
	    }
	}
	public void followPath (LinkedList<GridCell> path,GameObject entity){

		if (!entity.getBoundsTotal().intersects(path.getLast().getBoundsTotal())) {
			GridCell cell = path.get(entity.step);
			GameObject pointer  = new mousePoint(cell.getX()+10, cell.getY()+10, ID.MousePointer, handler);
			handler.goToCords((int)pointer.getX(), (int)pointer.getY(), entity);

			if(entity.getBoundsTotal().intersects(pointer.getBoundsTotal())){
				entity.step++;
			}else{
				handler.goToCords((int)pointer.getX(), (int)pointer.getY(), entity);
				
			}
		}else{
			entity.path = null;
		}
	}
	
	public LinkedList<GridCell> calculatePath(GridCell startCell,GridCell endCell,GameObject entity){
		entity.tempPath.clear();
		while (startCell != endCell) {
			LinkedList<GridCell> allAdjCells = new LinkedList<GridCell>();
			int startRow = startCell.getRow();
			int startCol = startCell.getCol();
			entity.closedList.add(startCell);
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
						GridCell adjCell = findGridCellByRowAndCol(startRow+i, startCol+j);
					if(!entity.closedList.contains(adjCell) && adjCell.isWall == false){
						if(Math.abs(i) == 1 || Math.abs(j) == 1){
							adjCell.G = startCell.G + 14;
						}else if(i != 0 && j != 0){
							adjCell.G = startCell.G + 10;
	
						}

						adjCell.H = Math.abs(adjCell.getRow() - endCell.getRow()) + Math.abs(adjCell.getCol() - endCell.getCol());
						adjCell.F = adjCell.G + adjCell.H;
						allAdjCells.add(adjCell);
							

					}
				}
			}
			int lowest = 99999999;
			GridCell lowestFCell = null;
			for (int i = 0; i < allAdjCells.size(); i++) {
				int cur =  allAdjCells.get(i).F;
				if (cur < lowest){
					lowest = cur;
					lowestFCell = allAdjCells.get(i);
				}
			}
			entity.tempPath.add(lowestFCell);
			//findPath(lowestFCell, endCell);
			startCell = lowestFCell;
			}

		entity.closedList.clear();
		return entity.tempPath;
	}
	public GridCell findGridCellByRowAndCol(int row,int col){
		GridCell curCell = gridCells[row][col];
		return curCell;
	}
	public GridCell findGridCellByXAndY(int x,int y){
		Rectangle point = new Rectangle(x, y, 5, 5);
		for (int i = 0; i < gridCells.length; i++) {
			for (int j = 0; j < gridCells.length; j++) {
				GridCell curCell = gridCells[i][j];
				if(point.intersects(curCell.getBoundsTotal())){
					return curCell;	
				}
			}
		}
		return null;
	}
}
