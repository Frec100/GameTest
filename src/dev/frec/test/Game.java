package dev.frec.test;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.frec.test.display.Display;
import dev.frec.test.gfx.*;
import dev.frec.test.gui.GUI;
import dev.frec.test.input.KeyManager;
import dev.frec.test.input.MouseManager;
import dev.frec.test.states.*;

@SuppressWarnings("unused")
public class Game implements Runnable
{
	private final int TIME = 1_000_000_000, FPS_LIMIT = 60;
	private Display display;
	private int width, height;
	public String title;
	
	public boolean running = false;
	private Thread thread;
	
	//Way for the computer to draw things to the screen
	private BufferStrategy bs;
	//Allows painting to the canvas
	private Graphics g;
	
	//States
	private State gameState, menuState;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private GameCamera gameCamera;
	
	private Handler handler;
	
	private GUI gui;
	
	
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init()
	{
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		gui = new GUI(handler);
		State.setState(gameState);
	}
	
	private void tick()
	{
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render()
	{
		Toolkit.getDefaultToolkit().sync();//THANK GOD YES
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Start drawing
		
		g.clearRect(0, 0, width, height);
		g.fillRect(0, 0, width, height);
		
		if(State.getState() != null)
			State.getState().render(g);
		
		gui.render(g);
		
		bs.show();//Shows result
		g.dispose();//Ends drawing
	}
	
	public void run()
	{
		init();
		//FPS limiter
		int fps = FPS_LIMIT;
		double timePerTick = TIME / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= TIME)
			{
				//System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public GameCamera getGameCamera()
	{
		return gameCamera;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public synchronized void start()
	{//Calls run method
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try
			{thread.join();}
		catch (InterruptedException e)
			{e.printStackTrace();}
	}
	
	public GameState getGameState()
	{
		return (GameState) gameState;
	}
}
