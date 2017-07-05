package dev.frec.test.gui;

import java.awt.Color;
import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.gfx.Assets;

public class HealthBar
{

	private Handler handler;

	public HealthBar(Handler handler)
	{
		this.handler = handler;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect(33, 19, handler.getWorld().getEntityManager().getPlayer().getHealth(), 20);
		g.drawImage(Assets.health, 5, 5, 256, 64,null);
	}
}