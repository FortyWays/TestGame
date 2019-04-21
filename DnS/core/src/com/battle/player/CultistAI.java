package com.battle.player;

import java.util.ArrayList;
import java.util.HashMap;

import com.battle.card.Card;
import com.battle.card.CardEffectAttributes;
import com.fortyways.state.BattleState;
import com.fortyways.util.Graphic;

public class CultistAI extends AIDecisionMaking{

	private ArrayList<Card>dpRaise;
	private ArrayList<Card>dpCards;
	private int cardsPerTurn=1;
	
	public CultistAI(BattleState state, BattleEntity ai) {
		super(state, ai);
	}

	@Override
	public void MakeDecision() {
		HashMap<Card, BattleEntity> decision=new HashMap<>();
		CategorizeCards();
		if(state.DarkPresence>=1&&dpCards.size()>0
				&&decision.size()<cardsPerTurn){
			for(Card card:dpCards){
				if(card.requiredDP<=state.DarkPresence){
					decision.put(card, state.player);
					break;
				}
			}
			
		}
		
		if(dpRaise.size()>0
				&&decision.size()<cardsPerTurn){
			decision.put(dpRaise.get(0), state.player);
		}

		if(decision.size()<cardsPerTurn){
			ArrayList<Card> gonnaPlay=getCurrentPossibleDamage();
			if(gonnaPlay.size()>0)
			decision.put(gonnaPlay.get(0), state.player);
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
		dpCards=new ArrayList<>();
		dpRaise=new ArrayList<>();
		for(Card card:ai.cardsInHand){
			if(card.requiredDP>0){
				dpCards.add(card);
				//break;
			}
			else
			for(CardEffectAttributes cea: card.cardInstructions){
				if(cea.effectid=="increasedp"){
					dpRaise.add(card);
					break;
				}
				else if(cea.effectid=="selfhp"
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
					break;
				}
				
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
