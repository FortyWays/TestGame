package com.fortyways.storages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.battle.card.Card;
import com.encounter.EncounterPlayer;
import com.fortyways.dns.DnS;
import com.stage.items.Item;
import com.stage.items.ItemEffectAttributes;
import com.stage.player.StagePlayer;

public class ItemStorage {

	public static HashMap<String, Item> Items;
	public static void init(){
		Items=new HashMap<>();
		
		ItemEffectAttributes[] temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("addcardsperturn", 1);
		Items.put("Speed Stone", new Item("Speed Stone",
				"You can play one extra card per turn", 500,
				temp, DnS.res.getAtlas("pack").findRegion("SpeedStone")));
		
		temp =new ItemEffectAttributes[3];
		temp[0]=new ItemEffectAttributes("meleeblock", 1);
		temp[1]=new ItemEffectAttributes("rangedblock", 1);
		temp[2]=new ItemEffectAttributes("magicblock", 1);
		Items.put("Shield Ring", new Item("Shield Ring",
				"Gain Melee, Ranged and Magic Block at the start of the battle ", 500,
				temp, DnS.res.getAtlas("pack").findRegion("ShieldRing")));
		
		temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("addmaxhp", 10);
		Items.put("Small Health Stone", new Item("Small Health Stone",
				"Gain 10 maximum health ", 100,
				temp, DnS.res.getAtlas("pack").findRegion("SmallHealthStone")));
		temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("addmaxsp", 10);
		Items.put("Small Stamina Stone", new Item("Small Stamina Stone",
				"Gain 10 maximum stamina ", 100,
				temp, DnS.res.getAtlas("pack").findRegion("SmallStaminaStone")));
		temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("addmaxmp", 10);
		Items.put("Small Mana Stone", new Item("Small Mana Stone",
				"Gain 10 maximum mana ", 100,
				temp, DnS.res.getAtlas("pack").findRegion("SmallManaStone")));
		
		
		temp =new ItemEffectAttributes[2];
		temp[0]=new ItemEffectAttributes("setmaxhp", 30);
		temp[1]=new ItemEffectAttributes("resethp", 1);
		Items.put("Spellbound Armor", new Item("Spellbound Armor",
				"Set your hp to 30 reset it at the start of your turn ", 1000,
				temp, DnS.res.getAtlas("pack").findRegion("Armor1")));
		
		temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("immunepoison", 1);
		Items.put("Antivenom", new Item("Antivenom",
				"Immune to poison", 500,
				temp, DnS.res.getAtlas("pack").findRegion("Antivenom")));
		
		temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("", 1);
		Items.put("DullSword", new Item("DullSword",
				"", 100,
				temp, DnS.res.getAtlas("pack").findRegion("DullSword")));
		ArrayList<Card> cards=new ArrayList<>();
		cards.add(CardStorage.getCard("TestAttack1"));
		cards.add(CardStorage.getCard("TestAttack1"));
		cards.add(CardStorage.getCard("TestAttack1"));
		cards.add(CardStorage.getCard("TestAttack2"));
		cards.add(CardStorage.getCard("TestAttack2"));
		cards.add(CardStorage.getCard("TestStun"));
		cards.add(CardStorage.getCard("TestStun"));
		Items.get("DullSword").setCards(cards);
		Items.get("DullSword").setSlot("weapon");
		Items.get("DullSword").setClassName("player-warrior");
		temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("", 1);
		Items.put("Magic Clock", new Item("Magic Clock",
				"Control time!", 5000,
				temp, DnS.res.getAtlas("pack").findRegion("Clock")));
		cards=new ArrayList<>();
		cards.add(CardStorage.getCard("Stop Time"));
		Items.get("Magic Clock").setCards(cards);
		//Items.get("Magic Clock").setSlot("misc");
		
		temp =new ItemEffectAttributes[1];
		temp[0]=new ItemEffectAttributes("", 1);
		Items.put("Wooden Bow", new Item("Wooden Bow",
				"", 100,
				temp, DnS.res.getAtlas("pack").findRegion("WoodenBow")));
		cards=new ArrayList<>();
		cards.add(CardStorage.getCard("Quick Shot"));
		cards.add(CardStorage.getCard("Quick Shot"));
		cards.add(CardStorage.getCard("Quick Shot"));
		cards.add(CardStorage.getCard("Quick Shot"));
		cards.add(CardStorage.getCard("Steady Shot"));
		cards.add(CardStorage.getCard("Steady Shot"));
		cards.add(CardStorage.getCard("Steady Shot"));
		cards.add(CardStorage.getCard("Steady Shot"));
		
		Items.get("Wooden Bow").setClassName("player-ranger");
		Items.get("Wooden Bow").setSlot("weapon");
		Items.get("Wooden Bow").setCards(cards);
		
		Items.put("Charged Sword", new Item("Charged Sword",
				"", 500,
				temp, DnS.res.getAtlas("pack").findRegion("ChargedSword")));
		Items.get("Charged Sword").setSlot("weapon");
		Items.get("Charged Sword").setClassName("player-warrior");
		cards=new ArrayList<>();
		cards.add(CardStorage.getCard("AutomatonAttack"));
		cards.add(CardStorage.getCard("AutomatonAttack"));
		cards.add(CardStorage.getCard("AutomatonAttack"));
		cards.add(CardStorage.getCard("AutomatonAttack2"));
		Items.get("Charged Sword").setCards(cards);
		
		Items.put("Skeleton Staff", new Item("Skeleton Staff",
				"", 500,
				temp, DnS.res.getAtlas("pack").findRegion("SkullStaff")));
		Items.get("Skeleton Staff").setSlot("weapon");
		//Items.get("Skeleton Staff").setClassName("player-warrior");
		cards=new ArrayList<>();
		cards.add(CardStorage.getCard("SummonSkeletonMinion"));
		cards.add(CardStorage.getCard("SummonSkeletonMinion"));
		Items.get("Skeleton Staff").setCards(cards);
		
		Items.put("Totem Mace", new Item("Totem Mace",
				"", 500,
				temp, DnS.res.getAtlas("pack").findRegion("TotemMace")));
		Items.get("Totem Mace").setSlot("weapon");
		//Items.get("Skeleton Staff").setClassName("player-warrior");
		cards=new ArrayList<>();
		cards.add(CardStorage.getCard("TotemMaceAttack"));
		cards.add(CardStorage.getCard("TotemMaceAttack"));
		cards.add(CardStorage.getCard("TotemMaceAttack"));
		cards.add(CardStorage.getCard("SummonHealTotem"));
		cards.add(CardStorage.getCard("SummonHealTotem"));
		Items.get("Totem Mace").setCards(cards);
		
		temp =new ItemEffectAttributes[2];
		temp[0]=new ItemEffectAttributes("rangedfire", 1);
		temp[1]=new ItemEffectAttributes("changedp", -1);
		Items.put("Torch", new Item("Torch",
				"All your ranged attacks set target on fire\n"
				+ "Reduce Dark Presence by 1", 100,
				temp, DnS.res.getAtlas("pack").findRegion("Torch")));
		Items.get("Torch").setSlot("secondary");
		cards=new ArrayList<>();
		cards.add(CardStorage.getCard("TorchAttack"));
		cards.add(CardStorage.getCard("TorchAttack"));
		Items.get("Torch").setCards(cards);
		
		
	}
	public static Item getItem(String name){
		if(Items.containsKey(name)){
			return Items.get(name);
		}
		return null;
	}
	public static Item getRandomItem(){
		Random temp=new Random();
		int num=temp.nextInt(Items.size());
		Iterator<Item> it=Items.values().iterator();
		Item t =new Item();
		for(int i=0;i<num;i++){
			t=it.next();
		}
		return t;
	}
	public static Item getRandomItemNoDupesWithClass(EncounterPlayer player){
		
		
		Random temp=new Random();
		ArrayList<Item> possibleItems=new ArrayList<>();
		Iterator<Item> it=Items.values().iterator();
		Item t =new Item();
		while(it.hasNext()){
			
			t=it.next();
			if((t.getClassName()==""||t.getClassName()==player.playerName)
					&&!player.items.contains(t)){
				possibleItems.add(t);
			}
		}
		
		//Item t =new Item();
			
		
		int num=temp.nextInt(possibleItems.size());
		t=possibleItems.get(num);
		//
		//t=possibleItems.get(possibleItems.size()-1);
		//
		return t;
		
		//return Items.get("Skeleton Staff");
	}
}
