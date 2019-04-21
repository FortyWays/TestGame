package com.battle.player;

import java.util.ArrayList;
import java.util.HashMap;

import com.battle.card.Card;
import com.battle.card.CardEffectAttributes;
import com.fortyways.dns.DnS;
import com.fortyways.state.BattleState;
import com.fortyways.util.Graphic;

public class StandardAI extends AIDecisionMaking{

	public StandardAI(BattleState state, BattleEntity ai) {
		super(state, ai);
	
	}

	@Override
	public void MakeDecision() {
		HashMap<Card, BattleEntity> decision=new HashMap<>();
		CategorizeCards();
		if(CurrentPossibleDamage()>state.player.hp){
			ArrayList<Card> gonnaPlay=getCurrentPossibleDamage();
			for(Card card: gonnaPlay){
				decision.put(card, state.player);
				
			}
		}
		else if(NeedsHealing(0.4f)&&hasHealing()){
			ArrayList<Card> gonnaPlay=GetHealing();
			for(Card card: gonnaPlay){
				decision.put(card, ai);
			}
		}
		else{
			ArrayList<Card> gonnaPlay=getCurrentPossibleDamage();
			for(Card card: gonnaPlay){
				decision.put(card, state.player);
				
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

	@Override
	protected void CategorizeCards() {
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
			else if(cea.effectid=="attack1"||
					cea.effectid=="attackall"){
				attackCards.add(card);
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
