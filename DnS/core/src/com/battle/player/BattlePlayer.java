package com.battle.player;


import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;
import com.battle.graphics.Animation;
import com.battle.graphics.StatBar;
import com.fortyways.dns.DnS;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.util.Graphic;


public class BattlePlayer extends BattleEntity{

	private StatBar hpBar;
	private StatBar spBar;
	private StatBar mpBar;
	private int startingCardAmount=5;
	
	public BattlePlayer(int maxhp,int maxsp,int maxmp,
			int hpregen,int spregen,int mpregen,ArrayList<Card> deck,String name)
	{
		
		super(maxhp, maxsp, maxmp,hpregen,spregen,mpregen, deck);
		setPlayerIdleAnim();
		//String name="player-ranger";
		setIdleAnim(new Animation(DnS.WIDTH/2-80, DnS.HEIGHT/2, 
				SpriteStorage.getFrameProportions(name)[0]*5,
				SpriteStorage.getFrameProportions(name)[1]*5,
				SpriteStorage.getIdleAnimationSpriteSheet(name)
				,20, SpriteStorage.getFrameProportions(name)[0]
				,SpriteStorage.getFrameProportions(name)[1]));
		this.entityName="player-warrior";
		hpBar=new StatBar(80, 30, 
				maxhp, "hp2");
		mpBar=new StatBar(80, 30+hpBar.height-2, 
				maxmp, "mp2");
		spBar=new StatBar(80, 30+hpBar.height*2-4, 
				maxsp, "sp2");
	}
	
	public void renderBars(SpriteBatch spriteBatch){
		hpBar.render(spriteBatch);
		spBar.render(spriteBatch);
		mpBar.render(spriteBatch);
	}
	@Override
	public void ChangeHP(int amount) {
		super.ChangeHP(amount);
		hpBar.UpdateBar(hp);
	}
	@Override
	public void ChangeSP(int amount) {
		super.ChangeSP(amount);
		spBar.UpdateBar(sp);
	}
	@Override
	public void ChangeMP(int amount) {
		super.ChangeMP(amount);
		mpBar.UpdateBar(mp);
	}
	private void setPlayerIdleAnim() {
		///
		setIdleAnim(new Animation(DnS.WIDTH/2-80, DnS.HEIGHT/2, 100, 100,
				DnS.res.getAtlas("pack").findRegion("RangerIdle"), 20, 20, 22));
		///
		
	}
	public void GetCardsInHand(int[] deckid)
	{
		for(int i=0;i<deckid.length;i++){//Adding to Hand
			cardsInHand.add(deck.get(deckid[i]));
		}
		for(int i=deckid.length-1;i>deckid.length-i-1;i--)//Removing from deck
		{
			deck.remove(cardsInHand.get(i));
		}
		
		
	}
	
	public void setStartingCardAmount(int startingCardAmount) {
		this.startingCardAmount = startingCardAmount;
	}
	public int getStartingCardAmount() {
		return startingCardAmount;
	}

	
	

}
