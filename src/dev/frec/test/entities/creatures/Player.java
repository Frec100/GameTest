package dev.frec.test.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.frec.test.Handler;
import dev.frec.test.entities.Entity;
import dev.frec.test.entities.statics.Portal;
import dev.frec.test.gfx.Animation;
import dev.frec.test.gfx.Assets;
import dev.frec.test.projectiles.*;
import dev.frec.test.tile.Tile;
import dev.frec.test.worlds.World;

public class Player extends Creature
{
	//Box 2D
	private Handler handler;
	private Animation animIdle, animRun, animPunch1, animPunch2, animSneak, animRoll;
	private Rectangle bounds2;
	public boolean attacking = false, sneak = false, startsneak = false, hasgun = true;
	private int attack = 0;
	private World world;
	
	float hspeed = 0.3f, maxhspeed = 12;
	float jumpspeed = 16;
	boolean faceRight = true, immune = false;
	
	public Player(Handler handler, float x, float y, World world)
	{
		super(handler, x, y, DEFAULT_CREATURE_WIDTH *2, DEFAULT_CREATURE_HEIGHT *2);
		this.handler = handler;//weird fix?
		this.world = world;
		
		this.health = 200;
		
		SetDefBounds();
		
		//Anim
		animRun = new Animation(250, Assets.player_run);
		animIdle = new Animation(2000, Assets.player_idle);
		animPunch1 = new Animation(2000, Assets.player_punch);
		animPunch2 = new Animation(2000, Assets.player_punch_low);
		animSneak = new Animation(300, Assets.player_sneak);
		animRoll = new Animation(200, Assets.player_roll);
	}
	
	private void SetDefBounds()
	{
		bounds.x = 48;
		bounds.y = 8;
		bounds.width = 31;
		bounds.height = 60;
		
		bounds2 = new Rectangle();
		bounds2.x = 48;
		bounds2.y = 68;
		bounds2.width = 31;
		bounds2.height = 59;
	}

	@Override
	public void tick()
	{
		super.tick();
		animRun.tick();
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

		Entity e = checkEntityCollisions(0f, 0f);
		if(e != null && e.getClass() == Portal.class)
		{
			Portal en = (Portal) e;
			handler.getGame().getGameState().NewLevel(en.level);
		}
	}
	
