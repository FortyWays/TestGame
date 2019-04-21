package com.stage.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.Animation;
import com.battle.graphics.StatBar;
import com.fortyways.dns.DnS;
import com.fortyways.storages.ItemStorage;
import com.fortyways.storages.SpriteStorage;
import com.stage.items.Item;

public class StagePlayer {

	public String stageName;
	
	private Animation idleAnim;
	private float worldX;
	private float worldY;
	public float speed=1;
	public String playerName;
	private int cardsPerTurn;
	public ArrayList<Item> items=new ArrayList<>();
	public ArrayList<Item> equipped=new ArrayList<>();
	private boolean immuneBleed=false;
	private boolean immunePoison=false;
	
	private int hp;//health
	private int sp;//stamina
	private int mp;//mana
	
	private int hpregen;
	private int spregen;
	private int mpregen;
	
	private int maxhp;
	private int maxsp;
	private int maxmp;
	
	private StatBar hpbar;
	private StatBar spbar;
	private StatBar mpbar;

	private boolean meleeBlock=false;
	private boolean rangedBlock=false;
	private boolean magicBlock=false;
	
	public StagePlayer() {
		worldX=100;
		worldY=100;
		speed=3f;
		idleAnim=new Animation(DnS.WIDTH/2, DnS.HEIGHT/2, 34, 40,
				DnS.res.getAtlas("pack").findRegion("PlayerSprite2"), 20, 17, 20);
	}
	public StagePlayer(String playerName,String stageName) {
		this.stageName=stageName;
		cardsPerTurn=2;
		worldX=350;
		worldY=850;
		speed=3f;
		this.playerName=playerName;
		idleAnim=new Animation(DnS.WIDTH/2, DnS.HEIGHT/2,
				SpriteStorage.getFrameProportions(playerName)[0]*2,
				SpriteStorage.getFrameProportions(playerName)[1]*2,
				SpriteStorage.getIdleAnimationSpriteSheet(playerName)
				,20, SpriteStorage.getFrameProportions(playerName)[0]
				,SpriteStorage.getFrameProportions(playerName)[1]);
		if(playerName=="player-ranger"){
			setMaxStats(30, 30, 30);
			setStats(30, 30, 30);
			setRegens(0, 5, 5);
			ItemStorage.getItem("Wooden Bow").Equip(this);
		}
		else if(playerName=="player-warrior"){
			setMaxStats(50, 40, 30);
			setStats(50, 40, 30);
			setRegens(5, 10, 1);
			ItemStorage.getItem("DullSword").Equip(this);
		}
		hpbar=new StatBar(60, 30, maxhp, "hp2");
		mpbar=new StatBar(60, 30+hpbar.height-2, maxmp, "mp2");
		spbar=new StatBar(60, 30+2*hpbar.height-4, maxsp, "sp2");
	}
	public void render(SpriteBatch spriteBatch){
		idleAnim.render(spriteBatch);
		hpbar.render(spriteBatch);
		spbar.render(spriteBatch);
		mpbar.render(spriteBatch);
	}
	public void update(float dt){
		idleAnim.update(dt);
	}
	
	public void setMaxStats(int maxhp,int maxsp,int maxmp){
		this.maxhp=maxhp;
		//hp=maxhp;
		this.maxsp=maxsp;
		//sp=maxsp;
		this.maxmp=maxmp;
		hpbar=new StatBar(60, 30, maxhp, "hp2");
		spbar=new StatBar(60, 30+hpbar.height-2, maxsp, "sp2");
		mpbar=new StatBar(60, 30+2*hpbar.height-4, maxmp, "mp2");
		//mp=maxmp;
	}
	public void setStats(int hp,int sp,int mp){
		this.hp=hp;
		hpbar.UpdateBar(hp);
		this.sp=sp;
		spbar.UpdateBar(sp);
		this.mp=mp;
		mpbar.UpdateBar(mp);
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
	public float getWorldX() {
		return worldX;
	}
	public float getWorldY() {
		return worldY;
	}
	public void setWorldX(float worldX) {
		this.worldX = worldX;
	}
	public void setWorldY(float worldY) {	
		this.worldY = worldY;
	}
	public void setHp(int hp) {
		if(hp>maxhp){
			this.hp=maxhp;
		}
		else if(hp<0){
			this.hp=0;
		}
		else
		this.hp = hp;
		hpbar.UpdateBar(hp);
	}
	public void setMp(int mp) {
		if(mp>maxmp){
			this.mp=maxmp;
		}
		else if(mp<0){
			this.mp=0;
		}
		else
		this.mp = mp;
		mpbar.UpdateBar(mp);
	}
	public void setSp(int sp) {
		if(sp>maxsp){
			this.sp=maxsp;
		}
		else if(sp<0){
			this.sp=0;
		}
		else
		this.sp = sp;
		spbar.UpdateBar(sp);
	}
	public void setCardsPerTurn(int cardsPerTurn) {
		this.cardsPerTurn = cardsPerTurn;
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
}
