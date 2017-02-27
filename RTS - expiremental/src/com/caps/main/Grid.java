package com.caps.main;

import java.awt.Rectangle;
import java.util.LinkedList;

public class Grid {
	protected int[][] worldGrid = new int[75][75];
	protected GridCell[][] gridCells = new GridCell[75][75];
	//public LinkedList<GridCell> path = new LinkedList<GridCell>();
	private Handler handler;
	
	public Grid(Handler handler){
		this.handler = handler;
		loadGrid();
		
	}
	public void loadGrid(){
		for(int i = 0; i < 75; i++){
			for(int j = 0; j < 75; j++){
		        gridCells[i][j] = new GridCell(i*20, j*20, i, j);
		        for (int j2 = 0; j2 < handler.buildingObject.size(); j2++) {
		        	
		        	if(gridCells[i][j].getBoundsTotal().intersects(handler.buildingObject.get(j2).getBoundsTotal())){
				    	  worldGrid[i][j] = 1;
				    	  gridCells[i][j].setWall(true);
			    	 
		        	}
				}
		        for(int k = 0; k < handler.object.size(); k++){
		        	if(gridCells[i][j].getBoundsTotal().intersects(handler.object.get(k).getBoundsTotal())){
		        		
		        		if(handler.object.get(k).getId() == ID.Base){
				    	    worldGrid[i][j] = 1;
				    	    gridCells[i][j].setWall(true);
				    	}else{
				    	   	/*worldGrid[i][j] = 0;
				    	    gridCells[i][j].setWall(false);*/
		        		}
			    	 
		        	}
		        }

		    }
	    }
	}
	public void followPath (LinkedList<GridCell> path,GameObject entity){
		Rectangle interRect = entity.getBoundsTotal();
		if (path.size() > entity.step && !interRect.intersects(path.getLast().getBoundsTotal())) {
			GridCell cell = path.get(entity.step);
			Coordinate pointer  = new Coordinate((int) (cell.getX()+10), (int) cell.getY()+10, 4, 4);
			handler.goToCords((int)pointer.getX()+10, (int)pointer.getY()+10, entity);
			
			if(interRect.intersects(pointer.rect)){
				entity.step++;
			}else{
				handler.goToCords((int)pointer.getX(), (int)pointer.getY(), entity);
				
			}
		}else{
			entity.path = null;
			entity.setVelX(0);
			entity.setVelY(0);
			entity.step = 0;
			
		}
	}

	
	public LinkedList<GridCell> calculatePath(GridCell startCell,GridCell endCell,GameObject entity){
		entity.tempPath.clear();
		entity.closedList.clear();
		entity.openList.clear();
		entity.step = 0;
		LinkedList<GridCell> prevAdjCells = null;
		if(endCell.isWall == false && endCell != startCell){
			while (startCell != endCell) {
				LinkedList<GridCell> allAdjCells = new LinkedList<GridCell>();
	
				int startRow = startCell.getRow();
				int startCol = startCell.getCol();
				entity.closedList.add(startCell);	
				entity.cnt = 0;
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						GridCell adjCell = findGridCellByRowAndCol(startRow+i, startCol+j);
						
						if(!entity.closedList.contains(adjCell) && adjCell.isWall == false && !entity.openList.contains(adjCell)){
							entity.cnt++;
							if(Math.abs(i) == 1 || Math.abs(j) == 1){
								adjCell.G = startCell.G + 14;
							}else if(i != 0 && j != 0){
								adjCell.G = startCell.G + 10;
		
							}
	
								adjCell.H = Math.abs(adjCell.getRow() - endCell.getRow()) + Math.abs(adjCell.getCol() - endCell.getCol());
								//adjCell.H = adjCell.H*2;
								adjCell.F = adjCell.G + adjCell.H;
	
								allAdjCells.add(adjCell);
								adjCell.setRender(true);

						}
					}
				}
				if(entity.cnt == 0){
					entity.tempPath.removeLast();
					entity.openList.removeAll(prevAdjCells);
					//entity.openList.clear();
					//System.out.println("No open cells");
				}else{
					//System.out.println("Open cells: " + entity.cnt);

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
					entity.openList.addAll(allAdjCells);
					prevAdjCells = allAdjCells;
				}
				startCell = entity.tempPath.getLast();

				}

			return entity.tempPath;
		}else{
			return null;
			
		}
		
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
