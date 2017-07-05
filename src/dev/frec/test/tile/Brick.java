package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Brick extends Tile
{

	public Brick(int id)
	{
		super(Assets.brick, id);
	}
	
	@Override
	public int solid()
	{
		return 1;
	}
}
