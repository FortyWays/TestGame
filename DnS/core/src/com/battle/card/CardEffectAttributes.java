package com.battle.card;

public class CardEffectAttributes {
	public int amount;
	public String effectid;
	public String dmgtype;
	public String needsTarget="none";
	public Card card;
	public int turns;
	public String entityName;
	public CardEffectAttributes(String effectid,int amount){
		this.effectid=effectid;
		this.amount=amount;
	}

	public void setDmgtype(String dmgtype) {
		this.dmgtype = dmgtype;
	}
	
}
