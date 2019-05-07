package com.stage.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.Animation;
import com.battle.graphics.StatBar;
import com.battle.player.PlayerItemEffects;
import com.encounter.EncounterPlayer;
import com.fortyways.dns.DnS;
import com.fortyways.storages.ItemStorage;
import com.fortyways.storages.SpriteStorage;
import com.stage.items.Item;

public class StagePlayer extends EncounterPlayer{

	public String stageName;
	
	private Animation idleAnim;
	private float worldX;
	private float worldY;
	public float speed=1;
	

	
	public StagePlayer() {
		super();
		worldX=100;
		worldY=100;
		speed=3f;

		pie=new PlayerItemEffects();
		idleAnim=new Animation(DnS.WIDTH/2, DnS.HEIGHT/2, 34, 40,
				DnS.res.getAtlas("pack").findRegion("PlayerSprite2"), 20, 17, 20);
	}
	public StagePlayer(String playerName,String stageName) {
		super(playerName);
		this.stageName=stageName;
		idleAnim=new Animation(DnS.WIDTH/2, DnS.HEIGHT/2, 34, 40,
				DnS.res.getAtlas("pack").findRegion("PlayerSprite2"), 20, 17, 20);
		worldX=350;
		worldY=850;
		speed=3f;
	}
	public void render(SpriteBatch spriteBatch){
		idleAnim.render(spriteBatch);
		hpbar.render(spriteBatch);
		spbar.render(spriteBatch);
		mpbar.render(spriteBatch);
	}
	public void update(float dt){
		idleAnim.update(dt);
	}
	public float getWorldX() {
		return worldX;
	}
	public float getWorldY() {
		return worldY;
	}
	public void setWorldX(float worldX) {
		this.worldX = worldX;
	}
	public void setWorldY(float worldY) {
		this.worldY = worldY;
	}
}
