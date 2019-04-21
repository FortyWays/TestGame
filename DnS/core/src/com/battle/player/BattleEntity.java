package com.battle.player;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.card.Card;
import com.battle.graphics.Animation;
import com.battle.graphics.ColorSwitchAnimation;
import com.battle.graphics.FadingAnimation;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.util.Graphic;


public abstract class BattleEntity {
	protected int hp;//health
	protected int sp;//stamina
	protected int mp;//mana
	
	protected int hpregen;
	protected int spregen;
	protected int mpregen;
	
	protected int maxhp;
	protected int maxsp;
	protected int maxmp;
	
	public String entityName;
	protected TextureRegion sprite=null;
	protected Animation idleAnim=null;
	
	public boolean drewThisTurn=false;
	
	public BattleEntityEffects effects;
	protected AdditionalAnimationHandler animHandler;
	
	public int cardsPerTurn=2;
	private int maxCardsInHand=7;
	public ArrayList<Card> cardsInHand;
	protected ArrayList<Card> deck;
	
	
	
	protected BattleEntity(int maxhp,int maxsp,int maxmp, 
			int hpregen,int spregen,int mpregen,ArrayList<Card> deck){
		this.maxhp=maxhp;
		this.maxsp=maxsp;
		this.maxmp=maxmp;
		this.hpregen=hpregen;
		this.spregen=spregen;
		this.mpregen=mpregen;
		this.hp=this.maxhp;
		this.sp=this.maxsp;
		this.mp=this.maxmp;
		this.deck=deck;
		effects=new BattleEntityEffects();
		animHandler=new AdditionalAnimationHandler(this);
	}
	

	
	public void ChangeHP(int amount){
		if(hp+amount>=maxhp){
			hp=maxhp;
		}
		else if(hp+amount<=0){
			hp=0;
			
			if(effects.isDead()!=true){
			SpriteStorage.getDeadAnimation(this);
			effects.setDead(true);
			}
			
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
	
	public void regen(){
		ChangeHP(hpregen);
		ChangeSP(spregen);
		ChangeMP(mpregen);
	}
	
	public void update(float dt){
		animHandler.update(dt);
		getIdleAnim().update(dt);
	}
	public void render(SpriteBatch spriteBatch){
		animHandler.render(spriteBatch);
		animHandler.setColorForAnimation(spriteBatch);
		getIdleAnim().render(spriteBatch);
		animHandler.setColorBack(spriteBatch);
	}
	
	public boolean Touched(float curx,float cury){
		return curx>getIdleAnim().getX()-getIdleAnim().getWidth()/2 && 
				cury>getIdleAnim().getY()-getIdleAnim().getHeight()/2 && 
				curx<getIdleAnim().getX()+getIdleAnim().getWidth()/2 && 
				cury<getIdleAnim().getY()+getIdleAnim().getHeight()/2;
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
	
	public void FillEmptyHand(int amount){
		cardsInHand=new ArrayList<>();
		Random r=new Random();
		while(cardsInHand.size()<amount && deck.size()>0)
		{
			int num=r.nextInt(deck.size());
			cardsInHand.add(deck.get(num));
			deck.remove(num);
		}
		
	}
	
	public void DrawRandomCards(int amount,boolean drawinFromCard){
		if(deck.size()>0){
			Random r=new Random();
			
				for(int i=0;i<amount;i++)
				{
					int num=r.nextInt(deck.size());
					if(drawinFromCard){
					if(cardsInHand.size()-1<maxCardsInHand){
					cardsInHand.add(deck.get(num));
					deck.remove(num);
					}}
					else{
						if(cardsInHand.size()<maxCardsInHand){
							cardsInHand.add(deck.get(num));
							deck.remove(num);
							}
					}
				}
				drewThisTurn=true;
		}
	}
	public void setBleeding(boolean bleeding){
		if(bleeding!=this.effects.isBleeding()){
			animHandler.setBleedingIcon(bleeding);}
		this.effects.setBleeding(bleeding);
	}
	public void setPoisoned(boolean poisoned){
		if(poisoned!=this.effects.isPoisoned()){
			animHandler.setPoisonedIcon(poisoned);}
		this.effects.setPoisoned(poisoned);
	}
	public void PlayOnHitAnimation(){
		animHandler.onHitAnimation.Play();
	}
	public void setStunned(boolean stunned) {
		if(stunned!=this.effects.isStunned()){
			animHandler.setStunnedIcon(stunned);}
		this.effects.setStunned(stunned); 
	}
	public void setMarked(boolean marked) {
		if(marked!=this.effects.isMarked()){
			animHandler.setMarkedIcon(marked);}
		this.effects.setMarked(marked); 
	}
	public boolean getStunned(){
		return this.effects.isStunned();
	}
	public int cardsLeftInDeck(){
		return deck.size();
	}
	public Animation getIdleAnim() {
		return idleAnim;
	}
	public void setIdleAnim(Animation idleAnim) {
		this.idleAnim = idleAnim;
	}
	public void addCardInDeck(Card card) {
		deck.add(card);
	}
	public boolean isDead() {
		return this.effects.isDead();
	}
	
	public boolean isBleeding() {
		return this.effects.isBleeding();
	}
	public boolean isPoisoned() {
		return this.effects.isPoisoned();
	}
	public void setRangedBlock(boolean b) {
		if(b!=this.effects.isRangedBlock()){
			animHandler.setRangedBlockIcon(b);}
		this.effects.setRangedBlock(b);
	}
	public void setMeleeBlock(boolean b) {
		if(b!=this.effects.isMeleeBlock()){
			animHandler.setMeleeBlockIcon(b);}
		this.effects.setMeleeBlock(b);
	}
	public void setMagicBlock(boolean b) {
		if(b!=this.effects.isMagicBlock()){
		animHandler.setMagicBlockIcon(b);}
		this.effects.setMagicBlock(b);
	}
	public boolean getRangedBlock() {
		return this.effects.isRangedBlock();
	}
	public boolean getMeleeBlock() {
		return this.effects.isMeleeBlock();
	}
	public boolean getMagicBlock() {
		return this.effects.isMagicBlock();
	}
	public boolean getImmuneToPoison(){
		return this.effects.isImmunePoison();
	}
	public boolean getImmuneToBleed(){
		return this.effects.isImmuneBleed();
	}
	public void setImmuneToPoison(boolean immune){
		this.effects.setImmunePoison(immune);
	}
	public void setImmuneToBleed(boolean immune){
		this.effects.setImmuneBleed(immune);
	}
	public void addMessageAnimation(String message,String color,float y){
		animHandler.anims.add(new FadingAnimation(message, color,
				this.getIdleAnim().getX(), this.getIdleAnim().getY()+y));
	}
	public void applyBleed(){
		if(isBleeding()){
		PlayOnHitAnimation();
		addMessageAnimation("bleeding "+"-"+effects.getBleedingDmg(), "red", 90);
		effects.setBleedingTurns(effects.getBleedingTurns()-1);
		ChangeHP(-effects.getBleedingDmg());
		if(effects.getBleedingTurns()==0){
			setBleeding(false);
		}
		
		}
	}
	public void applyPoison(){
		if(isPoisoned()){
		PlayOnHitAnimation();
		addMessageAnimation("poisoned "+"-"+effects.getPoisonedDmg(), "green", 90);
		effects.setPoisonedTurns(effects.getPoisonedTurns()-1);
		ChangeHP(-effects.getPoisonedDmg());
		if(effects.getPoisonedTurns()==0){
			setPoisoned(false);
		}
		
		}
	}
	public void applyMarked(){
		if(effects.isMarked()){
			effects.setMarkedTurns(effects.getMarkedTurns()-1);
			if(effects.getMarkedTurns()==0){
				setMarked(false);
			}
		}
	}

	
}
