package com.battle.card;

import com.battle.player.BattleEntity;
import com.fortyways.state.BattleState;

public class CardEffects {
	public static void GetEffect(BattleEntity caster,BattleEntity target,CardEffectAttributes[] cea){
		for(int i=0;i<cea.length;i++){
		switch(cea[i].effectid)
		{
		case("attack1"):{AttackOne(cea[i],target);}
		//case("attackall"):{AttackAll(cea[i]);}
		case("attackplayer"):{}
		case("draw"):{Draw(cea[i],caster);}
		case("gainhp"):{GainHP(cea[i],target);}
		case("gainmp"):{GainMP(cea[i], target);}
		case("gainsp"):{GainSP(cea[i], target);}
		}
		
		}
	}
	private static void Draw(CardEffectAttributes cea,BattleEntity target)
	{
		
	}
	private static void AttackOne(CardEffectAttributes cea,BattleEntity target)
	{
		target.ChangeHP(-cea.amount);
	}

	private static void AttackAll(CardEffectAttributes cea,BattleState bs)
	{
		
	}
	private static void GainHP(CardEffectAttributes cea,BattleEntity target)
	{
		target.ChangeHP(cea.amount);
	}
	private static void GainSP(CardEffectAttributes cea,BattleEntity target)
	{
		target.ChangeSP(cea.amount);
	}
	private static void GainMP(CardEffectAttributes cea,BattleEntity target)
	{
		target.ChangeMP(cea.amount);
	}
	private static void PutInDeck(CardEffectAttributes cea,BattleEntity target)
	{
		
	}
	private static void ReduceAttack(BattleEntity target)
	{
		
	}
	private static void BlockCard(BattleState bs)
	{
		
	}
	
}
