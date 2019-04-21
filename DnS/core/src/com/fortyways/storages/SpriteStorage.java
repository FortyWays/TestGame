package com.fortyways.storages;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.player.BattleEntity;
import com.fortyways.dns.DnS;

public class SpriteStorage {

	private static HashMap<String, int[]> frameWidthHeights;//int[0]=witdth/int[1]=height;
	private static HashMap<String, TextureRegion> idleAnimation;
	private static HashMap<String, TextureRegion> deathAnimation;
	private static HashMap<String, TextureRegion> attackAnimation;
	private static HashMap<String, TextureRegion> deadAnimation;
	
	public static void init(){
		frameWidthHeights=new HashMap<>();
		int[] temp=new int[2];
		temp[0]=23;temp[1]=20;
		frameWidthHeights.put("goblin-berserk", temp);
		temp=new int[2];
		temp[0]=23;temp[1]=22;
		frameWidthHeights.put("goblin-archer", temp);
		temp=new int[2];
		temp[0]=17;temp[1]=20;	
		frameWidthHeights.put("player-warrior", temp);
		temp=new int[2];
		temp[0]=20;temp[1]=22;	
		frameWidthHeights.put("player-ranger", temp);
		temp=new int[2];
		temp[0]=20;temp[1]=20;	
		frameWidthHeights.put("cultist", temp);
		temp=new int[2];
		temp[0]=20;temp[1]=20;	
		frameWidthHeights.put("slimeboy", temp);
		temp=new int[2];
		temp[0]=20;temp[1]=22;	
		frameWidthHeights.put("automaton", temp);
		idleAnimation=new HashMap<>();
		idleAnimation.put("goblin-berserk", DnS.res.getAtlas("pack").findRegion("EnemySprite2"));
		idleAnimation.put("player-warrior", DnS.res.getAtlas("pack").findRegion("PlayerSprite2"));
		idleAnimation.put("goblin-archer", DnS.res.getAtlas("pack").findRegion("EnemySprite3"));
		idleAnimation.put("player-ranger", DnS.res.getAtlas("pack").findRegion("RangerIdle"));
		idleAnimation.put("cultist", DnS.res.getAtlas("pack").findRegion("CultistIdle"));
		idleAnimation.put("slimeboy", DnS.res.getAtlas("pack").findRegion("SlimeBoyIdle"));
		idleAnimation.put("automaton", DnS.res.getAtlas("pack").findRegion("AutomatonIdle"));
		deathAnimation=new HashMap<>();
		deathAnimation.put("player-warrior", DnS.res.getAtlas("pack").findRegion("DeathAnimation"));
		
		attackAnimation=new HashMap<>();
		attackAnimation.put("goblin-archer", DnS.res.getAtlas("pack").findRegion("ShootAnimationpng"));
		
		deadAnimation=new HashMap<>();
		deadAnimation.put("player-warrior", DnS.res.getAtlas("pack").findRegion("DeadPlayer"));
		deadAnimation.put("goblin-berserk", DnS.res.getAtlas("pack").findRegion("EnemyDead"));
		deadAnimation.put("goblin-archer", DnS.res.getAtlas("pack").findRegion("EnemyArcherDead"));
		deadAnimation.put("slimeboy", DnS.res.getAtlas("pack").findRegion("SlimeBoyDead"));
		deadAnimation.put("automaton", DnS.res.getAtlas("pack").findRegion("AutomatonDead"));
	}
	public static int[] getFrameProportions(String name){
		if(frameWidthHeights.containsKey(name)){
			return frameWidthHeights.get(name);
		}
		return null;
	}
	public static TextureRegion getIdleAnimationSpriteSheet(String name){
		if(idleAnimation.containsKey(name)){
			return idleAnimation.get(name);
		}
		return null;
		
	}
	public static void getDeathAnimation(BattleEntity ent){
		if(deathAnimation.containsKey(ent.entityName)){
			ent.getIdleAnim().switchAnimation(
					deathAnimation.get(ent.entityName), 
					frameWidthHeights.get(ent.entityName)[0], frameWidthHeights.get(ent.entityName)[1]
							, true, 20);
		}
	}
	public static void getAttackAnimation(BattleEntity ent){
		if(attackAnimation.containsKey(ent.entityName)){
			ent.getIdleAnim().switchAnimation(
					attackAnimation.get(ent.entityName), 
					frameWidthHeights.get(ent.entityName)[0], frameWidthHeights.get(ent.entityName)[1]
							, true, 20);
		}
	}
	public static void getDeadAnimation(BattleEntity ent){
		if(deadAnimation.containsKey(ent.entityName)){
			ent.getIdleAnim().switchAnimation(
					deadAnimation.get(ent.entityName), 
					frameWidthHeights.get(ent.entityName)[0], frameWidthHeights.get(ent.entityName)[1]
							, false, 20);
		}
	}
	
	
}
