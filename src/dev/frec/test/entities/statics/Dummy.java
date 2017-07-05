package dev.frec.test.entities.statics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.gfx.Assets;
import dev.frec.test.tile.Tile;

public class Dummy extends StaticEntity
{
	public Dummy (Handler handler, float x, float y)
	{
		super(handler, x, y, Tile.TILESIZE *2, Tile.TILESIZE * 2);
		health = 1000;
	}

	@Override
	public void tick()
	{
		health = 1000;
		super.tick();
	}

	@Override
	public void render(Graphics g)
	{
		if(!hit)
		g.drawImage(Assets.dummy, (int) (x - handler.getGameCamera().getxOffset()),
								 (int) (y - handler.getGameCamera().getyOffset()),
								 width, height, null);
		else
		{
			g.drawImage(Assets.dummyhit, (int) (x - handler.getGameCamera().getxOffset()),
					 (int) (y - handler.getGameCamera().getyOffset()),
					 width, height, null);
			spawntext(g, loss);
		}
		
	}
	
	public void spawntext(Graphics g, int n)
	{
		g.setColor(Color.GREEN);
		g.setFont(new Font("System", Font.PLAIN, 40)); 
		g.drawString("Hit!:" + n, (int) (x - handler.getGameCamera().getxOffset()-5),
				 				   (int) (y - handler.getGameCamera().getyOffset())-7);
	}
	
	@Override
	public void die()
	{
		
	}
}
