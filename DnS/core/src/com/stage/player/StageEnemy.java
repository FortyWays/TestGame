package com.stage.player;

import javax.swing.text.Position;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.Animation;
import com.fortyways.dns.DnS;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.util.Graphic;
import com.fortyways.util.Rectangle;
import com.stage.graphics.InfoPanel;
import com.stage.world.Tile;

public class StageEnemy {

	Tile position;
	private Animation idleAnim;
	
	public Graphic fightButton;
	private String entityName;
	public InfoPanel panel;
	public StageEnemy(Tile position,
			String entityName,String stageName) {
		this.position=position;
		this.entityName=entityName;
		panel=new InfoPanel(entityName,stageName);
		
		//idleAnim=new Animation(relativeX, relativeY, 46, 40,
		//		DnS.res.getAtlas("pack").findRegion("EnemySprite2"), 20, 23, 20);
		idleAnim = new Animation(position.relativeX, position.relativeY,
				SpriteStorage.getFrameProportions(entityName)[0]*2,
				SpriteStorage.getFrameProportions(entityName)[1]*2,
				SpriteStorage.getIdleAnimationSpriteSheet(entityName)
				,20, SpriteStorage.getFrameProportions(entityName)[0]
				,SpriteStorage.getFrameProportions(entityName)[1]);
		fightButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2-170, 100, 30, 
				DnS.res.getAtlas("pack").findRegion("PlayButton"));
	}

	public void render(SpriteBatch spriteBatch){
		idleAnim.render(spriteBatch);
		
	}
	public void update(float dt){
		idleAnim.setX(position.relativeX+idleAnim.width/2);
		idleAnim.setY(position.relativeY+idleAnim.height/2);
		idleAnim.update(dt);
		panel.update(dt);
	}
	public void renderButton(SpriteBatch spriteBatch) {
		panel.render(spriteBatch);
		fightButton.render(spriteBatch);
	}
	
	public String getEntityName() {
		return entityName;
	}
	public boolean Touched(float x,float y){
		return position.Touched(x, y);
	}
	public Tile getPosition() {
		return position;
	}

}
