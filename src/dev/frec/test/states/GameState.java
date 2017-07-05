package dev.frec.test.states;

import java.awt.Graphics;
import dev.frec.test.Handler;
import dev.frec.test.worlds.*;

public class GameState extends State
{
	private World world;
	
	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "res/worlds/lv1.wld");
		handler.setWorld(world);
	}
	
	public void NewLevel(int level)
	{
		World world;
		switch (level)
		{
		case 1:
			world = new World(handler, "res/worlds/lv1.wld");
			break;
		case 2:
			world = new Level1(handler, "res/worlds/lv2.wld");
			break;
		default:
			world = new World(handler, "res/worlds/lv1.wld");
			break;
		}
		this.world = world;
		handler.setWorld(world);
	}
	
	@Override
	public void tick()
	{
		world.tick();
	}

	@Override
	public void render(Graphics g)
	{
		world.render(g);
	}
	
}
