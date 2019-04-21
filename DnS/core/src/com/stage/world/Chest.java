package com.stage.world;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.fortyways.storages.ItemStorage;
import com.fortyways.util.Graphic;
import com.stage.player.StagePlayer;

public class Chest {
	
	Tile position;
	TextureRegion closedTexture=DnS.res.getAtlas("pack").findRegion("Chest");
	TextureRegion openTexture=DnS.res.getAtlas("pack").findRegion("ChestOpened");
	private boolean closed=true;
	public Graphic openButton;
	public Chest(Tile tile) {
		this.position=tile;
		//System.out.println(tile.x);
		//closedTexture=DnS.res.getAtlas("pack").findRegion("Chest");
		//openTexture=DnS.res.getAtlas("pack").findRegion("ChestOpened");
		openButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2-100, 80, 30,
				DnS.res.getAtlas("pack").findRegion("OpenButton"));
	}
	public void render(SpriteBatch sb){
		if(closed){
			sb.draw(closedTexture,position.relativeX,position.relativeY,40,40);
		}
		else {
			sb.draw(openTexture,position.relativeX,position.relativeY,40,40);
		}
	}
	public void open(World world,StagePlayer player){
		world.items.add(ItemStorage.getRandomItemNoDupesWithClass(player));
		world.items.get(world.items.size()-1).setUpPanel();
		world.items.get(world.items.size()-1).setWorldCoords(position.x, position.y);
		closed=false;
	}
	
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public boolean isClosed() {
		return closed;
	}
	
	public void renderButton(SpriteBatch sb){
		openButton.render(sb);
	}
	public boolean Touched(float x,float y){
		return position.Touched(x, y);
	}
	
}
