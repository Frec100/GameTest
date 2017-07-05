package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Dirt_Back extends Tile
{
	public Dirt_Back(int id)
	{
		super(Assets.bdirt, id);
		this.id = id;
	}
	
	@Override
	public int solid()
	{
		return 0;
	}
}
