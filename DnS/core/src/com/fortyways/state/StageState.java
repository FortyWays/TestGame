package com.fortyways.state;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.dns.DnS;
import com.fortyways.storages.ItemStorage;
import com.fortyways.util.Graphic;
import com.fortyways.util.Rectangle;
import com.fortyways.util.StageToBattleTransfer;
import com.stage.items.Item;
import com.stage.player.StageEnemy;
import com.stage.player.StagePlayer;
import com.stage.world.Tile;
import com.stage.world.World;
import com.stage.world.WorldBuilder;

public class StageState extends State{

	private World world;
	private StagePlayer player;

	private String stageName;
	private float panelHeight=300;
	private float panelWidth=500;
	
	private Graphic InventoryButton=new Graphic(DnS.WIDTH-50, DnS.HEIGHT/2,
			DnS.res.getAtlas("pack").findRegion("InventoryButton").getRegionWidth()*1,
			DnS.res.getAtlas("pack").findRegion("InventoryButton").getRegionHeight()*1
			, DnS.res.getAtlas("pack").findRegion("InventoryButton"));
	private Graphic HeroButton=new Graphic(DnS.WIDTH-50, DnS.HEIGHT/2+40,
			DnS.res.getAtlas("pack").findRegion("HeroButton").getRegionWidth()*1,
			DnS.res.getAtlas("pack").findRegion("HeroButton").getRegionHeight()*1
			, DnS.res.getAtlas("pack").findRegion("HeroButton"));
	private ArrayList<StageEnemy> removedEnemies = new ArrayList<>();
	private Rectangle panelRect=new Rectangle(DnS.WIDTH/2, DnS.HEIGHT/2, panelWidth, panelHeight);
	BitmapFont font =new BitmapFont();
	public StageState(GSM gsm) {
		super(gsm);
		world=new World();
		player=new StagePlayer();
		for(Tile t:world.tiles){
			t.getNewRelative(player);
		}
		for(Item item:world.items){
			item.getNewRelative(player);
		}
		
	}

	public StageState(GSM gsm, StagePlayer player, ArrayList<StageEnemy> removed) {
		super(gsm);

		world=WorldBuilder.buildWorld(player.stageName);
		this.player=player;
		this.removedEnemies=removed;
		for(StageEnemy Removedenemy: removed){
			for(StageEnemy Worldenemy:world.enemies){
				if(Removedenemy.getPosition().x==Worldenemy.getPosition().x
						&&Removedenemy.getPosition().y==Worldenemy.getPosition().y
						){
					world.enemies.remove(Worldenemy);
					break;
				}
			}
		}
		for(Tile t:world.tiles){
			t.getNewRelative(player);
		}
		
		for(Item item:world.items){
			item.getNewRelative(player);
		}
	}
	
	public StageState(GSM gsm,String playerName) {
		super(gsm);
		//world=new World();
		//stageName="desert";
		stageName="castle";
		world=WorldBuilder.buildWorld(stageName);
		player=new StagePlayer(playerName,stageName);

		for(Tile t:world.tiles){
			t.getNewRelative(player);
		}
		for(Item item:world.items){
			item.getNewRelative(player);
		}
	}
	@Override
	public void handleInput() {
		if(Gdx.input.isTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			
			
			if(world.clippingEnemy(player)){
				if(!panelRect.Touched(mouse.x, mouse.y))
			world.movePlayer(player,mouse);
				world.selectedEnemy.panel.handleInput(mouse.x, mouse.y);
				if(world.selectedEnemy.fightButton.Touched(mouse.x, mouse.y))
				gsm.set(new TransitionFadeState(gsm, this, new BattleState(gsm,
						player,world.selectedEnemy,removedEnemies)));
			}
			else if(world.clippingItem(player)){
				if(!panelRect.Touched(mouse.x, mouse.y))
					world.movePlayer(player,mouse);
				world.selectedItem.panel.handleInput(mouse.x, mouse.y);
				if(world.selectedItem.equipButton.Touched(mouse.x, mouse.y)
						&&Gdx.input.justTouched()){
					if(world.selectedItem.getClassName()==player.playerName 
							||world.selectedItem.getClassName()=="" ){
					world.selectedItem.Equip(player);
					world.items.remove(world.selectedItem);
					}
				}
			}
			else if(world.clippingChest(player)){
				
				if(world.selectedChest.openButton.Touched(mouse.x, mouse.y)
						&&world.selectedChest.isClosed()
						&&Gdx.input.justTouched()){
				world.selectedChest.open(world,player);}
				else{
					world.movePlayer(player,mouse);
				}
			}
			else if(world.clippingPickup(player)){
				world.selectedPickUp.use(player);
				world.pickups.remove(world.selectedPickUp);
			}
			else
			world.movePlayer(player,mouse);	
			
			
		}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		player.update(dt);
		world.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		world.render(sb);
		
	
		world.renderEnemies(sb);
		player.render(sb);
		world.renderButton(sb, player);
		InventoryButton.render(sb);
		HeroButton.render(sb);
		//font.draw(sb, player.getWorldX()+" "+player.getWorldY(), 20, 20);
		sb.end();
		
	}

}
