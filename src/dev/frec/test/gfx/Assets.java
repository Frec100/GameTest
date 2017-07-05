package dev.frec.test.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
	private static final int width = 16, height = 16;

	public static BufferedImage arm1, arm2, armless, bullet;
	public static BufferedImage crate, barrel, portal, enemy, health;
	public static BufferedImage bg, bgrass, bdirt;
	public static BufferedImage grass, dirt, moss, rock, drygrass, sandstone;
	public static BufferedImage brick, platform, dummy, dummyhit;
	public static BufferedImage[] player_run, player_idle, player_punch, player_punch_low, player_roll, player_sneak;
	public static BufferedImage punch1;
	public static void init()
	{
		
		SpriteSheet spriteplayer = new SpriteSheet(ImageLoader.loadImage("/textures/Player.png"));
		player_idle = new BufferedImage[1];
		player_idle[0] = spriteplayer.crop(0, 0, 32, 32);
		
		player_punch = new BufferedImage[1];
		player_punch[0] = spriteplayer.crop(0, 32, 32, 32);
		
		player_punch_low = new BufferedImage[1];
		player_punch_low[0] = spriteplayer.crop(32, 32, 32, 32);
		
		player_run = new BufferedImage[6];
		player_run[0] = spriteplayer.crop(32, 0, 32, 32);
		player_run[1] = spriteplayer.crop(64, 0, 32, 32);
		player_run[2] = spriteplayer.crop(96, 0, 32, 32);
		player_run[3] = spriteplayer.crop(128, 0, 32, 32);
		player_run[4] = spriteplayer.crop(96, 0, 32, 32);
		player_run[5] = spriteplayer.crop(64, 0, 32, 32);
		
		player_roll = new BufferedImage[4];
		player_roll[0] = spriteplayer.crop(0, 96, 16, 16);
		player_roll[1] = spriteplayer.crop(16, 96, 16, 16);
		player_roll[2] = spriteplayer.crop(0, 112, 16, 16);
		player_roll[3] = spriteplayer.crop(16, 112, 16, 16);
		
		player_sneak = new BufferedImage[2];
		player_sneak[0] = spriteplayer.crop(32, 96, 32, 16);
		player_sneak[1] = spriteplayer.crop(32, 112, 32, 16);
		
		SpriteSheet spritedummy = new SpriteSheet(ImageLoader.loadImage("/textures/Dummy.png"));
		dummy = spritedummy.crop(0, 0, 32, 32);
		dummyhit = spritedummy.crop(32, 0, 32, 32);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sprite.png"));
		brick = 		sheet.crop(width *3, width, width, height);
		platform = 		sheet.crop(width *3, 0,		width, height);
		grass = 		sheet.crop(0,		 0,		width, height);
		moss = 			sheet.crop(width,	 0,		width, height);
		dirt = 			sheet.crop(0,		 width, width, height);
		rock = 			sheet.crop(width,	 width, width, height);
		drygrass = 		sheet.crop(width *2, 0, 	width, height);
		sandstone = 	sheet.crop(width *2, width, width, height);
		
		bgrass = 	sheet.crop(0,		width*2, width, height);
		bdirt = 	sheet.crop(0,		width*3, width, height);
		
		punch1 = ImageLoader.loadImage("/textures/Punch1.png");
		bg = ImageLoader.loadImage("/textures/BG.png");

		SpriteSheet spriteobjects = new SpriteSheet(ImageLoader.loadImage("/textures/Object.png"));
		crate = spriteobjects.crop(0, 0, 16, 16);

		portal = ImageLoader.loadImage("/textures/Portal.png");

		enemy = ImageLoader.loadImage("/textures/Enemy1.png");
		
		health = ImageLoader.loadImage("/textures/Healthbar.png");

		arm1 = spriteplayer.crop(64, 32, 48, 32);
		arm2 = spriteplayer.crop(64, 64, 32, 32);
		armless = spriteplayer.crop(96, 64, 32, 32);

		bullet = ImageLoader.loadImage("/textures/Bullet.png");
		
	}
}
