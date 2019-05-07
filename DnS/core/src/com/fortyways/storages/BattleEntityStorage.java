package com.fortyways.storages;

import java.util.ArrayList;
import java.util.HashMap;

import com.battle.card.Card;
import com.battle.player.AIDecisionMaking;
import com.battle.player.BattleEnemy;
import com.battle.player.BattleEntity;
import com.battle.player.CultistAI;
import com.battle.player.GoblinAI;
import com.battle.player.HealTotemMinionAI;
import com.battle.player.SkeletonMinionAI;
import com.battle.player.StandardAI;
import com.fortyways.dns.DnS;
import com.fortyways.state.BattleState;

public class BattleEntityStorage {

	private static HashMap<String, ArrayList<Card>> decks;
	private static HashMap<String, int[]> stats;//0-hp,1-sp,2-mp
	private static HashMap<String, int[]> regens;
	
	
	public static void init(){
		decks=new HashMap<>();
		ArrayList<Card> tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestHeal1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("SummonGoblin"));
		decks.put("goblin-berserk", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("TestRangedAttack1"));
		tempdeck.add(CardStorage.getCard("TestRangedAttack1"));
		tempdeck.add(CardStorage.getCard("TestRangedAttack1"));
		tempdeck.add(CardStorage.getCard("TestRangedAttack1"));
		tempdeck.add(CardStorage.getCard("TestHeal1"));
		tempdeck.add(CardStorage.getCard("Poison1"));
		tempdeck.add(CardStorage.getCard("Poison1"));
		//tempdeck.add(CardStorage.getCard("SummonGoblin"));
		decks.put("goblin-archer", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("SacrificialStab"));
		tempdeck.add(CardStorage.getCard("SacrificialStab"));
		tempdeck.add(CardStorage.getCard("SacrificialStab"));
		tempdeck.add(CardStorage.getCard("SacrificialStab"));
		tempdeck.add(CardStorage.getCard("EldritchSlime"));
		tempdeck.add(CardStorage.getCard("EldritchSlime"));
		tempdeck.add(CardStorage.getCard("EldritchSlime"));
		tempdeck.add(CardStorage.getCard("EldritchAttack"));
		tempdeck.add(CardStorage.getCard("EldritchAttack"));
		tempdeck.add(CardStorage.getCard("TestHeal1"));
		decks.put("cultist", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("Slime"));
		tempdeck.add(CardStorage.getCard("Slime"));
		tempdeck.add(CardStorage.getCard("Slime"));
		tempdeck.add(CardStorage.getCard("Slime"));
		tempdeck.add(CardStorage.getCard("Slime"));
		tempdeck.add(CardStorage.getCard("Slime"));
		decks.put("slimeboy", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("AutomatonAttack"));
		tempdeck.add(CardStorage.getCard("AutomatonAttack"));
		tempdeck.add(CardStorage.getCard("AutomatonAttack"));
		tempdeck.add(CardStorage.getCard("Repair"));
		tempdeck.add(CardStorage.getCard("AutomatonAttack2"));
		decks.put("automaton", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("BoneSmack"));
		tempdeck.add(CardStorage.getCard("BoneSmack"));
		tempdeck.add(CardStorage.getCard("Bone Throw"));
		tempdeck.add(CardStorage.getCard("Bone Throw"));
		tempdeck.add(CardStorage.getCard("Bone Throw"));
		decks.put("skeleton-minion", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("BoneSmack"));
		tempdeck.add(CardStorage.getCard("BoneSmack"));
		tempdeck.add(CardStorage.getCard("BoneSmack"));
		tempdeck.add(CardStorage.getCard("Bone Throw"));
		tempdeck.add(CardStorage.getCard("Bone Throw"));
		tempdeck.add(CardStorage.getCard("Bone Throw"));
		decks.put("skeleton", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("TotemHeal"));
		tempdeck.add(CardStorage.getCard("TotemHeal"));
		tempdeck.add(CardStorage.getCard("TotemHeal"));
		tempdeck.add(CardStorage.getCard("TotemHeal"));
		decks.put("healtotem", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("SummonSlime"));
		tempdeck.add(CardStorage.getCard("SummonBabySlime"));
		tempdeck.add(CardStorage.getCard("SummonBabySlime"));
		//tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		//tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		//tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		decks.put("slime", tempdeck);
		tempdeck=new ArrayList<>();
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		tempdeck.add(CardStorage.getCard("TestWeakAttack1"));
		decks.put("babyslime", tempdeck);
		
