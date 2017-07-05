package dev.frec.test.entities.statics;

import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.gfx.Assets;

public class Portal extends StaticEntity
{

	public int level;

	public Portal(Handler handler, float x, float y, int width, int height, int level)
	{
		super(handler, x, y, width, height);
		this.level = level;
	}

	@Override
	public void die()
	{
		
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.portal, (int) (x - handler.getGameCamera().getxOffset()),
				 (int) (y - handler.getGameCamera().getyOffset()),
				 width, height, null);
	}
	
}
