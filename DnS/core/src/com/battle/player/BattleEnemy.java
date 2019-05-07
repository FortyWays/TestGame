package com.battle.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.card.Card;
import com.battle.graphics.Animation;
import com.battle.graphics.StatBar;
import com.fortyways.dns.DnS;
import com.fortyways.state.BattleState;
import com.fortyways.storages.BattleEntityStorage;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.util.Graphic;
import com.stage.graphics.InfoPanel;


public class BattleEnemy extends BattleEntity{
	private AIDecisionMaking ai;
	
	
	public int pos;
	//public InfoPanel panel;
	
	public BattleEnemy(String name,int pos,String stageName) {
		
		super(BattleEntityStorage.getStats(name)[0]
				,BattleEntityStorage.getStats(name)[1],
				BattleEntityStorage.getStats(name)[2],
				BattleEntityStorage.getRegens(name)[0],
				BattleEntityStorage.getRegens(name)[1],
				BattleEntityStorage.getRegens(name)[2], 
				BattleEntityStorage.getDeck(name));
		setIdleAnim(new Animation(DnS.WIDTH/2+100*pos+90, DnS.HEIGHT/2,
				SpriteStorage.getFrameProportions(name)[0]*5,
				SpriteStorage.getFrameProportions(name)[1]*5,
				SpriteStorage.getIdleAnimationSpriteSheet(name)
				,20, SpriteStorage.getFrameProportions(name)[0]
				,SpriteStorage.getFrameProportions(name)[1]));
		animHandler.setFireAnim();
		this.pos=pos;
		this.entityName=name;
		hpBar=new StatBar(getIdleAnim().x+10, getIdleAnim().y+70, 
				maxhp, "hp");
		mpBar=new StatBar(getIdleAnim().x+10, getIdleAnim().y+70+hpBar.height-2, 
				maxmp, "mp");
		spBar=new StatBar(getIdleAnim().x+10, getIdleAnim().y+70+hpBar.height*2-4, 
				maxsp, "sp");
		panel=new InfoPanel(entityName,stageName);
		
	}
	
	@Override
	public void render(SpriteBatch spriteBatch) {
		if(!isDead()){
		hpBar.render(spriteBatch);
		//spBar.render(spriteBatch);
		//mpBar.render(spriteBatch);
		}
		super.render(spriteBatch);
	}
	
	@Override
	public void ChangeHP(int amount) {
		// TODO Auto-generated method stub
		super.ChangeHP(amount);
		hpBar.UpdateBar(hp);
		panel.updateBars(hp, sp, mp);
	}
	@Override
	public void ChangeSP(int amount) {
		// TODO Auto-generated method stub
		super.ChangeSP(amount);
		spBar.UpdateBar(sp);
		panel.updateBars(hp, sp, mp);
	}
	@Override
	public void ChangeMP(int amount) {
		super.ChangeMP(amount);
		mpBar.UpdateBar(mp);
		panel.updateBars(hp, sp, mp);
	}
	public void setUpAi(BattleState state){
		ai=BattleEntityStorage.getAI(this, state);
	}
	public AIDecisionMaking getAi() {
		return ai;
	}

	public boolean hasNoPlay() {
		if(cardsInHand.size()==0){
			return true;
		}
		else{
			for(Card card:cardsInHand){
				if(!(card.spcost>0&&this.sp<card.spcost)
						||
				!(card.mpcost>0&&this.mp<card.mpcost))
					return false;
			}
		}
		
		return true;
	}
	
	
	
}
