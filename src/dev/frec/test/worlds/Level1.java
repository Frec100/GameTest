package dev.frec.test.worlds;

import java.awt.Graphics;

import dev.frec.test.Handler;
import dev.frec.test.entities.EntityManager;
import dev.frec.test.entities.creatures.Player;
import dev.frec.test.entities.statics.Portal;

public class Level1 extends World
{

	public Level1(Handler handler, String path)
	{
		super(handler, path);
		entityManager = new EntityManager(handler, new Player(handler, 100, 100, this));
		entityManager.addEntity(new Portal(handler, 500, 1000, 64, 64, 1));

		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		super.render(g);
	}
	
}
