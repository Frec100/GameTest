package dev.frec.test.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile
{
	public static Tile[] tiles = new Tile[256];
	public static Tile voidTile = new Tile(null, 0);
	public static Tile grassTile = new Grass(1);
	public static Tile dirtTile = new Dirt(2);
	public static Tile grassBackTile = new Grass_Back(3);
	public static Tile dirtBackTile = new Dirt_Back(4);
	public static Tile mossTile = new Moss(5);
	public static Tile rockTile = new Rock(6);
	public static Tile drygrassTile = new SandStone(7);
	public static Tile sandstoneTile = new DryGrass(8);
	//public static Tile brickTile = new Brick(3);
	//public static Tile platformTile = new Platform(4);
	
    /**
     * Size of all in game <code>Tile</code>s.
     * 64.
     */
	public static final int TILESIZE = 64;
	
	public BufferedImage texture, t2;
	protected int id;
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick()
	{
		
	}
	
	public int solid()
	{
		return 0;
		/*0-not solid
		 *1-solid
		 *2-platform
		 *3-side? wip
		*/
	}
	
	public void render(Graphics g, int x, int y)
	{
		if(t2 == null)
		g.drawImage(texture, x, y, TILESIZE, TILESIZE, null);
		else
		g.drawImage(t2, x, y, TILESIZE, TILESIZE, null);
	}
	
	public int getID()
	{
		return this.id;
	}
}
