package com.battle.card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.player.BattleEntity;
import com.fortyways.state.BattleState;

public abstract class Card {
	public int spcost;
	public int mpcost;
	public String cardId;
	public TextureRegion cardArt;
	public CardEffectAttributes[] CardInstructions;
	
	public Card(int spcost,int mpcost,String cardId,TextureRegion cardArt){
		this.spcost=spcost;
		this.mpcost=mpcost;
		this.cardId=cardId;
		this.cardArt=cardArt;
		GetCardInstructions();
	}
	
	public boolean IsPlayable(int cursp,int curmp){
		if(cursp>=spcost && curmp>=mpcost){
			return true;
		}
		else{
			return false;
		}
		
	}
	public void Play(BattleEntity caster,BattleEntity target){
		ApplyCost(caster);
		CardEffects.GetEffect(caster,target, CardInstructions);
	}
	abstract void GetCardInstructions();
	public void ApplyCost(BattleEntity ent){
		ent.ChangeSP(-spcost);
		ent.ChangeMP(-mpcost);
	};
	
	
}
