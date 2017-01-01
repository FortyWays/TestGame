package com.battle.card;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CardStorage {
	public static HashMap<String, CardEffectAttributes[]> PlayerCards;
	public static HashMap<String, CardEffectAttributes[]> AICards;
	public static void init(){
		//Create dictionaries here
		PlayerCards=new HashMap<>();
		AICards=new HashMap<>();
		CardEffectAttributes temp[] =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("attack1",5);
		temp[0].setDmgtype("melee");
		PlayerCards.put("TestAttack1", temp);
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("gainhp", 4);
		PlayerCards.put("TestHeal1", temp);
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("attackall",2);
		temp[0].setDmgtype("melee");
		
		PlayerCards.put("TestAttack2", temp);
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("attackplayer",5);
		temp[0].setDmgtype("melee");
		AICards.put("TestAttack1", temp);
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("gainhpai", 4);
		
		
	}
	
}
