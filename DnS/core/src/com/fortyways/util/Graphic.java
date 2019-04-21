package com.fortyways.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphic extends Rectangle{
	
	public TextureRegion image;
	public Graphic(float x,float y,float width,float height,TextureRegion image) {
		super(x,y,width,height);
		this.image=image;
		
	}
	public Graphic(float x,float y,TextureRegion image) {
		super(x,y,image.getRegionWidth(),image.getRegionHeight());
		this.image=image;
		}
	
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(image, x-width/2, y-height/2, 0, 0, width, height, 1f, 1, 0);
		//spriteBatch.draw(image,x-width/2,y-height/2,width,height);
	}
	

}
