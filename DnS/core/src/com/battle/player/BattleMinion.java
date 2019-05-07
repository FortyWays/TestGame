package com.battle.player;

import java.util.ArrayList;

import com.battle.card.Card;
import com.battle.graphics.Animation;
import com.battle.graphics.StatBar;
import com.fortyways.dns.DnS;
import com.fortyways.storages.SpriteStorage;

public class BattleMinion extends BattleEnemy{

	public BattleMinion(String name, int pos, String stageName) {
		super(name, pos, stageName);
		setIdleAnim(new Animation(DnS.WIDTH/2-100*pos-190, DnS.HEIGHT/2,
				SpriteStorage.getFrameProportions(name)[0]*5,
				SpriteStorage.getFrameProportions(name)[1]*5,
				SpriteStorage.getIdleAnimationSpriteSheet(name)
				,20, SpriteStorage.getFrameProportions(name)[0]
				,SpriteStorage.getFrameProportions(name)[1]));
		animHandler.setFireAnim();
		this.pos=pos;
		hpBar=new StatBar(getIdleAnim().x+10, getIdleAnim().y+70, 
				maxhp, "hp");
		mpBar=new StatBar(getIdleAnim().x+10, getIdleAnim().y+70+hpBar.height-2, 
				maxmp, "mp");
		spBar=new StatBar(getIdleAnim().x+10, getIdleAnim().y+70+hpBar.height*2-4, 
				maxsp, "sp");
	}

	

}
