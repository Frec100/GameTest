package dev.frec.test.worlds;

import java.awt.Graphics;
import dev.frec.test.Handler;
import dev.frec.test.entities.*;
import dev.frec.test.entities.creatures.*;
import dev.frec.test.entities.creatures.enemies.Enemy1;
import dev.frec.test.entities.statics.*;
import dev.frec.test.gfx.Assets;
import dev.frec.test.tile.Tile;
import dev.frec.test.utils.Utils;

public class World
{
	protected Handler handler;
	public int width, height;
	public int [][] tiles;
	protected int spawnX, spawnY;
	protected EntityManager entityManager;
	
	public World(Handler handler, String path)
	{
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100, this));

		//entityManager.addEntity(new Dummy(handler, 200, 400));
		//entityManager.addEntity(new Crate(handler, 500, 500, 64, 64));
		entityManager.addEntity(new Portal(handler, 1900, 280, 64, 64, 2));
		entityManager.addEntity(new Enemy1(handler, 500, 1000, 64, 64));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick()
	{
		entityManager.tick();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(Assets.bg, 0, 0, null);
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILESIZE);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILESIZE + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILESIZE);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILESIZE + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILESIZE - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILESIZE - handler.getGameCamera().getyOffset()));
			}
		}
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height)
		{
			return Tile.voidTile;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		return t;
	}
	
	public void loadWorld(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++)
		{
			for(int x = 0;x < width;x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public EntityManager getEntityManager()
	{
		return entityManager;
	}
}
