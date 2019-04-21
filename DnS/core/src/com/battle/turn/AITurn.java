package com.battle.turn;

import java.util.ArrayList;

import com.battle.graphics.BattleGUI;
import com.battle.player.BattleEnemy;
import com.battle.player.BattleEntity;
import com.battle.player.BattlePlayer;

public class AITurn extends Turn{

	public AITurn(int number) {
		super(number);
	}

	@Override
	public Turn EndTurn(ArrayList<BattleEntity> enties,BattleGUI gui) {
		gui.switchingTurnSigns=true;
		PlayerTurn newTurn=new PlayerTurn(this.number+1);
		for(BattleEntity ent: enties){
			if(ent.getClass()==BattlePlayer.class){
				if(!ent.isDead())
				ent.regen();
			}
			else{
				if(ent.getStunned()){
					ent.setStunned(false);
				}
			}
		}
		return newTurn.StartTurn(enties,gui);
	}

	@Override
	public Turn StartTurn(ArrayList<BattleEntity> enties,BattleGUI gui) {
		for(BattleEntity ent: enties){
			
			if(ent.getClass()==BattleEnemy.class){
				ent.DrawRandomCards(1,false);
				if(!ent.isDead()){
					ent.applyBleed();
					ent.applyPoison();
					ent.applyMarked();
					}
			}
		}
		checkIfGameFinished(enties);
		return this;
	}

}
