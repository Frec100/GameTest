package dev.frec.test.entities.creatures.enemies;

import dev.frec.test.Handler;
import dev.frec.test.entities.creatures.*;

public abstract class Enemy extends Creature
{

	public Enemy(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);
	}
}
