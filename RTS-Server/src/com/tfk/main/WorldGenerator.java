package com.tfk.main;

import java.io.IOException;

import com.tfk.resources.Wood;

public class WorldGenerator
{
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final double FEATURE_SIZE = 24;

	public void run(long i, Server server)
		throws IOException {
		i = 2;
		OpenSimplexNoise noise = new OpenSimplexNoise(i);
		int j = 0;
		for (int x = 0; x < WIDTH; x++)
		{
			for (int y = 0; y < HEIGHT; y++)
			{
				double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0);
				if(value > 0.1 && value < 0.5 && y % 2 == 0 && !server.grid.gridCells[x][y].isWall){
					server.handler.addObject(new Wood(x*20, y*20, ID.Resource, server.handler));
				}else if(value < -0.1 && value > -0.14 && x % 2 == 0  && j < 30){
					
				}
			}
		}
		server.addServerText("World generated!");
	}
}