	@Override
	public void moveX()
	{
		if(xMove > 0)//Right
		{
			//BOUNDS 1
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILESIZE;
			int tx2 = (int) (x + xMove + bounds2.x + bounds2.width) / Tile.TILESIZE;
			
			if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILESIZE) != 1 &&					//lower right corner
			   collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILESIZE) != 1 &&
			   collisionWithTile(tx2, (int) (y + bounds2.y) / Tile.TILESIZE) != 1 &&					//lower right corner
			   collisionWithTile(tx2, (int) (y + bounds2.y + bounds2.height) / Tile.TILESIZE) != 1)	//upper right corner
			{
				x += xMove;
			}
			else
			{
				x = tx * Tile.TILESIZE - bounds.x - bounds.width - 1;
				xMove = 0;
			}
		}
		else
		{
			if(xMove < 0)	//Left
			{
				int tx = (int) (x + xMove + bounds.x) / Tile.TILESIZE;
				int tx2 = (int) (x + xMove + bounds2.x) / Tile.TILESIZE;
				
				if(collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILESIZE) != 1 &&				//lower left corner
				   collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILESIZE) != 1 &&
				   collisionWithTile(tx2, (int) (y + bounds2.y) / Tile.TILESIZE) != 1 &&				//lower left corner
				   collisionWithTile(tx2, (int) (y + bounds2.y + bounds2.height) / Tile.TILESIZE) != 1)//lower right corner
				{
					x += xMove;
				}
				else
				{
					x = tx * Tile.TILESIZE + Tile.TILESIZE - bounds.x;
					xMove = 0;
				}
			}
		}
	}
	
	@Override
	public void moveY()
	{
		
		if(yMove < 0)//Up
		{
			int ty = (int) (y + yMove + bounds.y) / Tile.TILESIZE;
			
			if(collisionWithTile((int) (x + bounds.x) / Tile.TILESIZE, ty) != 1 &&
			   collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILESIZE, ty) != 1)
			{
				onGround = false;
				y += yMove;
			}
			else
			{
				y = ty * Tile.TILESIZE + Tile.TILESIZE - bounds.y;
			}
		}
		else//Down
		{
			int ty = (int) (y + yMove + bounds2.y + bounds2.height) / Tile.TILESIZE;
			//Solid tile
			if(collisionWithTile((int) (x + bounds2.x) / Tile.TILESIZE, ty) == 0 &&				
			   collisionWithTile((int) (x + bounds2.x + bounds2.width) / Tile.TILESIZE, ty) == 0)
			{
				onGround = false;
				y += yMove;
			}
			else
			{
				y = ty * Tile.TILESIZE - bounds2.y - bounds2.height -1;
				onGround = true;
				yMove = 0;
			}
		}
	}

	private void getInput()
	{
		//H Movement
		if(handler.getKeyManager().right)
		{
			faceRight = true;
			xMove += hspeed;
		}
		else
		{
			if(xMove > 0)
			{
				xMove -= hspeed;
			}
		}
		if(handler.getKeyManager().left)
		{
			faceRight = false;
			xMove -= hspeed;
		}
		else
		{
			if(xMove < 0)
			{
				xMove += hspeed;
				if(xMove > 0)
					xMove = 0;
			}
		}
		if(handler.getKeyManager().down)
		{
			sneak = true;
		}
		else
			sneak = false;
		
		//Apply horizontal speed
		if(xMove > maxhspeed)
		{
			xMove = maxhspeed;
		}
		if(xMove < -maxhspeed)
		{
			xMove = -maxhspeed;
		}
		//Apply gravity
		
		if(!this.onGround)
		{
			yMove += grav/20;
		}
		
		//Jump
		
		if(handler.getKeyManager().space && this.onGround)
		{
			yMove -= jumpspeed;
		}
		
		if(yMove < -6 && !handler.getKeyManager().space)
		{
			yMove = -6;
		}
		
		//System.out.println("VX = "+ xMove + "VY = " + yMove);
		
		if(sneak)
		{
			if(!startsneak)
			{
				startsneak = true;
				
			}
			if(xMove == 0) //Sneak
			{
				height = 64;
				width = 128;
				
				bounds.x = 0;
				bounds.y = 32;
				bounds.width = 64;
				bounds.height = 32;
				
				bounds2.x = 64;
				bounds2.y = 32;
				bounds2.width = 64;
				bounds2.height = 32;
			}
			else //Roll
			{
				height = 64;
				width = 64;
				bounds.x = 0;
				bounds.y = 0;
				bounds.width = 64;
				bounds.height = 64;
				
				bounds2 = bounds;
			}
		}
		else
		{
			startsneak = false;
			height = DEFAULT_CREATURE_WIDTH *2;
			width = DEFAULT_CREATURE_WIDTH *2;
			SetDefBounds();
			if(handler.getMouseManager().isLeftPressed() && !attacking)
			{
				if(hasgun)
				{
					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();
					int mx = (int) b.getX();
					int my = (int) b.getY();
					world.getEntityManager().addEntity(new Bullet(handler, x, y, 8, 8, 20,
													   Math.atan2(y+(height/2) - my, x+(height/2) - mx) - Math.PI / 2));
					
				}
				else
				{
					world.getEntityManager().addEntity(new Punch1(handler, x, y, 15, this));
					attack = 1;
					attacking = true;
				}
			}
		}
		if(!attacking)
		{
			attack = 0;
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		if(hasgun)
		{
			g.drawImage(Assets.arm2, (int) (x - handler.getGameCamera().getxOffset()),
									 (int) (y - handler.getGameCamera().getyOffset()),
									 width, height, null);
		}
		if(faceRight)
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
								   (int) (y - handler.getGameCamera().getyOffset()),
								   width, height, null);
		else
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()) + width,
				   (int) (y - handler.getGameCamera().getyOffset()),
				   -width, height, null);
		
		if(hasgun)
		{
			g.drawImage(Assets.arm1, (int) (x - handler.getGameCamera().getxOffset()),
									 (int) (y - handler.getGameCamera().getyOffset()),
									 width+64, height, null);
		}
		
		g.setColor(Color.red);
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				   (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
						  bounds.width, bounds.height);
		
		g.setColor(Color.green);
		g.drawRect((int) (x + bounds2.x - handler.getGameCamera().getxOffset()),
				   (int) (y + bounds2.y - handler.getGameCamera().getyOffset()),
						  bounds2.width, bounds2.height);
	}
	
	private BufferedImage getCurrentAnimationFrame()
	{
		if(hasgun)
		{
			return Assets.armless;
		}
		else
		{
			if(sneak)
			{
				if(xMove == 0)
					return animSneak.getCurrentFrame();
				else
					return animRoll.getCurrentFrame();
			}
			else
			if(attacking)
			{
				switch(attack)
				{
				case 1:
					return animPunch1.getCurrentFrame();
				case 2:
					return animPunch2.getCurrentFrame();
				default:
					return animIdle.getCurrentFrame();
				}
			}
			else
			{
				if(xMove != 0)
				{
					return animRun.getCurrentFrame();
				}
				else
				{
					return animIdle.getCurrentFrame();
				}
			}
		}
	}

	@Override
	public void die()
	{
		System.out.println("You lose?");
		handler.getGame().running = false;
	}
	
	public boolean getDirection()
	{
		return faceRight;
	}
	
}
