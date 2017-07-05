package dev.frec.test.projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.frec.test.Handler;
import dev.frec.test.gfx.Assets;

public class Bullet extends Projectile
{
	//private int speed;
	
	public Bullet(Handler handler, float x, float y, int width, int height, int damage, double rotation)
	{
		super(handler, x, y, width, height, 5000);
		
		//speed = 4;
	}

	@Override
	public void die()
	{
		
	}

	@Override
	public void tick()
	{
		x++;
	}

	@Override
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		
		g2d.rotate(Math.toRadians(90));
		g.drawImage(Assets.bullet, (int)x, (int)y,null);
		
		g2d.setTransform(old);
	}

}
