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
		
		boolean playerExtraTurn=false;
		for(BattleEntity ent: enties){
			if(ent.getClass()==BattlePlayer.class){
				playerExtraTurn=ent.isExtraTurn();
			}
		}
		if(playerExtraTurn){

			
			PlayerTurn newTurn =new PlayerTurn(this.number);
			for(BattleEntity ent: enties){
				if(ent.getClass()==BattlePlayer.class){
					if(!ent.isDead())
						ent.regen();
					ent.setExtraTurn(false);
				}
			}
			return newTurn.StartTurn(enties,gui);
		}
		else{
		gui.switchingTurnSigns=true;
		AITurn newTurn =new AITurn(this.number);
		for(BattleEntity ent: enties){
			if(ent.getClass()!=BattlePlayer.class){
				if(!ent.isDead())
				ent.regen();
			}
		}
		return newTurn.StartTurn(enties,gui);
		}
	}

	@Override
	public Turn StartTurn(ArrayList<BattleEntity> enties, BattleGUI gui) {
		
		for(BattleEntity ent: enties){
			
			if(ent.getClass()==BattlePlayer.class){
				BattlePlayer player=(BattlePlayer) ent;
				player.DrawRandomCards(1,false);
				gui.playerCards.checkForNewCards(player);
				player.applyBleed();
				player.applyPoison();
				player.applyMarked();
				player.applyFire();
				if(player.pie.isResetHP()){
					player.ChangeHP(player.getMaxhp());
				}
				if(player.pie.isResetSP()){
					player.ChangeHP(player.getMaxsp());
				}
				if(player.pie.isResetMP()){
					player.ChangeHP(player.getMaxmp());
				}
				break;
			}
		}
		checkIfGameFinished(enties);
		return this;
	}
	

}
