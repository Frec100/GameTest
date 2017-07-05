package dev.frec.test;

public class Launcher {

	public static void main(String[] args)
	{
		Game game = new Game("Title", 20 * 64, 13 * 64);
		game.start();
	}
}
