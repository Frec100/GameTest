package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class SandStone extends Tile
{

	public SandStone(int id) {
		super(Assets.sandstone, id);
	}
	
	@Override
	public int solid()
	{
		return 1;
	}
}
