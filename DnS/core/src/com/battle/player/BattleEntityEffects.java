package com.battle.player;

public class BattleEntityEffects {

	private boolean dead;
	private boolean stunned;
	private boolean poisoned;
	private boolean bleeding;
	private boolean onfire;
	private int fireDmg;
	private int fireTurns;
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
	private boolean immuneFire;
	private boolean extraTurn;
	private boolean rangedFire;
	private int dpMod;
	
	public BattleEntityEffects() {
		//justSpawned=false;
		marked=false;
		dead=false;
		stunned=false;
		poisoned=false;
		bleeding=false;
		onfire=false;
		fireDmg=0;
		fireTurns=0;
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
		immuneFire=false;
		extraTurn=false;
		rangedFire=false;
		dpMod=0;
	}
	
	public void setBleeding(boolean bleeding) {
		if(bleeding==false){
			bleedingDmg=0;
		}
		this.bleeding = bleeding;
	}
	public void setOnFire(boolean onfire) {
		if(onfire==false){
			fireDmg=0;
		}
		this.onfire = onfire;
	}
	public void setFireDmg(int fireDmg) {
		this.fireDmg = fireDmg;
	}
	public void setFireTurns(int fireTurns) {
		this.fireTurns = fireTurns;
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
	public void setImmuneFire(boolean immuneFire) {
		this.immuneFire = immuneFire;
	}
	public boolean isImmuneFire() {
		return immuneFire;
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
	public void setExtraTurn(boolean extraTurn) {
		this.extraTurn = extraTurn;
	}
	public boolean isExtraTurn() {
		return extraTurn;
	}
	public boolean isOnFire() {
		return onfire;
	}
	public int getFireDmg() {
		return fireDmg;
	}
	public int getFireTurns() {
		return fireTurns;
	}
	public void setDpMod(int dpMod) {
		this.dpMod = dpMod;
	}
	public void setRangedFire(boolean rangedFire) {
		this.rangedFire = rangedFire;
	}
	public int getDpMod() {
		return dpMod;
	}
	public boolean isRangedFire() {
		return rangedFire;
	}
}
