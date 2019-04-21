package com.stage.world;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.fortyways.dns.DnS;
import com.fortyways.util.Rectangle;
import com.stage.items.Item;
import com.stage.player.StageEnemy;
import com.stage.player.StagePlayer;

public class World {

	public ArrayList<Tile> tiles;
	public ArrayList<Tile> impassableTiles;
	//public Color backColor= new Color(198, 152, 73,1);
	public int size=20;
	public int tileSize=40;
	public int worldOffset=30;
	public ArrayList<StageEnemy> enemies=new ArrayList<>();
	public ArrayList<Item> items=new ArrayList<>();
	public ArrayList<Chest> chests=new ArrayList<>();
	public ArrayList<PickUp> pickups=new ArrayList<>();
	public PickUp selectedPickUp;
	public StageEnemy selectedEnemy;
	public Chest selectedChest;
	public Item selectedItem;
	public World() {
		
	}
	public World(String name) {
		tiles=new ArrayList<>();
		
		
	}
	public void setTiles(ArrayList<Tile> tiles,ArrayList<Tile> impassableTiles){
		this.tiles=tiles;
		this.impassableTiles=impassableTiles;
		
	}
	
	
	public boolean clippingEnemy(StagePlayer player){
		for(StageEnemy enemy:enemies){
			Rectangle temp=new Rectangle(enemy.getPosition().x-5, enemy.getPosition().y-5,
					enemy.getPosition().width+10f, enemy.getPosition().height+10f);
			if(temp.Touched(player.getWorldX()-20, player.getWorldY()-20)){
				selectedEnemy=enemy;
				return true;
			}
		}
		return false;
	}
	public boolean clippingItem(StagePlayer player){
		for(Item item:items){
			if(item.Touched(player.getWorldX()-20,  player.getWorldY()-20)){
				selectedItem=item;
				return true;
			}
		}
			return false;
	}
	public boolean clippingChest(StagePlayer player){
		for(Chest chest:chests){
			
			if(chest.Touched(player.getWorldX()-20, player.getWorldY()-20)){
				selectedChest=chest;
				return true;
			}
		}
			return false;
	}
	public boolean clippingPickup(StagePlayer player){
		for(PickUp pickup:pickups){
			
			if(pickup.Touched(player.getWorldX()-20, player.getWorldY()-20)){
				selectedPickUp=pickup;
				return true;
			}
		}
			return false;
	}
	public void render(SpriteBatch spriteBatch){
		//Gdx.gl.glClearColor(198f/255f, 152f/255f, 73f/255f, 1);
		for(Tile t:tiles){
			t.render(spriteBatch);
		}
		for(Chest chest:chests){
			chest.render(spriteBatch);
		}
		for(Item item:items){
			item.render(spriteBatch);
		}
		for(PickUp pickup:pickups){
			pickup.render(spriteBatch);
		}
	
		//spriteBatch.setColor(backColor);
	}
	public void renderEnemies(SpriteBatch spriteBatch){
		for(StageEnemy enemy:enemies){
			enemy.render(spriteBatch);
		}
	}
	public void renderButton(SpriteBatch spriteBatch,StagePlayer player){
		if(clippingEnemy(player)){
			selectedEnemy.renderButton(spriteBatch);
		}
		if(clippingItem(player)){
			selectedItem.renderButton(spriteBatch);
		}
		if(clippingChest(player)){
			if(selectedChest.isClosed())
			selectedChest.renderButton(spriteBatch);
		}
	}
	
	public void setPlayerCoords(StagePlayer player,float x,float y){
		if(y>tileSize&&y<size*tileSize){
			boolean check=false;
			for(Tile t:impassableTiles){
				Rectangle temp=new Rectangle(t.x, t.y, t.width+0.01f, t.height+0.01f);
				if(temp.Touched(x-20, y-30)){
					check=true;
					break;
				}
			}
			for(StageEnemy enemy:enemies){

				Rectangle temp=new Rectangle(enemy.getPosition().x, enemy.getPosition().y, 
						enemy.getPosition().width+0.01f, enemy.getPosition().height+0.01f);
				if(temp.Touched(x-20, y-30)){
					check=true;
					break;
				}
			}
			if(!check)
			player.setWorldY(y);
		}
		if(x>tileSize&&x<size*tileSize){
			boolean check=false;
			for(Tile t:impassableTiles){
				Rectangle temp=new Rectangle(t.x, t.y, t.width+0.01f, t.height+0.01f);
				if(temp.Touched(x-20, y-30)){
					check=true;
					break;
				}
			}
			for(StageEnemy enemy:enemies){

				Rectangle temp=new Rectangle(enemy.getPosition().x, enemy.getPosition().y, 
						enemy.getPosition().width+0.01f, enemy.getPosition().height+0.01f);
				if(temp.Touched(x-20, y-30)){
					check=true;
					break;
				}
			}
			if(!check)
			player.setWorldX(x);
		}
		
	}

	public void movePlayer(StagePlayer player, Vector3 mouse) {
		if(mouse.x>DnS.WIDTH/3&&mouse.x<2*DnS.WIDTH/3&&mouse.y>2*DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX(),player.getWorldY()+player.speed);
		}
		else if(mouse.x>2*DnS.WIDTH/3&&mouse.y>DnS.HEIGHT/3&&mouse.y<2*DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX()+player.speed,player.getWorldY());
		}
		else if(mouse.x<DnS.WIDTH/3&&mouse.y>DnS.HEIGHT/3&&mouse.y<2*DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX()-player.speed,player.getWorldY());
		}
		else if(mouse.x<DnS.WIDTH/3&&mouse.y>2*DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX()-player.speed,player.getWorldY()+player.speed);
		}
		else if(mouse.x>2*DnS.WIDTH/3&&mouse.y>2*DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX()+player.speed,player.getWorldY()+player.speed);
		}
		else if(mouse.x<DnS.WIDTH/3&&mouse.y<=DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX()-player.speed,player.getWorldY()-player.speed);
		}
		else if(mouse.x>DnS.WIDTH/3&&mouse.x<2*DnS.WIDTH/3&&mouse.y<DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX(),player.getWorldY()-player.speed);
		}
		else if(mouse.x>2*DnS.WIDTH/3&&mouse.y<DnS.HEIGHT/3){
			setPlayerCoords(player,player.getWorldX()+player.speed,player.getWorldY()-player.speed);
		}
		for(Tile t:tiles){
			if(t.Touched(player.getWorldX(), player.getWorldY())){
			//	System.out.println(t.x+" "+t.y);
			}
			t.getNewRelative(player);
		}
		for(Item item:items){
			item.getNewRelative(player);
		}
		
	}

	public void update(float dt) {
		for(StageEnemy enemy:enemies){
			enemy.update(dt);
		}
		for(Item item:items){
			item.update(dt);
		}
		for(PickUp pickup:pickups){
			pickup.update(dt);
		}
	}
	
}
