package dev.frec.test.tile;

import dev.frec.test.gfx.Assets;

public class DryGrass extends Tile
{

	public DryGrass(int id) {
		super(Assets.drygrass, id);
	}
	
	@Override
	public int solid()
	{
		return 1;
	}
}
