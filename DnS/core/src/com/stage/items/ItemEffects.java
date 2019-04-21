package com.stage.items;

import com.stage.player.StagePlayer;

public class ItemEffects {
	public static void ApplyEffect(ItemEffectAttributes[] iea,StagePlayer player){
		for(int i=0;i<iea.length;i++){
			if(iea[i].effectid=="addmaxhp"){
				player.setMaxStats(player.getMaxhp()+iea[i].amount,
						player.getMaxsp(), player.getMaxmp());
				player.setStats(player.getHp()+iea[i].amount,
						player.getSp(), player.getMp());
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
	public static void RemoveEffect(ItemEffectAttributes[] iea,StagePlayer player){
		for(int i=0;i<iea.length;i++){
			if(iea[i].effectid=="addmaxhp"){
				if(player.getHp()>player.getMaxhp()-iea[i].amount){
				player.setStats(player.getMaxhp()-iea[i].amount,
						player.getSp(), player.getMp());}
				player.setMaxStats(player.getMaxhp()-iea[i].amount,
						player.getMaxsp(), player.getMaxmp());
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
