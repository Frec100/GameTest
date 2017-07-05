package dev.frec.test.projectiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.frec.test.Handler;
import dev.frec.test.entities.Entity;

public abstract class Projectile extends Entity
{
	
	public Projectile(Handler handler, float x, float y, int width, int height, int health)
	{
		super(handler, x, y, width, height);
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		bounds = new Rectangle(0,0,width,height);
	}
	
	public abstract void die();
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
