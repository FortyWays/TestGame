package com.battle.player;

import java.util.ArrayList;
import java.util.HashMap;

import com.battle.card.Card;
import com.battle.card.CardEffectAttributes;
import com.fortyways.state.BattleState;
import com.fortyways.util.Graphic;

public class HealTotemMinionAI extends AIDecisionMaking{

	public HealTotemMinionAI(BattleState state, BattleEntity ai) {
		super(state, ai);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void MakeDecision() {
		HashMap<Card, BattleEntity> decision=new HashMap<>();
		CategorizeCards();
		if(state.player.hp<state.player.maxhp){
			ArrayList<Card> gonnaPlay=GetHealing();
				if(gonnaPlay.size()>0){
				Card card=gonnaPlay.get(0);
				if(ai.sp>=card.spcost&&ai.mp>=card.mpcost){
				decision.put(card, state.player);
				}
			}
		}
		chosenCards=decision;
		System.out.println(chosenCards.size());
		if(!chosenCards.isEmpty()){
			it=chosenCards.entrySet().iterator();
		currentCard=it.next().getKey();
		card=new Graphic(ai.getIdleAnim().getX(), ai.getIdleAnim().getY(),
				currentCard.cardArt.getRegionWidth()/2,
				currentCard.cardArt.getRegionHeight()/2,currentCard.cardArt);
		alpha=1;
		}
		
	}

	@Override
	protected void CategorizeCards() {
	
		attackCards=new ArrayList<>();
		buffCards=new ArrayList<>();
		sustainCards=new ArrayList<>();
		//System.out.println("cards"+ai.cardsInHand.size());
		for(Card card:ai.cardsInHand){
			for(CardEffectAttributes cea: card.cardInstructions){
				System.out.println(cea.entityName);
				if(cea.effectid=="selfhp"
					||cea.effectid=="gainhp"
					||cea.effectid=="selfmp"
					||cea.effectid=="selfsp"
					||cea.effectid=="gainmp"
					||cea.effectid=="gainsp"
					){
				System.out.println("added");
				sustainCards.add(card);
				break;
			}
			
			}
		}
		
	}
	private BattleEntity getLowestHealthAlly() {
		BattleEntity res=state.player;
		
		if(res.hp<res.maxhp){
		return res;}
		else return null;
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
