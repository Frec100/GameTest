package dev.frec.test.entities.objects;

import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.gfx.Assets;
import dev.frec.test.tile.Tile;

public class Crate extends Object
{

	public Crate(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);
		health = 40;
	}
	
	@Override
	public void die()
	{
		
	}

	@Override
	public void tick()
	{
		super.tick();
		int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILESIZE;
		
		if(collisionWithTile((int) (x + bounds.x) / Tile.TILESIZE, ty) == 0 &&				
		   collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILESIZE, ty) == 0)
		{
			if(!onGround)
			yMove += grav;
		}
		else
		{
			y = ty * Tile.TILESIZE - bounds.y - bounds.height -1;
			this.onGround = true;
			yMove = 0;
		}
		y+=yMove;
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.crate,(int) (x - handler.getGameCamera().getxOffset()),
			 				     (int) (y - handler.getGameCamera().getyOffset()),
			 				     width, height, null);
	}

}
