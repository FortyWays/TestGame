package com.encounter;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;
import com.battle.graphics.Animation;
import com.battle.graphics.CustomFont;
import com.battle.graphics.StatBar;
import com.battle.player.PlayerItemEffects;
import com.fortyways.dns.DnS;
import com.fortyways.storages.ItemStorage;
import com.fortyways.storages.SpriteStorage;
import com.stage.items.Item;

public class EncounterPlayer {
	
public String stageName;
	
	
	protected Animation idleAnim;
	public String playerName;
	
	protected int cardsPerTurn;
	public ArrayList<Item> items=new ArrayList<>();
	public ArrayList<Item> equipped=new ArrayList<>();
	public ArrayList<Card> extraCards=new ArrayList<>();
	protected boolean immuneBleed=false;
	protected boolean immunePoison=false;
	public PlayerItemEffects pie;
	
	public int prevmaxhp;
	public int prevmaxsp;
	public int prevmaxmp;
	
	public Encounter curEncounter;
	
	protected int hp;//health
	protected int sp;//stamina
	protected int mp;//mana
	protected int food;
	protected int money;
	protected int fame;
	protected int dp;//darkpresence
	
	protected int hpregen;
	protected int spregen;
	protected int mpregen;
	
	protected int maxhp;
	protected int maxsp;
	protected int maxmp;
	
	protected StatBar hpbar;
	protected StatBar spbar;
	protected StatBar mpbar;

	protected boolean meleeBlock=false;
	protected boolean rangedBlock=false;
	protected boolean magicBlock=false;
	
	private CustomFont foodDisplay;
	private CustomFont moneyDisplay;
	private CustomFont fameDisplay;
	
	public EncounterPlayer(String playerName) {
		this.stageName=stageName;
		cardsPerTurn=2;
		pie=new PlayerItemEffects();
		this.playerName=playerName;
		food=5;
		money=0;
		fame=0;
		idleAnim=new Animation(DnS.WIDTH/2, DnS.HEIGHT/2,
				SpriteStorage.getFrameProportions(playerName)[0]*2,
				SpriteStorage.getFrameProportions(playerName)[1]*2,
				SpriteStorage.getIdleAnimationSpriteSheet(playerName)
				,20, SpriteStorage.getFrameProportions(playerName)[0]
				,SpriteStorage.getFrameProportions(playerName)[1]);
		if(playerName=="player-ranger"){
			
			setMaxStats(30, 30, 30);
			prevmaxhp=maxhp;
			prevmaxsp=maxsp;
			prevmaxmp=maxmp;
			setStats(30, 30, 30);
			setRegens(5, 5, 5);
			ItemStorage.getItem("Wooden Bow").Equip(this);
		}
		else if(playerName=="player-warrior"){
			setMaxStats(50, 40, 30);
			prevmaxhp=maxhp;
			prevmaxsp=maxsp;
			prevmaxmp=maxmp;
			setStats(40, 40, 30);
			setRegens(5, 5, 1);
			equipItem(ItemStorage.getItem("DullSword"));
			equipItem(ItemStorage.getItem("Antivenom"));
		
		}
		hpbar=new StatBar(60, DnS.HEIGHT-15, maxhp, "hp2");
		mpbar=new StatBar(60+100, DnS.HEIGHT-15, maxmp, "mp2");
		spbar=new StatBar(60+200, DnS.HEIGHT-15, maxsp, "sp2");
		updateDisplays();
	}

	public EncounterPlayer() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMaxStats(int maxhp,int maxsp,int maxmp){
		this.maxhp=maxhp;
		//hp=maxhp;
		this.maxsp=maxsp;
		//sp=maxsp;
		this.maxmp=maxmp;
		hpbar=new StatBar(60, DnS.HEIGHT-15, maxhp, "hp2");
		mpbar=new StatBar(60+100, DnS.HEIGHT-15, maxmp, "mp2");
		spbar=new StatBar(60+200, DnS.HEIGHT-15, maxsp, "sp2");
		//mp=maxmp;
	}
	BitmapFont font =new BitmapFont();



