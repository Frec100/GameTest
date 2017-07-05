package dev.frec.test.entities.objects;

import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.entities.*;

public abstract class Object extends Entity
{

	public Object(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);
	}

	@Override
	public void die()
	{
		
	}

	@Override
	public void tick()
	{
		super.tick();
	}

	@Override
	public void render(Graphics g)
	{
		
	}
	
}
