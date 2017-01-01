package com.battle.player;

import java.util.ArrayList;

import com.battle.card.Card;


public class BattleEnemy extends BattleEntity{
	public BattleEnemy(int maxhp,int maxsp,int maxmp,ArrayList<Card> deck) {
		super(maxhp, maxsp, maxmp, deck);
		
	}
}
