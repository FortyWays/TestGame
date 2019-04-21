package com.fortyways.storages;


import java.util.HashMap;
import com.battle.card.Card;
import com.battle.card.CardEffectAttributes;
import com.fortyways.dns.DnS;

public class CardStorage {
	public static HashMap<String, Card> Cards;
	public static void init(){
		Cards=new HashMap<>();
		
		CardEffectAttributes temp[] =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("attack1",10);
		temp[0].setDmgtype("melee");
		temp[0].needsTarget="one";
		temp[1]=new CardEffectAttributes("putbackindeck", 1);
		temp[1].card=new Card(10, 0, "TestAttack1", DnS.res.getAtlas("pack").findRegion("testAttack1"),temp);
		temp[1].card.description="Deal 10 damage.\nPut this back\nin your deck.";
		temp[1].card.cardInstructions=temp;
		Cards.put("TestAttack1",
				new Card(10, 0, "TestAttack1",DnS.res.getAtlas("pack").findRegion("testAttack1"),temp));
		Cards.get("TestAttack1").description="Deal 10 damage.\nPut this back in\n your deck.";
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("gainhp",15 );
		temp[1]=new CardEffectAttributes("cure", 0);
		Cards.put("TestHeal1", 
				new Card(0, 20, "TestHeal1",DnS.res.getAtlas("pack").findRegion("testHeal1"),temp));
		Cards.get("TestHeal1").description="Restore 15 health";
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("attackall",8);
		temp[0].setDmgtype("melee");
		temp[0].needsTarget="all";
		Cards.put("TestAttack2", 
				new Card(15, 0, "TestAttack2",DnS.res.getAtlas("pack").findRegion("testAttack2"),temp));
		Cards.get("TestAttack2").description="Deal 8 damage to all";
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("attack1",4);
		temp[0].setDmgtype("melee");
		temp[0].needsTarget="one";
		temp[1]=new CardEffectAttributes("stun",1);
		Cards.put("TestStun", 
				new Card(5, 0, "TestStun",DnS.res.getAtlas("pack").findRegion("testStun"),temp));
		Cards.get("TestStun").description="Deal 4 damage.\n"
				+ " Stun target";
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("selfsp",15);
		Cards.put("Inspired", 
				new Card(0, 10, "Inspired",DnS.res.getAtlas("pack").findRegion("InspiredCard"),temp));
		Cards.get("Inspired").description="Restore 15 stamina";
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("drawself",2);
		temp[1]=new CardEffectAttributes("blockranged",0);
		Cards.put("Bolster", 
				new Card(20, 0, "Bolster",DnS.res.getAtlas("pack").findRegion("BolsterCard"),temp));
		Cards.get("Bolster").description="Draw 2 cards.\nBlock next ranged\n attack.";
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("attack1",5);
		temp[0].setDmgtype("melee");
		temp[0].needsTarget="one";
		temp[1]=new CardEffectAttributes("bleeding",1);
		temp[1].turns=3;
		Cards.put("Bleed", 
				new Card(10, 0, "Bleed",DnS.res.getAtlas("pack").findRegion("testBleed1"),temp));
		Cards.get("Bleed").description="Deal 5 damage.\nBleeding 1 dmg for\n3 turns.";
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("attack1",15);
		temp[0].setDmgtype("ranged");
		temp[0].needsTarget="one";
		Cards.put("Steady Shot", 
				new Card(10, 0, "Steady Shot",DnS.res.getAtlas("pack").findRegion("SteadyShot"),temp));
		Cards.get("Steady Shot").description="Deal 15 damage.";
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("attack1random",10);
		temp[0].setDmgtype("ranged");
		temp[0].needsTarget="all";
		Cards.put("Quick Shot", 
				new Card(5, 0, "Quick Shot",DnS.res.getAtlas("pack").findRegion("QuickShot"),temp));
		Cards.get("Quick Shot").description="Deal 10 damage\n to a random\n target.";
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("marked",1);
		temp[0].needsTarget="one";
		Cards.put("Mark", 
				new Card(0, 5, "Mark",DnS.res.getAtlas("pack").findRegion("Mark"),temp));
		Cards.get("Mark").description="Mark a target.";
		/////////////////
		
		//TODO automaton
		temp =  new CardEffectAttributes[3];
		temp[0]=new CardEffectAttributes("attack1",5);
		temp[0].setDmgtype("melee");
		temp[0].needsTarget="one";
		temp[1]=new CardEffectAttributes("selfmp",5);
		temp[2]=new CardEffectAttributes("putbackindeck", 1);
		temp[2].card=new Card(5, 0, "AutomatonAttack", 
				DnS.res.getAtlas("pack").findRegion("AutomatonAttackCard"),temp);
		temp[2].card.description=
				"Deal 5 damage.\n Gain 5 mana.\nPut this back in\n your deck.";
		temp[2].card.cardInstructions=temp;
		Cards.put("AutomatonAttack", 
				new Card(5, 0, "AutomatonAttack",
						DnS.res.getAtlas("pack").findRegion("AutomatonAttackCard"),temp));
		Cards.get("AutomatonAttack").description=
				"Deal 5 damage.\n Gain 5 mana.\nPut this back in\n your deck.";
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("attack1",15);
		temp[0].setDmgtype("magic");
		temp[0].needsTarget="one";
		temp[1]=new CardEffectAttributes("putbackindeck", 1);
		temp[1].card=new Card(0, 10, "AutomatonAttack2", 
				DnS.res.getAtlas("pack").findRegion("AutomatonAttack2Card"),temp);
		temp[1].card.description=
				"Deal 15 damage.\n Put this back in\n your deck.";
		temp[1].card.cardInstructions=temp;
		Cards.put("AutomatonAttack2", 
				new Card(0, 10, "AutomatonAttack2",
						DnS.res.getAtlas("pack").findRegion("AutomatonAttack2Card"),temp));
		Cards.get("AutomatonAttack2").description=
				"Deal 15 damage.\n Put this back in\n your deck.";
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("gainhp",10 );
		temp[1]=new CardEffectAttributes("putbackindeck", 1);
		temp[1].card=new Card(0, 10, "Repair", 
				DnS.res.getAtlas("pack").findRegion("AutomatonRepairCard"),temp);
		temp[1].card.cardInstructions=temp;
		Cards.put("Repair", 
				new Card(0, 10, "Reair",DnS.res.getAtlas("pack").findRegion("AutomatonRepairCard"),temp));
		Cards.get("Repair").description="Restore 10 health";
		
		
		
		
		///
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("attack1",5);
		temp[0].setDmgtype("melee");
		temp[0].needsTarget="one";
		temp[1]=new CardEffectAttributes("putbackindeck", 1);
		temp[1].card=new Card(10, 0, "TestAttack1", DnS.res.getAtlas("pack").findRegion("testAttack1"),temp);
		temp[1].card.cardInstructions=temp;
		Cards.put("TestWeakAttack1", 
				new Card(10, 0, "TestWeakAttack1",DnS.res.getAtlas("pack").findRegion("testAttack1"),temp));
		Cards.get("TestWeakAttack1").description="Deal 5 damage.";
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("attack1",7);
		temp[0].setDmgtype("ranged");
		temp[0].needsTarget="one";
		Cards.put("TestRangedAttack1", 
				new Card(5, 0, "TestRangedAttack1",DnS.res.getAtlas("pack").findRegion("testAttack3"),temp));
		Cards.get("TestRangedAttack1").description="Deal 7 damage.";
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("attack1",4);
		temp[0].setDmgtype("ranged");
		temp[0].needsTarget="one";
		temp[1]=new CardEffectAttributes("poisoned", 1);
		temp[1].turns=3;
		Cards.put("Poison1", 
				new Card(10, 0, "Poison1",DnS.res.getAtlas("pack").findRegion("testAttack4"),temp));
		Cards.get("Poison1").description="Deal 4 damage.\nPoison 1 dmg\n for 3 turns";
		temp =  new CardEffectAttributes[1];
		
		temp[0]=new CardEffectAttributes("summonenemy",1);
		temp[0].needsTarget="all";
		temp[0].entityName="goblin-archer";
		Cards.put("SummonGoblin", 
				new Card(30, 0, "Summon",DnS.res.getAtlas("pack").findRegion("SummonGoblin"),temp));
		Cards.get("SummonGoblin").description="Summon random\n goblin";
		temp =  new CardEffectAttributes[3];
		temp[2]=new CardEffectAttributes("attackplayer",5);
		temp[2].setDmgtype("melee");
		temp[1]=new CardEffectAttributes("selfdmg", 5);
		temp[0]=new CardEffectAttributes("increasedp", 1);
		temp[0].needsTarget="all";
		Cards.put("SacrificialStab", 
				new Card(10, 0, "SacrificialStab",DnS.res.getAtlas("pack").findRegion("CultistCard1"),temp));
		Cards.get("SacrificialStab").description="Deal 5dmg to\n self and player.\nIncrease Dark\nPresence by 1\n";
		temp =  new CardEffectAttributes[3];
		temp[0]=new CardEffectAttributes("attack1",5);
		temp[0].setDmgtype("melee");
		temp[1]=new CardEffectAttributes("poisoned", 1);
		temp[1].turns=3;
		temp[2]=new CardEffectAttributes("bleeding",1);
		temp[2].turns=3;
		temp[0].needsTarget="one";
		Cards.put("EldritchAttack", 
				new Card(0, 10, "EldritchAttack",DnS.res.getAtlas("pack").findRegion("CultistCard2"),temp));
		Cards.get("EldritchAttack").description="Deal 5dmg to\n player.\n"
				+ "Bleeding 1 dmg\nfor 3 turns\nPoison 1 dmg\n for 3 turns";
		Cards.get("EldritchAttack").requiredDP=1;
		temp =  new CardEffectAttributes[2];
		temp[0]=new CardEffectAttributes("attack1",2);
		temp[0].setDmgtype("ranged");
		temp[1]=new CardEffectAttributes("poisoned", 1);
		temp[1].turns=3;
		Cards.put("Slime", 
				new Card(0, 10, "Slime",DnS.res.getAtlas("pack").findRegion("SlimeCard"),temp));
		Cards.get("Slime").description="Deal 2 dmg\nPoison 1 dmg\n for 3 turns";
		temp =  new CardEffectAttributes[1];
		temp[0]=new CardEffectAttributes("summonforeachdp",0);
		temp[0].needsTarget="all";
		temp[0].entityName="slimeboy";
		Cards.put("EldritchSlime", 
				new Card(0, 15, "EldritchSlime",DnS.res.getAtlas("pack").findRegion("SummonGoblin"),temp));
		Cards.get("EldritchSlime").description="For each Dark\n"
				+ "Presence level\nsummon a Slime";
		Cards.get("EldritchSlime").requiredDP=2;
		
	}
	public static Card getCard(String name){
		if(Cards.containsKey(name)){
			return Cards.get(name);
		}
		return null;
	}
	
}
