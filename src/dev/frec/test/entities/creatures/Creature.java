package dev.frec.test.entities.creatures;

import dev.frec.test.Handler;
import dev.frec.test.entities.Entity;
import dev.frec.test.tile.Tile;

public abstract class Creature extends Entity
{

	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;

	
	
	
	public Creature(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, height, height);
		speed = DEFAULT_SPEED;
	}
	
	public void move()
	{
		moveX();
		moveY();
		if(y > handler.getWorld().height)
		{
			//die();
		}
	}
	
	public void moveX()
	{
		//int tx = (int)(x + xMove + (bounds.width / 2)) / Tile.TILEWIDTH;﻿ MIDDLE CHECK USE ASAP
		if(xMove > 0)	//Right
		{
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILESIZE;
			
			if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILESIZE) != 1 &&					//lower right corner
			   collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILESIZE) != 1)	//upper right corner
			{
				x += xMove;
			}
			else
			{
				x = tx * Tile.TILESIZE - bounds.x - bounds.width - 1;
			}
		}
		else
		{
			if(xMove < 0)	//Left
			{
				int tx = (int) (x + xMove + bounds.x) / Tile.TILESIZE;
				
				if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILESIZE) != 1 &&				//lower left corner
				   collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILESIZE) != 1)//lower right corner
				{
					x += xMove;
				}
				else
				{
					x = tx * Tile.TILESIZE + Tile.TILESIZE - bounds.x;
				}
			}
		}
	}
	
	public void moveY()
	{
		if(yMove < 0)//Up
		{
			int ty = (int) (y + yMove + bounds.y) / Tile.TILESIZE;
			
			if(collisionWithTile((int) (x + bounds.x) / Tile.TILESIZE, ty) != 1 &&
			   collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILESIZE, ty) != 1)
			{
				this.onGround = false;
				y += yMove;
			}
			else
			{
				y = ty * Tile.TILESIZE + Tile.TILESIZE - bounds.y;
			}
		}
		else //Down
		{
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILESIZE;
			//Solid tile
			if(collisionWithTile((int) (x + bounds.x) / Tile.TILESIZE, ty) != 1 &&				
			   collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILESIZE, ty) != 1)
			{
				this.onGround = false;
				y += yMove;
			}
			else
			{
				//y = ty * Tile.TILESIZE - bounds.y - bounds.height -1;
				this.onGround = true;
				yMove = 0;
			}
		}
	}
	
	//Get Set
	
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
