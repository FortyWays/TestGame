package com.fortyways.storages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.battle.player.BattleEnemy;
import com.encounter.Encounter;
import com.encounter.Option;
import com.encounter.Outcome;
import com.fortyways.dns.DnS;
import com.stage.items.Item;

public class EncounterStorage {
	public static HashMap<String, Encounter> Encounters;
	public static void init(){
		Encounters=new HashMap<>();
		Encounter encounter =new Encounter("OrcCamp", 
				DnS.res.getAtlas("pack").findRegion("OrcCampEncounter"),
				"You notice a bunch of orcs set up a camp ahead of you.\n You consider your options.");
		ArrayList<Option> options=new ArrayList<>();
		Option option=new Option("Attack them",0);
		ArrayList<String> enemyTags=new ArrayList<>();
		enemyTags.add("goblin-berserk");
		enemyTags.add("goblin-archer");
		Outcome outcome = new Outcome("You won", enemyTags);
		outcome.foodgain=2;
		outcome.moneygain=15;
		
		ArrayList<Outcome> outcomes=new ArrayList<>();
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		option=new Option("Sneak past them", 1);
		outcome=new Outcome("You've successfully escaped the orcs");
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		encounter.setOptions(options);
		Encounters.put("OrcCamp", encounter);
		
		encounter =new Encounter("Cultists", 
				DnS.res.getAtlas("pack").findRegion("CultistsEncounter"),
				"A group of cultists are gathered ahead of you for another nefarious ritual \n They haven't noticed you yet.");
		options=new ArrayList<>();
		option=new Option("Attack them",0);
		enemyTags=new ArrayList<>();
		enemyTags.add("cultist");
		outcome = new Outcome("Now that cultists are dead you search through their belongings", enemyTags);
		outcome.foodgain=5;
		outcome.moneygain=30;
		outcome.famegain=1;
		outcome.itemgain=true;
		outcome.item=ItemStorage.getItem("Shield Ring");
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		option=new Option("Sneak past them", 1);
		outcome=new Outcome("You manage to avoid being seen");
		outcome.dpgain=2;
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		encounter.setOptions(options);
		Encounters.put("Cultists", encounter);
		
		encounter =new Encounter("Lucky Find", 
				DnS.res.getAtlas("pack").findRegion("LuckyFindEncounter"),
				"Right in front of you, you see an old chest burried into the ground \n");
		options=new ArrayList<>();
		option=new Option("Bust it open",0);
		outcome = new Outcome("The chest was trapped!");
		outcome.hpgain=-10;
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		outcome = new Outcome("Gold inside!");
		outcome.moneygain=20;
		outcomes.add(outcome);
		outcome = new Outcome("Treasure inside!");
		outcome.itemgain=true;
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		encounter.setOptions(options);
		Encounters.put("Lucky Find", encounter);
		

		encounter =new Encounter("Slimes!", 
				DnS.res.getAtlas("pack").findRegion("BossEncounter"),
				"There is a weird gelatinous mass in your way"
				+ "\n It seems to have some remains of previous adventurers stuck in it");
		options=new ArrayList<>();
		option=new Option("Attack it!",0);
		enemyTags=new ArrayList<>();
		enemyTags.add("slime");
		enemyTags.add("slime");
		outcome = new Outcome("You found a valuable artifact!", enemyTags);
		outcome.itemgain=true;
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		outcome = new Outcome("You found some gold!", enemyTags);
		outcome.moneygain=20;
		option.setOutcomes(outcomes);
		options.add(option);
		option=new Option("Try to feed it", 1);
		option.setRequiredFood(2);
		outcome=new Outcome("It consumes the food and lets you through");
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		encounter.setOptions(options);
		Encounters.put("Slimes!", encounter);
		

		encounter =new Encounter("Strange Shrine", 
				DnS.res.getAtlas("pack").findRegion("BossEncounter"),
				"There strange shrine to an unknown deity amidst the sands."
				+ "\n It seems to await some sort of offering.");
		options=new ArrayList<>();
		option=new Option("Offer your blood",0);
		
		outcome = new Outcome("You feel a new power inside of you(d)");
		outcome.awardCards.add(CardStorage.getCard("PurityDefense"));
		outcome.hpgain=-15;
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		outcome = new Outcome("You feel a new power inside of you(a)");
		outcome.awardCards.add(CardStorage.getCard("PurityAttack"));
		outcome.hpgain=-15;
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		
		option=new Option("Offer Food", 1);
		option.setRequiredFood(2);
		outcome=new Outcome("You feel rejuvenated");
		outcome.hpgain=15;
		outcome.spgain=15;
		outcome.mpgain=15;
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);

		option=new Option("Offer Money", 2);
		option.setRequiredMoney(50);
		outcome=new Outcome("A strange object appears...");
		outcome.itemgain=true;
		outcomes=new ArrayList<>();
		outcomes.add(outcome);
		option.setOutcomes(outcomes);
		options.add(option);
		encounter.setOptions(options);
		Encounters.put("Strange Shrine", encounter);
		
		
		
	}
	public static Encounter getRandomEncounter(){
		Random temp=new Random();
		int num=temp.nextInt(Encounters.size());
		Iterator<Encounter> it=Encounters.values().iterator();
		Encounter e=new Encounter();
		int i=0;
		while(it.hasNext()){
			
			e=it.next();
			if(num==i){
				break;
			}
			i++;
		}
	
		//
		//t=possibleItems.get(possibleItems.size()-1);
		//
		return e;
	}
	
}
