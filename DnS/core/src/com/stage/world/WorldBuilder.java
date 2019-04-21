package com.stage.world;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.fortyways.storages.ItemStorage;
import com.fortyways.storages.StageStorage;
import com.stage.player.StageEnemy;

public class WorldBuilder {

	public static World buildWorld(String name){
		
		int tileSize=40;
		World world=new World();
		if(name=="castle"){
		world.size=50;
		Texture map=new Texture("map2.png");
		map.getTextureData().prepare();
		Pixmap pixmap=map.getTextureData().consumePixmap();

		ArrayList<Tile> tiles=new ArrayList<>();
		ArrayList<Tile> impassableTiles= new ArrayList<>();

		for(int i=0;i<pixmap.getWidth();i++){
			for(int j=0;j<pixmap.getHeight();j++){
				if(1734439167==(pixmap.
						getPixel(i, j))){
					tiles.add(new Tile(0+i*tileSize, 0+j*tileSize, 
							DnS.res.getAtlas("pack").findRegion("CastleFloor"), true));
				}
				else if(791625727==(pixmap.
						getPixel( i, j))){
					tiles.add(new Tile(0+i*tileSize, 0+j*tileSize, getWallTexture(name,i,j), true));
					impassableTiles.add(tiles.get(tiles.size()-1));
				}		
			}
		}

		if(getTile(tiles,520, 840)!=null)
		world.enemies.add(new StageEnemy(getTile(tiles,520, 840),"automaton",name));
		if(getTile(tiles,590, 840)!=null)
		world.pickups.add(new PickUp("hp",getTile(tiles,590, 840)));
		if(getTile(tiles, 400, 910)!=null)
		world.chests.add(new Chest(getTile(tiles,  410, 910)));
		//world.items.add(ItemStorage.getItem("Charged Sword"));
		//world.items.get(0).setUpPanel();
		//world.items.get(0).setWorldCoords(450, 870);
		world.setTiles(tiles, impassableTiles);
		return world;
		
		}
		if(name=="desert"){
		world.size=50;
		Texture map=new Texture("map1.png");
		map.getTextureData().prepare();
		Pixmap pixmap=map.getTextureData().consumePixmap();
	
		ArrayList<Tile> tiles=new ArrayList<>();
		ArrayList<Tile> impassableTiles= new ArrayList<>();
		
		for(int i=0;i<pixmap.getWidth();i++){
			for(int j=0;j<pixmap.getHeight();j++){
				if(-2219009==(pixmap.
						getPixel(i, j))){
					tiles.add(new Tile(0+i*tileSize, 0+j*tileSize, DnS.res.getAtlas("pack").findRegion("Desert1"), true));
				}
				else if(-9830145==(pixmap.
						getPixel( i, j))){
					tiles.add(new Tile(0+i*tileSize, 0+j*tileSize, getWallTexture(name,i,j), true));
					impassableTiles.add(tiles.get(tiles.size()-1));
				}
				
			}
		}
		if(getTile(tiles,300, 900)!=null)
		world.enemies.add(new StageEnemy(getTile(tiles,300, 900),"goblin-berserk",name));
		if(getTile(tiles,700, 990)!=null)
		world.enemies.add(new StageEnemy(getTile(tiles,700, 990),"cultist",name));
		world.items.add(ItemStorage.getItem("Speed Stone"));
		world.items.get(0).setUpPanel();
		world.items.get(0).setWorldCoords(800, 990);
		world.items.add(ItemStorage.getItem("Antivenom"));
		world.items.get(1).setUpPanel();
		world.items.get(1).setWorldCoords(1000, 1200);
		world.setTiles(tiles, impassableTiles);
		return world;
		}
		
		return null;
	}
	
	private static TextureRegion getWallTexture(String name,int i,int j){
		
		HashMap<String,TextureRegion> stageSprites=StageStorage.getStageSprites(name);
		int floor=0;
		Texture map=new Texture("map1.png");
		if(name=="castle"){
			map=new Texture("map2.png");
			floor=1734439167;
		}
		
		if(name=="desert"){
			floor=-2219009;
			map=new Texture("map1.png");
		}
		
		map.getTextureData().prepare();
		Pixmap pixmap=map.getTextureData().consumePixmap();
		
		if(pixmap.getPixel(i, j)==pixmap.getPixel(i-1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i+1, j)
				&& pixmap.getPixel(i, j+1)==floor
				){
			
			return stageSprites.get("WallDown");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i-1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j+1)
				&& pixmap.getPixel(i, j-1)!=floor
				){
			return stageSprites.get("CornerRD");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i+1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j+1)
				&& pixmap.getPixel(i, j-1)!=floor
				){
			return stageSprites.get("CornerLD");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i-1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j-1)
				&& pixmap.getPixel(i+1, j)!=floor
				){
			return stageSprites.get("CornerRU");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i+1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j-1)
				&& pixmap.getPixel(i-1, j)!=floor
				){
			return stageSprites.get("CornerLU");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i+1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j+1)
				&& pixmap.getPixel(i-1, j)==floor
				){
			return stageSprites.get("RCornerLU");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i-1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j+1)
				&& pixmap.getPixel(i, j-1)==floor
				){
			return stageSprites.get("RCornerRU");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i+1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j-1)
				&& pixmap.getPixel(i, j+1)==floor
				){
			return stageSprites.get("RCornerLD");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i-1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j-1)
				&& pixmap.getPixel(i, j+1)==floor
				){
			return stageSprites.get("RCornerRD");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i-1, j)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i+1, j)
				&& pixmap.getPixel(i, j-1)==floor){
			return stageSprites.get("WallUp");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i, j+1)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j-1)
				&& pixmap.getPixel(i+1, j)==floor){
			return stageSprites.get("WallLeft");
		}
		else if(pixmap.getPixel(i, j)==pixmap.getPixel(i, j+1)
				&& pixmap.getPixel(i, j)==pixmap.getPixel(i, j-1)
				&& pixmap.getPixel(i-1, j)==floor){
			return stageSprites.get("WallRight");
		}
		else return DnS.res.getAtlas("pack").findRegion("Wall1");
		
		//return null;
		
		
	}
	public static Tile getTile(ArrayList<Tile> tiles,float x,float y){
		Tile result=null;
		for(Tile t:tiles){
			if(t.Touched(x, y)){
				result=t;
				break;
			}
		}
		return result;
	}
	
	
}