		stats=new HashMap<>();
		int[] tempstats=new int[3];
		tempstats[0]=50;
		tempstats[1]=40;
		tempstats[2]=30;
		stats.put("goblin-berserk", tempstats);
		tempstats=new int[3];
		tempstats[0]=30;
		tempstats[1]=30;
		tempstats[2]=30;
		stats.put("goblin-archer", tempstats);
		tempstats=new int[3];
		tempstats[0]=40;
		tempstats[1]=50;
		tempstats[2]=50;
		stats.put("cultist", tempstats);
		tempstats=new int[3];
		tempstats[0]=20;
		tempstats[1]=20;
		tempstats[2]=10;
		stats.put("slimeboy", tempstats);
		tempstats=new int[3];
		tempstats[0]=40;
		tempstats[1]=40;
		tempstats[2]=40;
		stats.put("automaton", tempstats);
		tempstats=new int[3];
		tempstats[0]=10;
		tempstats[1]=10;
		tempstats[2]=5;
		stats.put("skeleton-minion", tempstats);
		tempstats=new int[3];
		tempstats[0]=20;
		tempstats[1]=20;
		tempstats[2]=5;
		stats.put("skeleton", tempstats);
		tempstats=new int[3];
		tempstats[0]=20;
		tempstats[1]=20;
		tempstats[2]=20;
		stats.put("healtotem", tempstats);
		tempstats=new int[3];
		tempstats[0]=20;
		tempstats[1]=20;
		tempstats[2]=20;
		stats.put("slime", tempstats);
		tempstats=new int[3];
		tempstats[0]=10;
		tempstats[1]=10;
		tempstats[2]=10;
		stats.put("babyslime", tempstats);
		
		regens=new HashMap<>();
		int[] tempregens=new int[3];
		tempregens[0]=0;
		tempregens[1]=5;
		tempregens[2]=5;
		regens.put("goblin-berserk", tempregens);
		tempregens=new int[3];
		tempregens[0]=1;
		tempregens[1]=3;
		tempregens[2]=10;
		regens.put("cultist", tempregens);
		tempregens=new int[3];
		tempregens[0]=0;
		tempregens[1]=5;
		tempregens[2]=5;
		regens.put("automaton", tempregens);
		tempregens=new int[3];
		tempregens[0]=0;
		tempregens[1]=5;
		tempregens[2]=5;
		regens.put("goblin-archer", tempregens);
		tempregens=new int[3];
		tempregens[0]=0;
		tempregens[1]=2;
		tempregens[2]=0;
		regens.put("slimeboy", tempregens);
		tempregens=new int[3];
		tempregens[0]=0;
		tempregens[1]=5;
		tempregens[2]=0;
		regens.put("skeleton-minion", tempregens);
		tempregens=new int[3];
		tempregens[0]=0;
		tempregens[1]=5;
		tempregens[2]=0;
		regens.put("skeleton", tempregens);
		tempregens=new int[3];
		tempregens[0]=0;
		tempregens[1]=5;
		tempregens[2]=5;
		regens.put("healtotem", tempregens);
		tempregens=new int[3];
		tempregens[0]=1;
		tempregens[1]=5;
		tempregens[2]=5;
		regens.put("slime", tempregens);
		tempregens=new int[3];
		tempregens[0]=1;
		tempregens[1]=5;
		tempregens[2]=5;
		regens.put("babyslime", tempregens);
	}
	public static ArrayList<Card> getDeck(String name){
		if(decks.containsKey(name)){
			ArrayList<Card> newdeck=new ArrayList<>();
			for(Card card: decks.get(name)){
				newdeck.add(card);
			}
			return newdeck;
		}
		return null;
	}
	public static int[] getStats(String name){
		if(stats.containsKey(name)){
			return stats.get(name);
		}
		return null;
	}
	public static int[] getRegens(String name){
		if(regens.containsKey(name)){
			return regens.get(name);
		}
		return null;
	}
	public void setEffects(BattleEntity ent){
		if(ent.entityName=="slimeboy"){
			ent.setImmuneToPoison(true);
		}
		else if(ent.entityName=="skeleton-minion"||ent.entityName=="skeleton"){
			ent.setImmuneToBleed(true);
		}
		else if(ent.entityName=="automaton"){
			ent.setImmuneToPoison(true);
			ent.setImmuneToBleed(true);
		}
	}
	
	public static AIDecisionMaking getAI(BattleEntity ent,BattleState state){
		if(ent.entityName=="goblin-berserk"){
			return new GoblinAI(state, ent);
		}
		else if(ent.entityName=="goblin-archer"){
			return new GoblinAI(state, ent);
		}
		else if(ent.entityName=="cultist"){
			return new CultistAI(state, ent);
		}
		else if(ent.entityName=="slimeboy"){
			return new StandardAI(state, ent);
		}
		else if(ent.entityName=="skeleton"){
			return new StandardAI(state, ent);
		}
		else if(ent.entityName=="automaton"){
			return new StandardAI(state, ent);
		}
		if(ent.entityName=="skeleton-minion"){
			return new SkeletonMinionAI(state, ent);
		}
		if(ent.entityName=="healtotem"){
			return new HealTotemMinionAI(state, ent);
		}
		if(ent.entityName=="slime"){
			return new GoblinAI(state, ent);
		}
		if(ent.entityName=="babyslime"){
			return new StandardAI(state, ent);
		}
		return null;
	}
	
}
