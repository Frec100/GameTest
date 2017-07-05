package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Dirt extends Tile
{

	public Dirt(int id) {
		super(Assets.dirt, id);
	}
	
	@Override
	public int solid()
	{
		return 1;
	}
}
