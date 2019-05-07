package com.battle.player;

public class PlayerItemEffects {
	private boolean resetHP;
	private boolean resetSP;
	private boolean resetMP;
	
	private boolean rangedFire=false;
	private int dpMod=0;
	public PlayerItemEffects() {
		resetHP=false;
		resetMP=false;
		resetSP=false;
	}
	public boolean isResetHP() {
		return resetHP;
	}
	public boolean isResetMP() {
		return resetMP;
	}
	public boolean isResetSP() {
		return resetSP;
	}
	public void setResetHP(boolean resetHP) {
		this.resetHP = resetHP;
	}
	public void setResetMP(boolean resetMP) {
		this.resetMP = resetMP;
	}
	public void setResetSP(boolean resetSP) {
		this.resetSP = resetSP;
	}
	public boolean isRangedFire() {
		return rangedFire;
	}
	public int getDpMod() {
		return dpMod;
	}
	public void setDpMod(int dpMod) {
		this.dpMod = dpMod;
	}
	public void setRangedFire(boolean rangedFire) {
		this.rangedFire = rangedFire;
	}
}
