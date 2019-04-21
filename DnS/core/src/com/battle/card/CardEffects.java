package com.battle.card;

import java.util.ArrayList;
import java.util.Random;

import com.battle.graphics.FadingAnimation;
import com.battle.player.BattleEnemy;
import com.battle.player.BattleEntity;
import com.fortyways.state.BattleState;

public class CardEffects {
	public static void GetEffect(BattleEntity caster,BattleEntity target,CardEffectAttributes[] cea){
		for(int i=0;i<cea.length;i++){
		if(cea[i].effectid== "attack1"){AttackOne(cea[i],target);}
		//case("attackall"):{AttackAll(cea[i]);}
		else if(cea[i].effectid=="drawself"){Draw(cea[i],caster);}
		else if(cea[i].effectid=="gainhp" ){GainHP(cea[i],target);}
		else if(cea[i].effectid=="gainmp"){GainMP(cea[i], target);}
		else if(cea[i].effectid=="gainsp"){GainSP(cea[i], target);}
		else if(cea[i].effectid=="selfhp" ){GainHP(cea[i],caster);}
		else if(cea[i].effectid=="selfsp" ){GainSP(cea[i],caster);}
		else if(cea[i].effectid=="selfmp" ){GainMP(cea[i],caster);}
		else if(cea[i].effectid=="stun" ){Stun(cea[i],target);}
		else if(cea[i].effectid=="putbackindeck" ){PutInDeck(cea[i],caster);}
		else if(cea[i].effectid=="blockranged" ){target.setRangedBlock(true);}
		else if(cea[i].effectid=="blockmelee" ){target.setMeleeBlock(true);}
		else if(cea[i].effectid=="blockmagic" ){target.setMagicBlock(true);}
		else if(cea[i].effectid=="bleeding" ){Bleeding(cea[i],target);}
		else if(cea[i].effectid=="poisoned" ){Poisoned(cea[i],target);}
		else if(cea[i].effectid=="cure"){Cure(cea[i],target);}
		else if(cea[i].effectid=="marked"){Marked(cea[i], target);}
		else if(cea[i].effectid=="selfdmg" ){LoseHP(cea[i],caster);}
		}
		
		
	}
	


	public static void GetEffect(BattleEntity caster,BattleState state,CardEffectAttributes[] cea){
		for(int i=0;i<cea.length;i++){
		if(cea[i].effectid== "attackall"){AttackAll(cea[i],state);}
		else if(cea[i].effectid=="summonenemy"){SpawnEnemy(cea[i], state);}
		else if(cea[i].effectid=="reviveenemy"){ReviveEnemy(cea[i], state);}
		else if(cea[i].effectid=="attack1random"){AttackRandomEnemy(cea[i], state);}
		else if(cea[i].effectid=="attackplayer"){attack(state.player,cea[i].amount, cea[i].dmgtype);}
		else if(cea[i].effectid=="increasedp"){IncreaseDP(caster, cea[i], state);}
		else if(cea[i].effectid=="selfdmg" ){LoseHP(cea[i],caster);}
		else if(cea[i].effectid=="summonforeachdp" ){SummonForEachDP(cea[i],state);}
		
		}	
	}
	private static void ReviveEnemy(CardEffectAttributes cea, BattleState state) {
		for(int pos=0;pos<state.enemies.size();pos++){
			if(state.enemies.get(pos).entityName==cea.entityName&&
				state.enemies.get(pos).isDead()){
				state.enemies.set(pos, new BattleEnemy(cea.entityName, pos,state.stageName));
				state.enemies.get(pos).FillEmptyHand(4);
				state.enemies.get(pos).setUpAi(state);
				state.enties.set(pos+1, state.enemies.get(pos));
			}
		}
		
	}



	private static void SummonForEachDP(CardEffectAttributes cea,BattleState state){
		for(int i=0;i<state.DarkPresence;i++){
			if(state.enemies.size()<4){
			SpawnEnemy(cea, state);
			}
			else{
				int pos=-1;
			for(BattleEnemy enemy:state.enemies){
				if(enemy.isDead()){
					pos=enemy.pos;
					break;
				}
			}
			if(pos>=0){
			SpawnEnemy(cea, state);
			}
			}
		}
	}
	private static void IncreaseDP(BattleEntity caster,CardEffectAttributes cea,BattleState state){
		caster.addMessageAnimation("darkpresence", "red", 70);
		state.increaseDP(cea.amount);
	}
	private static void Cure(CardEffectAttributes cea, BattleEntity target) {
		target.setBleeding(false);
		target.setPoisoned(false);
		target.addMessageAnimation("cured", "red", 70);
	}
	
	private static void LoseHP(CardEffectAttributes cea, BattleEntity target) {
		
		target.addMessageAnimation("-"+cea.amount, "red", 70);
		target.ChangeHP(-cea.amount);
	}
	
