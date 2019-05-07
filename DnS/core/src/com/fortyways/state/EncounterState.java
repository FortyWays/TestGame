package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.CustomFont;
import com.encounter.Encounter;
import com.encounter.EncounterPlayer;
import com.encounter.Outcome;
import com.fortyways.dns.DnS;
import com.fortyways.storages.EncounterStorage;
import com.fortyways.storages.ItemStorage;
import com.fortyways.util.Graphic;
import com.fortyways.util.Rectangle;
import com.fortyways.util.StageToBattleTransfer;
import com.stage.graphics.DeckPanel;

public class EncounterState extends State {
	public int encounterCount;
	public int encounterAmount;
	private DeckPanel deckPanel;
	private boolean inDeckPanel=false;
	private Rectangle panelRect=new Rectangle(DnS.WIDTH/2, DnS.HEIGHT/2, 500, 300);
	
	private Graphic top=new Graphic(DnS.WIDTH/2+105, DnS.HEIGHT-15,
			DnS.res.getAtlas("pack").findRegion("EncunterTop").getRegionWidth(),
			DnS.res.getAtlas("pack").findRegion("EncunterTop").getRegionHeight(),
			DnS.res.getAtlas("pack").findRegion("EncunterTop"));
	private Graphic InventoryButton=new Graphic(DnS.WIDTH-50, DnS.HEIGHT/2,
			DnS.res.getAtlas("pack").findRegion("InventoryButton").getRegionWidth()*1,
			DnS.res.getAtlas("pack").findRegion("InventoryButton").getRegionHeight()*1
			, DnS.res.getAtlas("pack").findRegion("InventoryButton"));
	private Graphic DeckButton=new Graphic(DnS.WIDTH-50, DnS.HEIGHT/2+40,
			DnS.res.getAtlas("pack").findRegion("DeckButton").getRegionWidth()*1,
			DnS.res.getAtlas("pack").findRegion("DeckButton").getRegionHeight()*1
			, DnS.res.getAtlas("pack").findRegion("DeckButton"));
	
	BitmapFont font=new BitmapFont();
	EncounterPlayer player;
	Encounter currentEncounter=new Encounter(EncounterStorage.getRandomEncounter());;
	Outcome currentOutcome;

	
	public EncounterState(GSM gsm) {
		super(gsm);
		player=new EncounterPlayer("player-warrior");
		player.stageName="desert";
		encounterAmount=10;
		
	}
	
	public EncounterState(GSM gsm,EncounterPlayer player) {
		super(gsm);
		this.player=player;
		this.currentEncounter=player.curEncounter;
		currentEncounter.chosen=true;
		currentOutcome=currentEncounter.chosenOutcome;
		currentEncounter.fightended=true;
		
		if(currentOutcome.hpgain!=0){
			player.setHp(player.getHp()+currentOutcome.hpgain);
		}
		if(currentOutcome.spgain!=0){
			player.setSp(player.getSp()+currentOutcome.spgain);
		}
		if(currentOutcome.mpgain!=0){
			player.setMp(player.getMp()+currentOutcome.mpgain);
		}
		if(currentOutcome.foodgain!=0){
			player.setFood(player.getFood()+currentOutcome.foodgain);
			System.out.println(currentOutcome.foodgain);
		}
		if(currentOutcome.moneygain!=0){
			player.setMoney(player.getMoney()+currentOutcome.moneygain);
		}
		if(currentOutcome.famegain!=0){
			player.setFame(player.getFame()+currentOutcome.famegain);
		}
		if(currentOutcome.itemgain){
			if(currentOutcome.item==null)
			player.equipItem(ItemStorage.getRandomItemNoDupesWithClass(player));
			else
			player.equipItem(currentOutcome.item);;
		}
		System.out.println(player.getMoney());
		encounterAmount=10;
		player.updateDisplays();
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			if(!currentEncounter.chosen&&!inDeckPanel)
			currentEncounter.handleInput(mouse.x, mouse.y);
			else{
			if(currentEncounter.continueOption.background.Touched(mouse.x, mouse.y)&&!inDeckPanel){
				getNewEncounter();
				
			}
			}			
			if(currentEncounter.chosen&&!inDeckPanel){
				currentOutcome=currentEncounter.chosenOption.receiveOutcome();
				currentEncounter.chosenOutcome=currentOutcome;
				if(!currentOutcome.startBattle){
				if(currentOutcome.hpgain!=0){
					player.setHp(player.getHp()+currentOutcome.hpgain);
				}
				if(currentOutcome.spgain!=0){
					player.setSp(player.getSp()+currentOutcome.spgain);
				}
				if(currentOutcome.mpgain!=0){
					player.setMp(player.getMp()+currentOutcome.mpgain);
				}
				if(currentOutcome.foodgain!=0){
					player.setFood(player.getFood()+currentOutcome.foodgain);
				}
				if(currentOutcome.moneygain!=0){
					player.setMoney(player.getMoney()+currentOutcome.moneygain);
				}
				if(currentOutcome.famegain!=0){
					player.setFame(player.getFame()+currentOutcome.famegain);
				}
				if(currentOutcome.itemgain){
					if(currentOutcome.item==null)
						player.equipItem(ItemStorage.getRandomItemNoDupesWithClass(player));
						else
						player.equipItem(currentOutcome.item);;
				}
				player.updateDisplays();}
				if(currentOutcome.startBattle&&!currentEncounter.fightended){
					player.curEncounter=currentEncounter;
					gsm.set(new TransitionFadeState(gsm, this, new BattleState(gsm,
							player,currentOutcome.enemyTags)));
				}
			}
			
			if(inDeckPanel){
				
				if(!panelRect.Touched(mouse.x, mouse.y)){
					inDeckPanel=false;
				}
				else
				deckPanel.handleInput(mouse.x, mouse.y);
			}
			if(!inDeckPanel&&DeckButton.Touched(mouse.x, mouse.y)){
				deckPanel=new DeckPanel(StageToBattleTransfer.trasferPlayer(player).getDeck());
				inDeckPanel=true;
			}
			
			
			
		}
		
	}

	private void getNewEncounter() {
		if(player.getFood()>0){
		player.setFood(player.getFood()-1);
		player.setHp(player.getHp()+player.getHpregen());
		player.setSp(player.getHp()+player.getHpregen());
		player.setMp(player.getHp()+player.getHpregen());
		player.updateDisplays();
		}
		currentEncounter=new Encounter(EncounterStorage.getRandomEncounter());
		currentEncounter.chosenOption=null;
		currentEncounter.chosenOutcome=null;
		player.curEncounter=currentEncounter;
		currentOutcome=null;
		currentEncounter.chosen=false;
		currentEncounter.fightended=false;
		currentOutcome=null;
		
		player.curEncounter=this.currentEncounter;
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		currentEncounter.update(dt);
		if(inDeckPanel){
			deckPanel.update(dt);
		}
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		top.render(sb);
		player.render(sb);
		currentEncounter.render(sb);
		InventoryButton.render(sb);
		DeckButton.render(sb);
		if(inDeckPanel){
		deckPanel.render(sb);
		}
		sb.end();
	}

}
