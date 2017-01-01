package com.battle.player;


import java.util.ArrayList;
import java.util.Random;

import com.battle.card.Card;
import com.battle.card.PlayerCard;

public class BattlePlayer extends BattleEntity{

	public ArrayList<Card> cardsinhand;
	
	public BattlePlayer(int maxhp,int maxsp,int maxmp,ArrayList<Card> deck)
	{
		super(maxhp, maxsp, maxmp, deck);
		cardsinhand=new ArrayList<>();
	}
	public void GetCardsInHand(int[] deckid)
	{
		for(int i=0;i<deckid.length;i++){//Adding to Hand
			cardsinhand.add(deck.get(deckid[i]));
		}
		for(int i=deckid.length-1;i>deckid.length-i-1;i--)//Removing from deck
		{
			deck.remove(cardsinhand.get(i));
		}
		
		
	}
	public void GetRandomCardsFromDeck(int amount){
		Random r=new Random();//Generating
		while(cardsinhand.size()<amount+1)
		{
			cardsinhand.add(deck.get(r.nextInt(deck.size())));
		}
		for(int i=amount-1;i>amount-i-1;i--)//Removing from deck
		{
			deck.remove(cardsinhand.get(i));
		}
	}

	
	

}
