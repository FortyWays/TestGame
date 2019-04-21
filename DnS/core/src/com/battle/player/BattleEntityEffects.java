package com.battle.player;

public class BattleEntityEffects {

	private boolean dead;
	private boolean stunned;
	private boolean poisoned;
	private boolean bleeding;
	private int poisonedDmg;
	private int poisonedTurns;
	private int bleedingDmg;
	private int bleedingTurns;
	//private boolean justSpawned;
	private boolean marked;
	private int markedTurns;
	private boolean meleeBlock;
	private boolean rangedBlock;
	private boolean magicBlock;
	private boolean immunePoison;
	private boolean immuneBleed;
	
	public BattleEntityEffects() {
		//justSpawned=false;
		marked=false;
		dead=false;
		stunned=false;
		poisoned=false;
		bleeding=false;
		poisonedDmg=0;
		poisonedTurns=0;
		bleedingDmg=0;
		bleedingTurns=0;
		markedTurns=0;
		meleeBlock=false;
		rangedBlock=false;
		magicBlock=false;
		immuneBleed=false;
		immunePoison=false;
	}
	
	public void setBleeding(boolean bleeding) {
		if(bleeding==false){
			bleedingDmg=0;
		}
		this.bleeding = bleeding;
	}
	public void setBleedingDmg(int bleedingDmg) {
		this.bleedingDmg = bleedingDmg;
	}
	public void setBleedingTurns(int bleedingTurns) {
		this.bleedingTurns = bleedingTurns;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public void setMagicBlock(boolean magicBlock) {
		this.magicBlock = magicBlock;
	}
	public void setMeleeBlock(boolean meleeBlock) {
		this.meleeBlock = meleeBlock;
	}
	public void setPoisoned(boolean poisoned) {
		if(poisoned==false){
			poisonedDmg=0;
		}
		this.poisoned = poisoned;
	}
	public void setPoisonedDmg(int poisonedDmg) {
		this.poisonedDmg = poisonedDmg;
	}
	public void setPoisonedTurns(int poisonedTurns) {
		this.poisonedTurns = poisonedTurns;
	}
	public void setRangedBlock(boolean rangedBlock) {
		this.rangedBlock = rangedBlock;
	}
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	public void setImmuneBleed(boolean immuneBleed) {
		this.immuneBleed = immuneBleed;
	}
	public void setImmunePoison(boolean immunePoison) {
		this.immunePoison = immunePoison;
	}
	public boolean isImmuneBleed() {
		return immuneBleed;
	}
	public boolean isImmunePoison() {
		return immunePoison;
	}
	public int getBleedingDmg() {
		return bleedingDmg;
	}
	public int getBleedingTurns() {
		return bleedingTurns;
	}
	public int getPoisonedDmg() {
		return poisonedDmg;
	}
	public int getPoisonedTurns() {
		return poisonedTurns;
	}
	public boolean isBleeding() {
		return bleeding;
	}
	public boolean isDead() {
		return dead;
	}
	public boolean isMagicBlock() {
		return magicBlock;
	}
	public boolean isMeleeBlock() {
		return meleeBlock;
	}
	public boolean isPoisoned() {
		return poisoned;
	}
	public boolean isRangedBlock() {
		return rangedBlock;
	}
	public boolean isStunned() {
		return stunned;
	}
	public boolean isMarked() {
		return marked;
	}
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	public void setMarkedTurns(int markedTurns) {
		this.markedTurns = markedTurns;
	}
	public int getMarkedTurns() {
		return markedTurns;
	}
}
