package com.battle.turn;

import java.util.ArrayList;

import com.battle.graphics.BattleGUI;
import com.battle.player.BattleEnemy;
import com.battle.player.BattleEntity;
import com.battle.player.BattlePlayer;


public class PlayerTurn extends Turn{

	public PlayerTurn(int number) {
		super(number);
	}

	@Override
	public Turn EndTurn(ArrayList<BattleEntity> enties,BattleGUI gui) {
		gui.switchingTurnSigns=true;
		AITurn newTurn =new AITurn(this.number);
		for(BattleEntity ent: enties){
			if(ent.getClass()==BattleEnemy.class){
				if(!ent.isDead())
				ent.regen();
			}
		}
		return newTurn.StartTurn(enties,gui);
	}

	@Override
	public Turn StartTurn(ArrayList<BattleEntity> enties, BattleGUI gui) {
		checkIfGameFinished(enties);
		for(BattleEntity ent: enties){
			
			if(ent.getClass()==BattlePlayer.class){
				BattlePlayer player=(BattlePlayer) ent;
				player.DrawRandomCards(1,false);
				gui.playerCards.checkForNewCards(player);
				player.applyBleed();
				player.applyPoison();
				player.applyMarked();
				break;
			}
		}
		return this;
	}
	

}
