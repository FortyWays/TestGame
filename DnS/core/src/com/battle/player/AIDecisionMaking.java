package com.battle.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;
import com.battle.card.CardEffectAttributes;
import com.fortyways.state.BattleState;
import com.fortyways.util.Graphic;

public abstract class AIDecisionMaking {
	BattleState state;
	BattleEntity ai;
	
	ArrayList<Card> attackCards;
	ArrayList<Card> buffCards;
	ArrayList<Card> sustainCards;
	
	protected boolean animating=false;
	protected Card currentCard;
	protected Graphic card;
	protected Iterator<Entry<Card,BattleEntity>> it;
	public HashMap<Card, BattleEntity> chosenCards;
	
	
	public AIDecisionMaking(BattleState state,BattleEntity ai) {
		this.state=state;
		this.ai=ai;
	}
	public abstract void MakeDecision();
	
	protected abstract void CategorizeCards();
	
	protected abstract ArrayList<Card> GetHealing();
	
	protected boolean NeedsHealing(float percent){
		if(ai.getHp()<=ai.maxhp*percent){
			return true;
		}
		return false;
	}
	
	protected ArrayList<Card> getCurrentPossibleDamage(){
		int curMp=ai.mp,curSp=ai.sp;
		ArrayList<Card> temp=new ArrayList<>();
		for(int i=0;i<attackCards.size();i++){
			temp.add(attackCards.get(i));
		}
		ArrayList<Card> result=new ArrayList<>();
		for(int j=0;j<ai.cardsPerTurn;j++){
			if(temp.size()>0)
			{
				int max=0;
				int maxnum=0;
				for(int i=0;i<temp.size();i++){
					Card card=temp.get(i);
					if(card.cardInstructions[0].amount>=max){
						max=card.cardInstructions[0].amount;
						maxnum=i;
					}
				}
			//	if(curMp>=temp.get(maxnum).mpcost&&curSp>=temp.get(maxnum).spcost){
			//	curMp-=temp.get(maxnum).mpcost;	
			//	curSp-=temp.get(maxnum).spcost;
				result.add(temp.get(maxnum));
				temp.remove(maxnum);//}
			}
		}
		return result;
	}
	protected int CurrentPossibleDamage(){
		int curMp=ai.mp,curSp=ai.sp;
		ArrayList<Card> temp=new ArrayList<>();
		for(int i=0;i<attackCards.size();i++){
			temp.add(attackCards.get(i));
		}
		int dmg=0;
		for(int j=0;j<ai.cardsPerTurn;j++){
			if(temp.size()>0)
			{
				int max=0;
				int maxnum=0;
				for(int i=0;i<temp.size();i++){
					Card card=temp.get(i);
					if(card.cardInstructions[0].amount>=max){
						max=card.cardInstructions[0].amount;
						maxnum=i;
					}
				}
				//if(curMp>=temp.get(maxnum).mpcost&&curSp>=temp.get(maxnum).spcost){
				//	curMp-=temp.get(maxnum).mpcost;	
				//	curSp-=temp.get(maxnum).spcost;
				dmg+=max;
				temp.remove(maxnum);
				//}
			}
		}
		return dmg;
	}
	protected boolean hasHealing() {
		for(Card card:sustainCards){
			for(CardEffectAttributes cea: card.cardInstructions)
			if(cea.effectid=="selfhp"
					||cea.effectid=="gainhp"){
				return true;
			}
		}
		return false;
	}
	protected float alpha=1;
	public void update(float dt){
		if(card!=null && animating){
			if(card.width<card.image.getRegionWidth()){
				card.width+=1;
				card.height+=1;
				card.y+=1;
			}
			else if(card.width<card.image.getRegionWidth()*3){
				if(alpha>0)
				alpha-=0.01f;
				card.width+=1;
				card.height+=1;
				card.y+=1;
			}
			else{
				if(ai.cardsInHand.contains(currentCard)){
					if(currentCard.cardInstructions[0].needsTarget!="all"){
				ai.cardsInHand.get(ai.cardsInHand.indexOf(currentCard))
				.Play(ai, chosenCards.get(currentCard));
					}
				else{
					ai.cardsInHand.get(ai.cardsInHand.indexOf(currentCard))
					.Play(ai, state);
				}
				ai.cardsInHand.remove(currentCard);
				getNextCard();
				}
				else{
					alpha=1;
					animating=false;
					chosenCards=null;
					it=null;
				}
				
			}
			
		}
	}
	private void getNextCard() {
		if(it.hasNext()){
		currentCard=it.next().getKey();
		card=new Graphic(ai.getIdleAnim().x, ai.getIdleAnim().y,
				currentCard.cardArt.getRegionWidth()/2,
				currentCard.cardArt.getRegionHeight()/2,currentCard.cardArt);
		alpha=1;
		}
		else{
		animating=false;
		chosenCards=null;
		it=null;
		}
	}
	public void render(SpriteBatch spriteBatch){
		if(card!=null){
			spriteBatch.setColor(1, 1, 1, alpha);
			card.render(spriteBatch);
			spriteBatch.setColor(1, 1, 1, 1);
		}
	}
	public boolean isAnimating() {
		return animating;
	}
	public void setAnimating(boolean animating) {
		this.animating = animating;
	}
	public boolean decided(){
		if(chosenCards==null){
			return false;
		}
		if(chosenCards.isEmpty()){
			return false;
		}
		return true;
	}
	
}
