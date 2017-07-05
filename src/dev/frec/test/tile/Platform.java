package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class Platform extends Tile
{

	public Platform(int id) {
		super(Assets.platform, id);
	}

	@Override
	public int solid()
	{
		return 2;
	}
}
