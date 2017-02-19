package com.tfk.main;

import java.io.IOException;

public class WorldGenerator
{
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final double FEATURE_SIZE = 24;

	public void run(long i, Server server, Player p)
		throws IOException {
		OpenSimplexNoise noise = new OpenSimplexNoise(i);
		int j = 0;
		for (int y = 0; y < HEIGHT; y++)
		{
			for (int x = 0; x < WIDTH; x++)
			{
				double value = noise.eval(x / FEATURE_SIZE, y / FEATURE_SIZE, 0.0);
				if(value > 0.1 && value < 0.5 && y % 2 == 0){
					server.sendData(String.format("Server: %s\nWorldGenerator: tree \nx: %d y: %d", p.serverID(), x * 20, y * 20).getBytes(), p.getIP(), p.getPort());
				}else if(value < -0.1 && value > -0.14 && x % 2 == 0  && j < 30){
					
				}
				
			}
		}
	}
}
