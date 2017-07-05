package dev.frec.test.gui;

import java.awt.Graphics;

import dev.frec.test.Handler;

public class GUI
{
	protected Handler handler;
	
	private HealthBar healthbar;
	
	public GUI(Handler handler)
	{
		this.handler = handler;
		healthbar = new HealthBar(handler);
	}

	public void render(Graphics g)
	{
		healthbar.render(g);
	}
}
