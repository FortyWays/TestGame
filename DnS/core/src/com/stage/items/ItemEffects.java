package com.stage.items;

import com.encounter.EncounterPlayer;
import com.stage.player.StagePlayer;

public class ItemEffects {
	public static void ApplyEffect(ItemEffectAttributes[] iea,EncounterPlayer player){
		for(int i=0;i<iea.length;i++){
			
			if(iea[i].effectid=="addmaxhp"){
				player.setMaxStats(player.getMaxhp()+iea[i].amount,
						player.getMaxsp(), player.getMaxmp());
				player.prevmaxhp=player.getMaxhp();
				player.setStats(player.getHp()+iea[i].amount,
						player.getSp(), player.getMp());
			}
			if(iea[i].effectid=="addmaxsp"){
				player.setMaxStats(player.getMaxhp(),
						player.getMaxsp()+iea[i].amount, player.getMaxmp());
				player.prevmaxsp=player.getMaxsp();
				player.setStats(player.getHp(),
						player.getSp()+iea[i].amount, player.getMp());
			}
			if(iea[i].effectid=="addmaxmp"){
				player.setMaxStats(player.getMaxhp(),
						player.getMaxsp(), player.getMaxmp()+iea[i].amount);
				player.prevmaxmp=player.getMaxhp();
				player.setStats(player.getHp(),
						player.getSp(), player.getMp()+iea[i].amount);
			}
			else if(iea[i].effectid=="setmaxhp"){
				player.setMaxStats(iea[i].amount,
						player.getMaxsp(), player.getMaxmp());
				if(player.getHp()<iea[i].amount){
					player.setStats(player.getHp(),
							player.getSp(), player.getMp());
				}
				else
				player.setStats(iea[i].amount,
						player.getSp(), player.getMp());
			}
			else if(iea[i].effectid=="resethp"){
				player.pie.setResetHP(true);
			}
			else if(iea[i].effectid=="rangedfire"){
				player.pie.setRangedFire(true);
			}
			else if(iea[i].effectid=="changedp"){
				player.pie.setDpMod(player.pie.getDpMod()+iea[i].amount);
			}
			else if(iea[i].effectid=="addcardsperturn"){
				player.setCardsPerTurn(player.getCardsPerTurn()+iea[i].amount);
			}
			else if(iea[i].effectid=="immunepoison"){
				player.setImmunePoison(true);
			}
			else if(iea[i].effectid=="immunebleed"){
				player.setImmuneBleed(true);
			}
			else if(iea[i].effectid=="meleeblock"){
				player.setMeleeBlock(true);
			}
			else if(iea[i].effectid=="rangedblock"){
				player.setRangedBlock(true);
			}
			else if(iea[i].effectid=="magicblock"){
				player.setMagicBlock(true);
			}
			
		}
		
		
		
	}
	public static void RemoveEffect(ItemEffectAttributes[] iea,EncounterPlayer player){
		for(int i=0;i<iea.length;i++){
			if(iea[i].effectid=="addmaxhp"){
				if(player.getHp()>player.getMaxhp()-iea[i].amount){
				player.setStats(player.getMaxhp()-iea[i].amount,
						player.getSp(), player.getMp());}
				player.setMaxStats(player.getMaxhp()-iea[i].amount,
						player.getMaxsp(), player.getMaxmp());
			}
			else if(iea[i].effectid=="addmaxsp"){
				if(player.getSp()>player.getMaxsp()-iea[i].amount){
				player.setStats(player.getHp(),
						player.getMaxsp()-iea[i].amount, player.getMp());}
				player.setMaxStats(player.getMaxsp(),
						player.getMaxsp()-iea[i].amount, player.getMaxmp());
			}
			else if(iea[i].effectid=="addmaxmp"){
				if(player.getMp()>player.getMaxmp()-iea[i].amount){
				player.setStats(player.getHp(),
						player.getMaxsp(), player.getMaxmp()-iea[i].amount);}
				player.setMaxStats(player.getMaxsp(),
						player.getMaxsp(),player.getMaxmp()-iea[i].amount);
			}
			else if(iea[i].effectid=="setmaxhp"){
				player.setMaxStats(player.prevmaxhp,
						player.getMaxsp(), player.getMaxmp());
				player.setStats(iea[i].amount,
						player.getSp(), player.getMp());
			}
			else if(iea[i].effectid=="resethp"){
				player.pie.setResetHP(false);
			}
			else if(iea[i].effectid=="rangedfire"){
				player.pie.setRangedFire(false);
			}
			else if(iea[i].effectid=="changedp"){
				player.pie.setDpMod(player.pie.getDpMod()-iea[i].amount);
			}
			else if(iea[i].effectid=="addcardsperturn"){
				player.setCardsPerTurn(player.getCardsPerTurn()-iea[i].amount);
			}
			else if(iea[i].effectid=="immunepoison"){
				player.setImmunePoison(false);
			}
			else if(iea[i].effectid=="immunebleed"){
				player.setImmuneBleed(false);
			}
			else if(iea[i].effectid=="meleeblock"){
				player.setMeleeBlock(false);
			}
			else if(iea[i].effectid=="rangedblock"){
				player.setRangedBlock(false);
			}
			else if(iea[i].effectid=="magicblock"){
				player.setMagicBlock(false);
			}
			
		}
	}
}
