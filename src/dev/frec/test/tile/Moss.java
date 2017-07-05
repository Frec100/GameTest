package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Moss extends Tile
{

	public Moss(int id) {
		super(Assets.moss, id);
	}
	
	@Override
	public int solid()
	{
		return 1;
	}
}
