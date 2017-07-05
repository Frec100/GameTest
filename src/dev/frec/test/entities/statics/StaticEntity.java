package dev.frec.test.entities.statics;

import dev.frec.test.Handler;
import dev.frec.test.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

}