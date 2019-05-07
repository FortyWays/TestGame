package com.stage.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.stage.player.StagePlayer;

public class PickUp {

	private Tile position;
	private String type;
	private TextureRegion texture;
	private float buffer=0f;
	private boolean rising=false;
	
	public PickUp(String type,Tile position) {
		this.position=position;
		this.type=type;
		if(type=="hp"){
			texture=DnS.res.getAtlas("pack").findRegion("Health");
		}
		else if(type=="sp"){
			texture=DnS.res.getAtlas("pack").findRegion("Stamina");
		}
		else{
			texture=DnS.res.getAtlas("pack").findRegion("Mana");
		}
	}
	
	public void render(SpriteBatch sb){
		sb.draw(texture,position.relativeX+10,position.relativeY+10+buffer,20,20);
	}
	
	public void update(float dt){
		if(buffer<=10&&!rising){
			
			buffer+=0.5f;
			if(buffer==10)
				rising=!rising;
		}
		else if(buffer>=0){
			
			buffer-=0.5f;
			if(buffer==0)
			rising=!rising;
		}
	}
	public void use(StagePlayer player){
		if(type=="hp"){
			player.setHp(player.getHp()+5);
		}
		else if(type=="sp"){
			player.setSp(player.getSp()+5);
		}
		else{
			player.setMp(player.getMp()+5);
		}
	}
	public boolean Touched(float x,float y){
		return position.Touched(x, y);
	}
	public String getType() {
		return type;
	}
}