	public void render(SpriteBatch spriteBatch){
		foodDisplay.render(spriteBatch);
		moneyDisplay.render(spriteBatch);
		fameDisplay.render(spriteBatch);
		hpbar.render(spriteBatch);
		spbar.render(spriteBatch);
		mpbar.render(spriteBatch);
		/*for(int i=0;i<equipped.size();i++){
			Item item=equipped.get(i);
		//	item.x=20+50*i;
		//	item.y=20;
			//System.out.println(item.getName()+" "+item.x);
			item.render(spriteBatch);
		}*/
	}
	public void setStats(int hp,int sp,int mp){
		this.hp=hp;
		hpbar.UpdateBar(hp);
		this.sp=sp;
		spbar.UpdateBar(sp);
		this.mp=mp;
		mpbar.UpdateBar(mp);
	}
	public void equipItem(Item item){
		
		item.x=20+40*equipped.size();
		item.setRelativeX(20+40*equipped.size());
		item.y=20;
		
		item.Equip(this);
		
	}
	public void setRegens(int hpregen,int spregen,int mpregen){
		this.hpregen=hpregen;
		this.spregen=spregen;
		this.mpregen=mpregen;
	}
	public int getMaxhp() {
		return maxhp;
	}
	public int getMaxmp() {
		return maxmp;
	}
	public int getMaxsp() {
		return maxsp;
	}
	public int getHp() {
		return hp;
	}
	public int getHpregen() {
		return hpregen;
	}
	public int getMp() {
		return mp;
	}
	public int getMpregen() {
		return mpregen;
	}
	public int getSp() {
		return sp;
	}
	public int getSpregen() {
		return spregen;
	}

	public void setHp(int hp) {
		if(hp>maxhp){
			this.hp=maxhp;
		}
		else if(hp<0){
			this.hp=0;
		}
		else{
		this.hp = hp;}
		hpbar.UpdateBar(this.hp);
	}
	public void setMp(int mp) {
		if(mp>maxmp){
			this.mp=maxmp;
		}
		else if(mp<0){
			this.mp=0;
		}
		else{
		this.mp = mp;}
		mpbar.UpdateBar(this.mp);
	}
	public void setSp(int sp) {
		if(sp>maxsp){
			this.sp=maxsp;
		}
		else if(sp<0){
			this.sp=0;
		}
		else{
		this.sp = sp;}
		spbar.UpdateBar(this.sp);
	}
	public void setCardsPerTurn(int cardsPerTurn) {
		this.cardsPerTurn = cardsPerTurn;
	}
	public int getFame() {
		return fame;
	}
	public int getFood() {
		return food;
	}
	public int getMoney() {
		return money;
	}
	public void setFood(int food) {
		this.food = food;
		if(this.food <0){
			this.food =0;
		}
	}
	public void setFame(int fame) {
		this.fame = fame;
		if(this.fame<0){
			this.fame=0;
		}
	}
	public void setMoney(int money) {
		this.money = money;
		if(this.money<0){
			this.money=0;
		}
	}
	public int getCardsPerTurn() {
		return cardsPerTurn;
	}
	public boolean isImmuneBleed() {
		return immuneBleed;
	}
	public boolean isImmunePoison() {
		return immunePoison;
	}
	public void setImmuneBleed(boolean immuneBleed) {
		this.immuneBleed = immuneBleed;
	}
	public void setImmunePoison(boolean immunePoison) {
		this.immunePoison = immunePoison;
	}
	public void setMagicBlock(boolean magicBlock) {
		this.magicBlock = magicBlock;
	}
	public void setRangedBlock(boolean rangedBlock) {
		this.rangedBlock = rangedBlock;
	}
	public void setMeleeBlock(boolean meleeBlock) {
		this.meleeBlock = meleeBlock;
	}
	public boolean isMeleeBlock() {
		return meleeBlock;
	}
	public boolean isMagicBlock() {
		return magicBlock;
	}
	public boolean isRangedBlock() {
		return rangedBlock;
	}
	public int getDp() {
		return dp;
	}
	public void setDp(int dp) {
		this.dp = dp;
		if(this.dp<0){
			this.dp=0;
		}
	}
	public void updateDisplays(){
		foodDisplay=new CustomFont(Integer.toString(food),DnS.WIDTH/2-60, DnS.HEIGHT-15, "green");
		moneyDisplay=new CustomFont(Integer.toString(money), DnS.WIDTH/2-10, DnS.HEIGHT-15, "yellow");
		fameDisplay=new CustomFont(Integer.toString(fame), DnS.WIDTH/2+40, DnS.HEIGHT-15, "purple");
	}
	public void addCard(Card card){
		extraCards.add(card);
	}
	public void addCard(ArrayList<Card> cards){
		for(Card card:cards)
		extraCards.add(card);
	}
}