	private static void AttackRandomEnemy(CardEffectAttributes cea,BattleState state){
		int amount=0;
		for(BattleEntity ent:state.enemies){
			if(!ent.isDead()){
				amount++;
			}
		}
		if(amount==0){
			amount++;
		}
		Random r=new Random();
		int num=r.nextInt(amount);
		int curnum=0;
		for(BattleEntity ent:state.enemies){
			
			if(curnum==num){
				attack(ent,cea.amount,cea.dmgtype);
				
				break;
			}
			if(!ent.isDead()){
				curnum++;
			}
		}
		
	}
	private static void SpawnEnemy(CardEffectAttributes cea,BattleState state){
		if(state.enemies.size()<4){
		state.enemies.add(new BattleEnemy(cea.entityName, state.enemies.size(),state.stageName));
		state.enemies.get(state.enemies.size()-1).FillEmptyHand(4);
		//state.enemies.get(state.enemies.size()-1).effects.setJustSpawned(true);
		state.enemies.get(state.enemies.size()-1).setUpAi(state);
		state.enties.add(state.enemies.get(state.enemies.size()-1));}
		else{
			int pos = 0;
			for(BattleEnemy enemy:state.enemies){
				if(enemy.isDead()){
					pos=enemy.pos;
					break;
				}
			}
			state.enemies.set(pos, new BattleEnemy(cea.entityName, pos,state.stageName));
			state.enemies.get(pos).FillEmptyHand(4);
			state.enemies.get(pos).setUpAi(state);
			state.enties.set(pos+1, state.enemies.get(pos));
		}
	}
	private static void Stun(CardEffectAttributes cardEffectAttributes, BattleEntity target) {
		target.addMessageAnimation("stunned", "yellow", 90);
		target.setStunned(true);
	}
	private static void Draw(CardEffectAttributes cea,BattleEntity target)
	{
		target.addMessageAnimation("draw "+cea.amount, "yellow", 70);
		target.DrawRandomCards(cea.amount,true);
		
	}
	private static void AttackOne(CardEffectAttributes cea,BattleEntity target)
	{
		attack(target,cea.amount,cea.dmgtype);
	}

	private static void AttackAll(CardEffectAttributes cea,BattleState state)
	{
		for(BattleEntity ent:state.enemies){
			if(!ent.isDead()){
				attack(ent,cea.amount,cea.dmgtype);}
		}
	}
	private static void GainHP(CardEffectAttributes cea,BattleEntity target)
	{
		//System.out.println("ss");
		target.addMessageAnimation("+"+cea.amount, "red", 70);
		target.ChangeHP(cea.amount);
	}
	private static void GainSP(CardEffectAttributes cea,BattleEntity target)
	{	
		target.addMessageAnimation("+"+cea.amount, "green", 70);
		target.ChangeSP(cea.amount);
	}
	private static void GainMP(CardEffectAttributes cea,BattleEntity target)
	{
		target.addMessageAnimation("+"+cea.amount, "blue", 70);
		target.ChangeMP(cea.amount);
	}
	private static void PutInDeck(CardEffectAttributes cea,BattleEntity target)
	{
		
		for(int i=0;i<cea.amount;i++){
			target.addCardInDeck(cea.card);
		}
	}
	private static void ReduceAttack(BattleEntity target)
	{
		
	}
	private static void BlockCard(BattleState bs)
	{
		
	}
	private static void Bleeding(CardEffectAttributes cea,BattleEntity target){
		if(!target.getImmuneToBleed()){
		target.addMessageAnimation("bleeding", "red", 70);
		target.setBleeding(true);
		target.effects.setBleedingDmg(target.effects.getBleedingDmg()+cea.amount);
		target.effects.setBleedingTurns(cea.turns);
		}
	}
	private static void Marked(CardEffectAttributes cea,BattleEntity target){
		//target.addMessageAnimation("bleeding", "red", 70);
		target.setMarked(true);
		target.effects.setMarkedTurns(cea.amount);
	}
	private static void Poisoned(CardEffectAttributes cea,BattleEntity target){
		if(!target.getImmuneToPoison()){
		target.addMessageAnimation("poisoned", "green", 70);
		target.setPoisoned(true);
		target.effects.setPoisonedDmg(target.effects.getPoisonedDmg()+cea.amount);
		target.effects.setPoisonedTurns(cea.turns);
		}
	}
	private static void attack(BattleEntity target,int amount,String dmgtype){
		
		
		if(!target.isDead()){
			if((dmgtype=="ranged"&&target.getRangedBlock()==false)||
					(dmgtype=="melee"&&target.getMeleeBlock()==false)||
					(dmgtype=="magic"&&target.getMagicBlock()==false)){
				
				if(target.effects.isMarked()&&dmgtype=="ranged"){
					amount*=2;
				}
			target.addMessageAnimation("-"+amount, "red", 70);
			target.PlayOnHitAnimation();
			target.ChangeHP(-amount);}
			else if(dmgtype=="ranged"){
				target.setRangedBlock(false);
				target.addMessageAnimation("blocked", "yellow", 90);
			}
			else if(dmgtype=="melee"){
				target.setMeleeBlock(false);
				target.addMessageAnimation("blocked", "yellow", 90);
			}
			else if(dmgtype=="magic"){
				target.setMagicBlock(false);
				target.addMessageAnimation("blocked", "yellow", 90);
			}
			}
		//target.addMessageAnimation("-"+amount, "red", 70);
		//target.PlayOnHitAnimation();
		//target.ChangeHP(-amount);
	}
	
}
