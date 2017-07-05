package dev.frec.test.projectiles;

import dev.frec.test.Handler;
import dev.frec.test.entities.Entity;
import dev.frec.test.entities.creatures.Player;

public abstract class Melee extends Projectile
{
	protected Player caller;
	protected boolean faceRight;
	protected int damage, move = 0;
	protected Entity ehit;//Entity hit
	
	public Melee(Handler handler, float x, float y, int width, int height, int health, Player player)
	{
		super(handler, x, y, width, height, health);
		this.caller = player;
	}
	

	@Override
	public void die()
	{
		caller.attacking = false;
	}
}
