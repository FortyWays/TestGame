package com.fortyways.storages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.battle.card.Card;
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
	public static Item getRandomItemNoDupesWithClass(StagePlayer player){
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
		return t;
	}
}
