package dev.frec.test.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.entities.creatures.Player;
import dev.frec.test.entities.statics.Dummy;
import dev.frec.test.gfx.Assets;

public class Punch1 extends Melee
{
	public Punch1(Handler handler, float x, float y, int lifetime, Player player)
	{
		super(handler, x, y, 40, 40, lifetime, player);
		this.damage = 20;
		this.faceRight = player.getDirection();
	}
	
	@Override
	public void tick()
	{
		move++;
		if(faceRight)//Follow caller
			x = caller.getX() + 80 + move;
		else
			x = caller.getX() + 8 - move;
		y = caller.getY() + 35;
		hurt(1);//Time to live
		
		//CheckHit
		ehit = checkEntityCollisions(0f, 0f);
		if(ehit != null && ehit.getClass() == Dummy.class)
		{
			ehit.hurt(damage);
			ehit.Hit(40);
		}
		
		
	}
	
	@Override
	public void render(Graphics g)
	{
		if(faceRight)
		g.drawImage(Assets.punch1,(int) (x - handler.getGameCamera().getxOffset()),
				   				  (int) (y - handler.getGameCamera().getyOffset()),
				   				  width, height, null);
		else
		g.drawImage(Assets.punch1,(int) (x - handler.getGameCamera().getxOffset()) + width,
	   				 			  (int) (y - handler.getGameCamera().getyOffset()),
	   				 			  -width, height, null);
			
		
		g.setColor(Color.blue);
		g.drawRect((int) (x - handler.getGameCamera().getxOffset()), 
 				   (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}
}
