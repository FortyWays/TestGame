package com.stage.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.fortyways.util.Rectangle;
import com.stage.player.StagePlayer;

public class Tile extends Rectangle{


	private TextureRegion texture;
	private boolean passable=true;
	public float relativeX;
	public float relativeY;
	public Tile(float x, float y,TextureRegion texture,boolean passable) {
		super(x, y, 40, 40);
		this.texture=texture;
		this.passable=passable;
		relativeX=x;
		relativeY=y;
	}
	public void render(SpriteBatch spriteBatch){
		
		spriteBatch.draw(texture, relativeX, relativeY, width, height);
	}
	public void update(float dt){
		
	}
	public void getNewRelative(StagePlayer player){
		relativeX=this.x-player.getWorldX()+DnS.WIDTH/2;
		relativeY=this.y-player.getWorldY()+DnS.HEIGHT/2;
	}
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}
	public boolean isPassable() {
		return passable;
	}
	public void setPassable(boolean passable) {
		this.passable = passable;
	}
}
