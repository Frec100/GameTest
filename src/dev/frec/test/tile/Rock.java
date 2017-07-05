package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Rock extends Tile
{

	public Rock(int id) {
		super(Assets.rock, id);
	}
	
	@Override
	public int solid()
	{
		return 1;
	}
}
