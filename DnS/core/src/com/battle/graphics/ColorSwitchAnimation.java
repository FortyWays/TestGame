package com.battle.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.player.BattleEntity;

public class ColorSwitchAnimation {
	private float buff;
	private float startBuff;
	private boolean increasing;
	private boolean oneTime;
	private boolean isOn=false;
	public ColorSwitchAnimation(boolean oneTime,boolean increasing) {
		this.oneTime=oneTime;
		this.increasing=increasing;
		if(increasing){
			buff=0;
		}
		else{
			buff=1;
		}
		startBuff=buff;
	}
	public void setColor(SpriteBatch spriteBatch){
		if(isOn)
		spriteBatch.setColor(1, buff, buff, 1);
	}
	public void setColorBack(SpriteBatch spriteBatch){
		if(isOn)
		spriteBatch.setColor(1, 1, 1, 1);
	}
	public void update(float dt){
		if(isOn){
		if(buff>0.2 && !increasing){
			buff-=0.07f;
		}
		else if(buff<1 && increasing){
			buff+=0.07f;
		}
		else if(buff<=0.2||buff>=1){
			if(oneTime && Math.abs(buff-startBuff)<=0.07f){
				isOn=false;
			}
			increasing=!increasing;
		}
		}
	}
	public void Play(){
		isOn=true;
	}
	
	
}
