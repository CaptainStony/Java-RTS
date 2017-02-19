package com.caps.main;

import com.caps.resource.Wood;

import java.io.*;

public class WorldGenerator
{
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final double FEATURE_SIZE = 24;

	public void run(Handler handler, Grid grid, long i)
		throws IOException {
		GridCell[][] cells = grid.gridCells;
		OpenSimplexNoise noise = new OpenSimplexNoise(i);
		for (int y = 0; y < HEIGHT; y++)
		{
			for (int x = 0; x < WIDTH; x++)
			{
				double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0);
				if(value > 0.1 && value < 0.5 && y % 2 == 0 && !cells[x][y].isWall){
					
					handler.addObject(new Wood(x * 20, y * 20, ID.Resource, handler));
				}
			}
		}
	}
}