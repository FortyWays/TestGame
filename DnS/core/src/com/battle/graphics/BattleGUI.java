package com.battle.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.player.BattleEntity;
import com.fortyways.dns.DnS;
import com.fortyways.state.BattleState;
import com.fortyways.storages.StageStorage;
import com.fortyways.util.Graphic;

public class BattleGUI {

	public PlayerCards playerCards;
	
	public Graphic selectButton;
	public Graphic cancelButton;
	public Graphic endTurnButton;
	
	public Graphic VictoryScreen;
	public Graphic DefeatScreen;
	
	private Graphic selectATarget;
	private Graphic bottom;
	private Graphic top;
	private Graphic currentTurn;
	private Graphic deck;
	private Graphic background;
	private Graphic health;
	private Graphic stamina;
	private Graphic mana;
	private Graphic cardsPlayed;
	private Graphic leftTopCorner;
	private Graphic darkPresenceSign;
	private CustomFont dp;
	public boolean switchingTurnSigns=false;;
	private boolean switched=false;

	private BattleState state;
	
	
	BitmapFont font=new BitmapFont();
	public BattleGUI(BattleState state) {
		this.state=state;
		playerCards=new PlayerCards(state.player.cardsInHand);
		dp=new CustomFont(Integer.toString(state.DarkPresence), 50, DnS.HEIGHT/2+49, "purple");
		darkPresenceSign=new Graphic(
				DnS.res.getAtlas("pack").findRegion("DarkPresenceSign").getRegionWidth()/2,DnS.HEIGHT/2+50,
				DnS.res.getAtlas("pack").findRegion("DarkPresenceSign").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("DarkPresenceSign").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("DarkPresenceSign"));
		cardsPlayed=new Graphic(
				DnS.WIDTH-40,DnS.HEIGHT-20,
				DnS.res.getAtlas("pack").findRegion("CardsPlayed").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("CardsPlayed").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("CardsPlayed"));
		leftTopCorner=new Graphic(
				DnS.WIDTH-DnS.res.getAtlas("pack").findRegion("MenuTopCorner").getRegionWidth()*2,
				DnS.HEIGHT-DnS.res.getAtlas("pack").findRegion("MenuTopCorner").getRegionHeight()*2,
				DnS.res.getAtlas("pack").findRegion("MenuTopCorner").getRegionWidth()*4,
				DnS.res.getAtlas("pack").findRegion("MenuTopCorner").getRegionHeight()*4,
				DnS.res.getAtlas("pack").findRegion("MenuTopCorner"));
		selectATarget=new Graphic(
				DnS.WIDTH/2,DnS.HEIGHT-75,
				DnS.res.getAtlas("pack").findRegion("SelectATarget").getRegionWidth()*2,
				DnS.res.getAtlas("pack").findRegion("SelectATarget").getRegionHeight()*2,
				DnS.res.getAtlas("pack").findRegion("SelectATarget"));
		VictoryScreen=new Graphic(
				DnS.WIDTH/2,DnS.HEIGHT/2,
				DnS.res.getAtlas("pack").findRegion("VictoryScreen").getRegionWidth()*6,
				DnS.res.getAtlas("pack").findRegion("VictoryScreen").getRegionHeight()*6,
				DnS.res.getAtlas("pack").findRegion("VictoryScreen"));
		DefeatScreen=new Graphic(
				DnS.WIDTH/2,DnS.HEIGHT/2,
				DnS.res.getAtlas("pack").findRegion("DefeatScreen").getRegionWidth()*6,
				DnS.res.getAtlas("pack").findRegion("DefeatScreen").getRegionHeight()*6,
				DnS.res.getAtlas("pack").findRegion("DefeatScreen"));
		currentTurn=new Graphic(
				DnS.WIDTH/2, 130,
				DnS.res.getAtlas("pack").findRegion("YourTurn").getRegionWidth()*2,
				DnS.res.getAtlas("pack").findRegion("YourTurn").getRegionHeight()*2,
				DnS.res.getAtlas("pack").findRegion("YourTurn"));
		deck=new Graphic(DnS.WIDTH-50, 70,
				DnS.res.getAtlas("pack").findRegion("Deck").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("Deck").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("Deck"));
		background=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2,
				StageStorage.getBackground(state.stageName).getRegionWidth()*4,
				StageStorage.getBackground(state.stageName).getRegionHeight()*4,
				StageStorage.getBackground(state.stageName));
		selectButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT-30,
				DnS.res.getAtlas("pack").findRegion("SelectButton").getRegionWidth()*4,
				DnS.res.getAtlas("pack").findRegion("SelectButton").getRegionHeight()*4,
				DnS.res.getAtlas("pack").findRegion("SelectButton"));
		cancelButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT-30,
				DnS.res.getAtlas("pack").findRegion("CancelButton").getRegionWidth()*4,
				DnS.res.getAtlas("pack").findRegion("CancelButton").getRegionHeight()*4,
				DnS.res.getAtlas("pack").findRegion("CancelButton"));
		endTurnButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT-30,
				DnS.res.getAtlas("pack").findRegion("EndTurnButton").getRegionWidth()*4,
				DnS.res.getAtlas("pack").findRegion("EndTurnButton").getRegionHeight()*4,
				DnS.res.getAtlas("pack").findRegion("EndTurnButton"));
		bottom=new Graphic(
				DnS.WIDTH/2, DnS.res.getAtlas("pack").findRegion("Bottom2").getRegionHeight()*2,
				DnS.res.getAtlas("pack").findRegion("Bottom2").getRegionWidth()*4,
				DnS.res.getAtlas("pack").findRegion("Bottom2").getRegionHeight()*4,
				DnS.res.getAtlas("pack").findRegion("Bottom2"));
		top=new Graphic(
				DnS.WIDTH/2, 
				DnS.HEIGHT-DnS.res.getAtlas("pack").findRegion("Top2").getRegionHeight()*2,
				DnS.res.getAtlas("pack").findRegion("Top2").getRegionWidth()*4,
				DnS.res.getAtlas("pack").findRegion("Top2").getRegionHeight()*4,
				DnS.res.getAtlas("pack").findRegion("Top2"));
		health=new Graphic(
				30, 30,
				DnS.res.getAtlas("pack").findRegion("Health").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("Health").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("Health"));
		stamina=new Graphic(
				30, 88,
				DnS.res.getAtlas("pack").findRegion("Stamina").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("Stamina").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("Stamina"));
		mana=new Graphic(
				30, 58,
				DnS.res.getAtlas("pack").findRegion("Mana").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("Mana").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("Mana"));
		
		
	}
	
	public void handleInput(float x, float y){
		playerCards.handleInput(x, y);
		if(playerCards.selected&&playerCards.cards.size()>0){
			
		selectButton.x=playerCards.cards.get(playerCards.selnum).x+145;
		selectButton.y=160;//playerCards.cards.get(playerCards.selnum).y+110;
		
		}
	}
	
	public void update(float dt){
		
		playerCards.update(dt);
		if(switchingTurnSigns && !switched && currentTurn.y>100){
			currentTurn.y-=2;
		}
		else if(switchingTurnSigns && !switched &&  currentTurn.y==100){
			switchCurrentTurnSign();
		}
		else if(switchingTurnSigns && currentTurn.y!=130){
			currentTurn.y+=2;
		}
		else{
			switchingTurnSigns=false;
			switched=false;
		}
		
	}
	
	public void render(SpriteBatch spriteBatch,boolean waiting) {
		if(waiting){
		selectATarget.render(spriteBatch);}
		top.render(spriteBatch);
		leftTopCorner.render(spriteBatch);
		cardsPlayed.render(spriteBatch);
		font.setColor(Color.GRAY);
		font.draw(spriteBatch, Integer.toString(state.cardsPlayed)+"/"+state.player.cardsPerTurn, 
				DnS.WIDTH-30, DnS.HEIGHT-25);
		if(!state.gameFinished){
		currentTurn.render(spriteBatch);
		}
		bottom.render(spriteBatch);
		font.setColor(Color.YELLOW);
		font.draw(spriteBatch,Integer.toString(state.player.cardsLeftInDeck()), DnS.WIDTH-53,18);
		health.render(spriteBatch);
		stamina.render(spriteBatch);
		mana.render(spriteBatch);
		deck.render(spriteBatch);
		if(state.DarkPresence>0){
		darkPresenceSign.render(spriteBatch);
		dp.render(spriteBatch);
		}
		if(state.gameFinished&&state.playerWon){
		VictoryScreen.render(spriteBatch);
		}
		else if(state.gameFinished){
		DefeatScreen.render(spriteBatch);
		}
		playerCards.render(spriteBatch);
		if(playerCards.selected&&playerCards.cards.size()>0&&
				playerCards.descArt.x>playerCards.cards.get(playerCards.selnum).x+50){
			selectButton.render(spriteBatch);
				}
			else{if(!waiting){
			endTurnButton.render(spriteBatch);
			}
			else{
			cancelButton.render(spriteBatch);
			}
				}
	}

	public void renderBackground(SpriteBatch spriteBatch) {
		background.render(spriteBatch);
	}
	private void switchCurrentTurnSign(){
		if(currentTurn.image==DnS.res.getAtlas("pack").findRegion("YourTurn")){
			currentTurn.image=DnS.res.getAtlas("pack").findRegion("EnemyTurn");
			endTurnButton.image=DnS.res.getAtlas("pack").findRegion("EndTurnButton2");
		}
		else{
			currentTurn.image=DnS.res.getAtlas("pack").findRegion("YourTurn");
			endTurnButton.image=DnS.res.getAtlas("pack").findRegion("EndTurnButton");
		}
		switched=true;
	}
	public void PlayCard(BattleEntity caster,BattleState battleState){
		boolean handNeedsRefiling=false;
		if(playerCards.getSelectedCard().HasDraw()){
			handNeedsRefiling=true;
		}
		playerCards.getSelectedCard().Play(caster, battleState);
		playerCards.deleteSelectedCard();
		if(handNeedsRefiling){
			playerCards.checkForNewCards(caster);
		}
	}
	public void PlayCard(BattleEntity caster,BattleEntity target){
		boolean handNeedsRefiling=false;
		if(playerCards.getSelectedCard().HasDraw()){
			handNeedsRefiling=true;
		}
		playerCards.getSelectedCard().Play(caster, target);
		playerCards.deleteSelectedCard();
		if(handNeedsRefiling){
			playerCards.checkForNewCards(caster);
		}
	}
	public void updateDP(){
		dp=new CustomFont(Integer.toString(state.DarkPresence), 50, DnS.HEIGHT/2+49, "purple");
	}



}
