package com.caps.main;

public class Location {

	private int x;
	private int y;
	private Grid grid;
	private int col;
	private int row;
	private GridCell cell;
	public Location(int x, int y, Grid grid){
		this.x = x;
		this.y = y;
		this.col = x/20;
		this.row = y/20;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public String toString(){
		return String.format("x: {0} y: {1} row: {2} collum: {3}", this.x, this.y, this.row, this.col);
	}
	public GridCell getCell(){
		this.cell = grid.findGridCellByRowAndCol(this.col, this.row);
		return cell;
	}
}
