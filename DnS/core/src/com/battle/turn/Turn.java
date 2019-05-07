
package com.battle.turn;

import java.util.ArrayList;

import com.battle.graphics.BattleGUI;
import com.battle.player.BattleEntity;
import com.battle.player.BattleMinion;
import com.battle.player.BattlePlayer;

public abstract class Turn {
	
	public boolean gameFinished=false;
	public boolean playerWon=false;
	public int number;
	public Turn(int number){
		this.number=number;
	}
	public abstract Turn EndTurn(ArrayList<BattleEntity> enties,BattleGUI gui);
	public abstract Turn StartTurn(ArrayList<BattleEntity> enties,BattleGUI gui);
	public void checkIfGameFinished(ArrayList<BattleEntity> enties){
		for(BattleEntity ent:enties){
			if(ent.getClass()==BattlePlayer.class){
				if(ent.isDead()){
					gameFinished=true;
					playerWon=false;
					return;
				}
			}
			else{
				if(!ent.isDead()&&ent.getClass()!=BattleMinion.class){
					gameFinished=false;
					return;
				}
			}
		}
		gameFinished=true;
		playerWon=true;
	}
}
