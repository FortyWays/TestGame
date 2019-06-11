package com.fortyways.util;

import java.util.ArrayList;

import com.battle.card.Card;
import com.battle.player.BattlePlayer;
import com.encounter.EncounterPlayer;
import com.fortyways.storages.CardStorage;
import com.stage.items.Item;
import com.stage.player.StagePlayer;

public class StageToBattleTransfer {

	public static BattlePlayer trasferPlayer(EncounterPlayer player){
		BattlePlayer result;

		ArrayList<Card>playerDeck=new ArrayList<>();
		if(player.playerName=="player-ranger"){
			//Generating test decks
			playerDeck.add(CardStorage.getCard("Mark"));
			playerDeck.add(CardStorage.getCard("Mark"));
			/*playerDeck.add(CardStorage.getCard("Quick Shot"));
			playerDeck.add(CardStorage.getCard("Quick Shot"));
			playerDeck.add(CardStorage.getCard("Quick Shot"));
			playerDeck.add(CardStorage.getCard("Quick Shot"));
			playerDeck.add(CardStorage.getCard("Steady Shot"));
			playerDeck.add(CardStorage.getCard("Steady Shot"));
			playerDeck.add(CardStorage.getCard("Steady Shot"));
			playerDeck.add(CardStorage.getCard("Steady Shot"));*/
			//playerDeck.add(CardStorage.getCard("TestHeal1"));
			for(Item item:player.equipped){
				if(!item.getCards().isEmpty()){
					for(Card card:item.getCards()){
						playerDeck.add(card);
					}
				}
			}
			for(Card card:player.extraCards){
				playerDeck.add(card);
			}
			result=new BattlePlayer(player.getMaxhp(),player.getMaxsp() , player.getMaxmp(),
					player.getHpregen(), player.getSpregen(), player.getMpregen(), playerDeck,player.playerName);
			result.setStartingCardAmount(4);
		}
		else{
			//Generating test decks

		//	playerDeck.add(CardStorage.getCard("TestAttack1"));
		//	playerDeck.add(CardStorage.getCard("TestAttack1"));
		//	playerDeck.add(CardStorage.getCard("TestAttack1"));
		//	playerDeck.add(CardStorage.getCard("TestHeal1"));
		//	playerDeck.add(CardStorage.getCard("TestAttack2"));
		//	playerDeck.add(CardStorage.getCard("TestAttack2"));
		//	playerDeck.add(CardStorage.getCard("TestStun"));
		//	playerDeck.add(CardStorage.getCard("TestStun"));
		//	playerDeck.add(CardStorage.getCard("Inspired"));
		//	playerDeck.add(CardStorage.getCard("Inspired"));
		//	playerDeck.add(CardStorage.getCard("Bolster"));
		//	playerDeck.add(CardStorage.getCard("Bolster"));
		
			
			for(Item item:player.equipped){
				if(!item.getCards().isEmpty()){
					for(Card card:item.getCards()){
						playerDeck.add(card);
					}
				}
			}
			for(Card card:player.extraCards){
				playerDeck.add(card);
			}
			result=new BattlePlayer(player.getMaxhp(),player.getMaxsp() , player.getMaxmp(),
			player.getHpregen(), player.getSpregen(), player.getMpregen(), playerDeck,"player-warrior");
			result.setStartingCardAmount(6);
		}
		result.setMeleeBlock(player.isMeleeBlock());
		result.setRangedFire(player.pie.isRangedFire());
		result.setDpMod(player.pie.getDpMod());
		result.setMagicBlock(player.isMagicBlock());
		result.setRangedBlock(player.isRangedBlock());
		result.pie.setResetHP(player.pie.isResetHP());
		result.setImmuneToPoison(player.isImmunePoison());
		result.setImmuneToBleed(player.isImmuneBleed());
		result.ChangeHP(-player.getMaxhp()+player.getHp());
		result.ChangeSP(-player.getMaxsp()+player.getSp());
		result.ChangeMP(-player.getMaxmp()+player.getMp());
		result.cardsPerTurn=player.getCardsPerTurn();
		
		return result;
	}
	
}
