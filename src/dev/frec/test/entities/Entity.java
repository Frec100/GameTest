package dev.frec.test.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.frec.test.Handler;

public abstract class Entity
{
	public static final int DEFAULT_HEALTH = 10;

	protected float xMove, yMove;
	protected float grav;
	protected float speed;
	protected boolean onGround = false;
	boolean onPlatform = false;
	
	protected float x, y;
	protected Handler handler;
	protected int width, height, health;
	protected Rectangle bounds;
	private boolean active = true;
	//fight
	protected boolean hit = false;
	protected int loss, timestun, stun;
	
	public Entity(Handler handler, float x, float y, int width, int height)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0,0,width,height);
		
		grav = (float) 10;
		xMove = 0;
		yMove = 0;
		
		health = DEFAULT_HEALTH;
	}
	//Fight
	public abstract void die();
	
	public void hurt(int amt)
	{
		if(stun != 0)
			return;
		health -= amt;
		loss = amt;
		if(health <= 0)
		{
			active = false;
			die();
		}
	}
	
	public void Stun(int time)
	{
		stun = time;
		timestun = time;
	}
	
	public void Hit(int time)
	{
		hit = true;
		Stun(time);
	}
	//End Fight
	public Entity checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return e;
		}
		return null;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	protected int collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).solid();
	}
	
	public void tick()
	{
		if(stun != 0)
		{
			timestun--;
			if(timestun <= stun/2)
			{
				hit = false;
			}
			if(timestun <= 0)
			{
				stun = 0;
			}
		}
	}
	
	public abstract void render(Graphics g);
	
	//Get Set
	
	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
