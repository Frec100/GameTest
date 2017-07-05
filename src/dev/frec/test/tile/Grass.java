package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Grass extends Tile
{

	public Grass(int id) {
		super(Assets.grass, id);
	}
	
	@Override
	public int solid()
	{
		return 1;
	}
}
