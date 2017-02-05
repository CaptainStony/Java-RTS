package com.caps.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class PathFinder {

	public LinkedList<CellObject> openList;
	public LinkedList<CellObject> closedList;
	public int[][] grid = new int[100/2][100/2];
	public int[][] path = new int[100/2][100/2];

	
	
	public PathFinder() {
		
	}
	
	public void addOpenList(CellObject cell){
		openList.add(cell);
	}
	public void addClosedList(CellObject cell){
		closedList.add(cell);
	}
	public boolean checkClosedList(CellObject cell){
		if(closedList.contains(cell)){
			return true;
		}
		return false;
	}
	public boolean checkOpenList(CellObject cell){
		if(openList.contains(cell)){
			return true;
		}
		return false;
	}
	public void setEffort(CellObject setCell,CellObject endCell){
		setCell.effort = Math.abs(endCell.row-setCell.row+endCell.col-setCell.col);
	}

	public void render(Graphics g) {
		
	}

	public void calculatePath(CellObject startCel, CellObject endCell,Handler handler) {

		path = grid;
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path.length; j++) {
				for (int k = 0; k < handler.cell.size(); k++) {
					CellObject curCell = handler.cell.get(k);
					if(curCell.getRow() == i && curCell.getCol() == j && curCell.isWall == false){
						setEffort(curCell, endCell);
						//System.out.println(curCell.row +":"+curCell.col + "="+curCell.effort);
					}
				}
			}
		}
		System.out.println(startCel.effort);
		for (int k = 0; k < handler.cell.size(); k++) {
			CellObject curCell = handler.cell.get(k);
			if(curCell.effort+1 == startCel.effort && (curCell.parents.contains(startCel))){
	
				System.out.println(curCell.effort);
			}
		}
	}
}
