package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Grass_Back extends Tile
{
	public Grass_Back(int id)
	{
		super(Assets.bgrass, id);
		this.id = id;
	}
	
	@Override
	public int solid()
	{
		return 2;
	}
}
