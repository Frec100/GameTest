package dev.frec.test.entities.creatures.enemies;

import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.entities.Entity;
import dev.frec.test.entities.statics.Dummy;
import dev.frec.test.gfx.Assets;
import dev.frec.test.tile.Tile;

public class Enemy1 extends Enemy
{
	private int moveSpeed = 2;
	private boolean faceRight = true;
	int damage = 20;

	public Enemy1(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);
	}
	
	public void tick()
	{
		if(faceRight)
			xMove = moveSpeed;
		else
			xMove = -moveSpeed;
		

		if(!this.onGround)
		{
			yMove += grav/20;
		}
		moveY();
		moveX();
		
		isPlayerNear();
	}

	private void isPlayerNear()
	{
		Entity ehit = checkEntityCollisions(0f, 0f);
		if(ehit != null && ehit.getClass() == Dummy.class)
		{
			ehit.hurt(damage);
			ehit.Hit(40);
		}
	}
	
	@Override
	public void moveX()
	{
		if(xMove > 0)//Right
		{
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILESIZE;
			
			if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILESIZE) != 1 &&
			   collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILESIZE) != 1)
			{
				x += xMove;
			}
			else
			{
				x = tx * Tile.TILESIZE - bounds.x - bounds.width - 1;
				faceRight = false;
			}
		}
		else
		{
			if(xMove < 0)//Left
			{
				int tx = (int) (x + xMove + bounds.x) / Tile.TILESIZE;
				
				if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILESIZE) != 1 &&
				   collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILESIZE) != 1)
				{
					x += xMove;
				}
				else
				{
					x = tx * Tile.TILESIZE + Tile.TILESIZE - bounds.x;
					faceRight = true;
				}
			}
		}
	}
	
	@Override
	public void moveY()
	{
		
		int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILESIZE;
		//Solid tile
		if(collisionWithTile((int) (x + bounds.x) / Tile.TILESIZE, ty) == 0 &&				
		   collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILESIZE, ty) == 0)
		{
			onGround = false;
			y += yMove;
		}
		else
		{
			y = ty * Tile.TILESIZE - bounds.y - bounds.height -1;
			onGround = true;
			yMove = 0;
		}
	}

	@Override
	public void die()
	{
		
	}

	@Override
	public void render(Graphics g)
	{
		if(!faceRight)
		g.drawImage(Assets.enemy, (int) (x - handler.getGameCamera().getxOffset()),
								   (int) (y - handler.getGameCamera().getyOffset()),
								   width, height, null);
		else
		g.drawImage(Assets.enemy, (int) (x - handler.getGameCamera().getxOffset()) + width,
				   (int) (y - handler.getGameCamera().getyOffset()),
				   -width, height, null);
	}

}
