package com.battle.graphics;

import java.util.ArrayList;

import com.battle.card.Card;
import com.battle.player.BattleEntity;
import com.battle.player.BattlePlayer;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;

public class PlayerCardsAnimation {

	
	public static  int[] newPositions;
	public static boolean finished=true;
	
	public static void startAnimation(BattleEntity player,PlayerCards playerCards){
		
		finished=false;
		for(int i=playerCards.cards.size();i<player.cardsInHand.size();i++){
			Card card=player.cardsInHand.get(i);
				playerCards.cards.add(new Graphic(DnS.WIDTH-30*2*(player.cardsInHand.size()-i), 50, card.cardArt));
		}
		
		newPositions=new int[playerCards.cards.size()];
		for (int i=0;i<newPositions.length;i++) {
			newPositions[i]=i*60+240;
		}
	}
	
	public static void update(float dt,PlayerCards playerCards){
		
		if(!finished){
			for (int i=0;i<newPositions.length;i++) {
				if(i<playerCards.cards.size()){
				Graphic card=playerCards.cards.get(i);
				if(card.x!=newPositions[i]){
					card.x-=5;
				}
				else if(i==newPositions.length-1){
					finished=true;
				}
				}
			}
			
		}
	}
	
	
}
