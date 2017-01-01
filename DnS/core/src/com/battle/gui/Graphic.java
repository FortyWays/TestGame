package com.battle.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphic {
	public float x;
	public float y;
	public float width;
	public float height;
	public TextureRegion image;
	public Graphic(float x,float y,float width,float height,TextureRegion image) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.image=image;
	}
	public Graphic(float x,float y,TextureRegion image) {
		this.x=x;
		this.y=y;
		this.image=image;}
	public void render(SpriteBatch sb){
		sb.draw(image, x, y);
	}
}
