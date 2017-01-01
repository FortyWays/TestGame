package com.battle.player;

import java.util.ArrayList;

import com.battle.card.Card;
import com.battle.card.PlayerCard;

public abstract class BattleEntity {
	protected int hp;//health
	protected int sp;//stamina
	protected int mp;//mana
	protected int maxhp;
	protected int maxsp;
	protected int maxmp;
	protected boolean dead;
	protected ArrayList<Card> deck;
	protected BattleEntity(int maxhp,int maxsp,int maxmp,ArrayList<Card> deck){
		this.maxhp=maxhp;
		this.maxsp=maxsp;
		this.maxmp=maxmp;
		this.hp=this.maxhp;
		this.sp=this.maxsp;
		this.mp=this.maxmp;
		this.deck=deck;
		dead=false;
	}
	public void ChangeHP(int amount){
		if(hp+amount>=maxhp){
			hp=maxhp;
		}
		else if(hp+amount<=0){
			hp=0;
		}
		else{
			hp+=amount;
		}	
	}
	public void ChangeSP(int amount){
		if(sp+amount>=maxsp){
			sp=maxsp;
		}
		else if(sp+amount<=0){
			sp=0;
		}
		else{
			sp+=amount;
		}	
	}
	public void ChangeMP(int amount){
		if(mp+amount>=maxmp){
			mp=maxmp;
		}
		else if(mp+amount<=0){
			mp=0;
		}
		else{
			mp+=amount;
		}	
	}
	public int getHp() {
		return hp;
	}
	public int getMp() {
		return mp;
	}
	public int getSp() {
		return sp;
	}
}
