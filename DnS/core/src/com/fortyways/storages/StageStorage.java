package com.fortyways.storages;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;

public class StageStorage {

	private static HashMap<String, HashMap<String,TextureRegion>> stageSprites;
	private static  HashMap<String,TextureRegion> backgrounds;
	
	public static void init(){
		
		stageSprites=new HashMap<>();
		backgrounds=new HashMap<>();
		backgrounds.put("desert", DnS.res.getAtlas("pack").findRegion("Background1"));
		backgrounds.put("castle", DnS.res.getAtlas("pack").findRegion("Background"));
		HashMap<String, TextureRegion> temp=new HashMap<>();
	
		temp.put("WallLeft", DnS.res.getAtlas("pack").findRegion("Wall3"));
		temp.put("WallRight", DnS.res.getAtlas("pack").findRegion("Wall4"));
		temp.put("WallUp", DnS.res.getAtlas("pack").findRegion("Wall2"));
		temp.put("WallDown", DnS.res.getAtlas("pack").findRegion("Wall5"));
		temp.put("CornerLD", DnS.res.getAtlas("pack").findRegion("CornerLD"));
		temp.put("CornerLU", DnS.res.getAtlas("pack").findRegion("CornerLU"));
		temp.put("CornerRD", DnS.res.getAtlas("pack").findRegion("CornerRD"));
		temp.put("CornerRU", DnS.res.getAtlas("pack").findRegion("CornerRU"));
		temp.put("RCornerLD", DnS.res.getAtlas("pack").findRegion("ReverseCornerLD"));
		temp.put("RCornerLU", DnS.res.getAtlas("pack").findRegion("ReverseCornerLU"));
		temp.put("RCornerRD", DnS.res.getAtlas("pack").findRegion("ReverseCornerRD"));
		temp.put("RCornerRU", DnS.res.getAtlas("pack").findRegion("ReverseCornerRU"));
		temp.put("Floor", DnS.res.getAtlas("pack").findRegion("Desert1"));
		stageSprites.put("desert", temp);
		temp=new HashMap<>();
		temp.put("WallLeft", DnS.res.getAtlas("pack").findRegion("CastleWallLeft"));
		temp.put("WallRight", DnS.res.getAtlas("pack").findRegion("CastleWallRight"));
		temp.put("WallUp", DnS.res.getAtlas("pack").findRegion("CastleWallUp"));
		temp.put("WallDown", DnS.res.getAtlas("pack").findRegion("CastleWallDown"));
		temp.put("CornerLD", DnS.res.getAtlas("pack").findRegion("CastleCornerLD"));
		temp.put("CornerLU", DnS.res.getAtlas("pack").findRegion("CastleCornerLU"));
		temp.put("CornerRD", DnS.res.getAtlas("pack").findRegion("CastleCornerRD"));
		temp.put("CornerRU", DnS.res.getAtlas("pack").findRegion("CastleCornerRU"));
		temp.put("RCornerLD", DnS.res.getAtlas("pack").findRegion("CastleReverseCornerLD"));
		temp.put("RCornerLU", DnS.res.getAtlas("pack").findRegion("CastleReverseCornerLU"));
		temp.put("RCornerRD", DnS.res.getAtlas("pack").findRegion("CastleReverseCornerRD"));
		temp.put("RCornerRU", DnS.res.getAtlas("pack").findRegion("CastleReverseCornerRU"));
		temp.put("Floor", DnS.res.getAtlas("pack").findRegion("CastleFloor"));
		stageSprites.put("castle", temp);
	}
	
	public static  HashMap<String,TextureRegion> getStageSprites(String stageName){
		if(stageSprites.containsKey(stageName)){
			return stageSprites.get(stageName);
		}
		return null;
	}
	public static  TextureRegion getBackground(String stageName){
		if(backgrounds.containsKey(stageName)){
			return backgrounds.get(stageName);
		}
		return null;
	}
	
}
