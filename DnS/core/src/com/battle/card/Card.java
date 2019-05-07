package com.battle.card;



import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.player.BattleEntity;
import com.fortyways.state.BattleState;


public class Card {
	public int spcost;
	public int mpcost;
	public String cardId;
	public String description="";
	public TextureRegion cardArt;
	public CardEffectAttributes[] cardInstructions;
	public int requiredDP=-10;

	public Card(int spcost,int mpcost,String cardId,
			TextureRegion cardArt,CardEffectAttributes[] instructions){
		this.spcost=spcost;
		this.mpcost=mpcost;
		this.cardId=cardId;
		this.cardArt=cardArt;
		this.cardInstructions=instructions;
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
		CardEffects.GetEffect(caster,target, cardInstructions);
	}
	public void Play(BattleEntity caster, BattleState state){
		ApplyCost(caster);
		CardEffects.GetEffect(caster,state, cardInstructions);
	}

	
	public boolean CheckCost(BattleEntity ent){
		if(ent.getMp()>=mpcost&&ent.getSp()>=spcost){
		return true;
		}
		else{
		return false;
		}
	};
	public void ApplyCost(BattleEntity ent){
		ent.ChangeSP(-spcost);
		ent.ChangeMP(-mpcost);
	}
	
	public String needsTarget(){
		return cardInstructions[0].needsTarget;
	}
	public boolean HasDraw(){
		for(CardEffectAttributes cea: cardInstructions){
			if(cea.effectid=="drawself"){
				return true;
			}
		}
		return false;
	}

	public boolean CheckDarkPresence(int darkPresence) {
		if(darkPresence>=requiredDP){
			return true;
		}
		return false;
	}
	
}
