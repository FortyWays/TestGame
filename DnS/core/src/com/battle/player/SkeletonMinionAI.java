package com.battle.player;

import java.util.ArrayList;
import java.util.HashMap;

import com.battle.card.Card;
import com.battle.card.CardEffectAttributes;
import com.fortyways.state.BattleState;
import com.fortyways.util.Graphic;

public class SkeletonMinionAI extends AIDecisionMaking {
	private ArrayList<Card> summonCards;
	public SkeletonMinionAI(BattleState state, BattleEntity ai) {
		super(state, ai);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void MakeDecision() {
		HashMap<Card, BattleEntity> decision=new HashMap<>();
		CategorizeCards();
		if(summonCards.size()>0&&(state.minions.size()<3)){
			for(Card card:summonCards){
				if(ai.sp>=card.spcost&&ai.mp>=card.mpcost){
				decision.put(card, null);
				break;}
			}
		}
		else if(NeedsHealing(0.4f)&&hasHealing()){
			ArrayList<Card> gonnaPlay=GetHealing();
			for(Card card: gonnaPlay){
				if(ai.sp>=card.spcost&&ai.mp>=card.mpcost){
				decision.put(card, ai);
				}
			}
		}
		else{
			ArrayList<Card> gonnaPlay=getCurrentPossibleDamage();
			for(Card card: gonnaPlay){
				if(ai.sp>=card.spcost&&ai.mp>=card.mpcost){
				decision.put(card, getLowestHealthEnemy());}
			}
		}
		chosenCards=decision;
		if(!chosenCards.isEmpty()){
			it=chosenCards.entrySet().iterator();
		currentCard=it.next().getKey();
		card=new Graphic(ai.getIdleAnim().getX(), ai.getIdleAnim().getY(),
				currentCard.cardArt.getRegionWidth()/2,
				currentCard.cardArt.getRegionHeight()/2,currentCard.cardArt);
		alpha=1;
		}
		
	}

	private BattleEntity getLowestHealthEnemy() {
		BattleEntity res=state.player;
		int min=1000000;
		for(BattleEntity enemy:state.enemies){
			if(enemy.hp<min&&!enemy.isDead()){
				min=enemy.hp;
				res=enemy;
			}
		}
		
		return res;
	}

	@Override
	protected void CategorizeCards() {
		summonCards=new ArrayList<>();
		attackCards=new ArrayList<>();
		buffCards=new ArrayList<>();
		sustainCards=new ArrayList<>();
		for(Card card:ai.cardsInHand){
			for(CardEffectAttributes cea: card.cardInstructions)
			if(cea.effectid=="selfhp"
					||cea.effectid=="gainhp"
					||cea.effectid=="selfmp"
					||cea.effectid=="selfsp"
					||cea.effectid=="gainmp"
					||cea.effectid=="gainsp"
					){
				sustainCards.add(card);
				break;
			}
			else if(cea.effectid=="attack1"){
				attackCards.add(card);
				break;
			}
			else if(cea.effectid=="summonminion"){
				summonCards.add(card);
				break;
			}
			
		}
		
	}

	@Override
	protected ArrayList<Card> GetHealing() {
		ArrayList<Card> heals=new ArrayList<>();
		for(Card card: sustainCards){
			for(CardEffectAttributes cea: card.cardInstructions)
				
			if(cea.effectid=="selfhp"||cea.effectid=="gainhp"){
				heals.add(card);
				break;
			}
			
		}
		return heals;
	}

}
