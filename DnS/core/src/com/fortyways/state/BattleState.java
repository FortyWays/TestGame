package com.fortyways.state;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;
import com.battle.card.PlayerCard;
import com.battle.gui.PlayerCards;
import com.battle.player.BattleEnemy;
import com.battle.player.BattlePlayer;
import com.battle.turn.PlayerTurn;
import com.battle.turn.Turn;
import com.fortyways.dns.DnS;

public class BattleState extends State{
	public BattlePlayer player;
	public BattleEnemy[] enemies; 
	public ArrayList<Turn> turns;

	
	/////GUI
	public PlayerCards pc;
	
	/////
	BitmapFont font=new BitmapFont();
	////
	public BattleState(GSM gsm,BattlePlayer player,BattleEnemy[] enemies) {
		super(gsm);
		this.player=player;
		this.enemies=enemies;
		turns=new ArrayList<>();
		turns.add(new PlayerTurn());//player goes first as default
	}
	
	public BattleState(GSM gsm) {
		super(gsm);
		
		ArrayList<Card>playerDeck=new ArrayList<>();
		////////////Generating test decks
		playerDeck.add(new PlayerCard(10, 0, "TestAttack1",DnS.res.getAtlas("pack").findRegion("testAttack1")));
		playerDeck.add(new PlayerCard(10, 0, "TestAttack1",DnS.res.getAtlas("pack").findRegion("testAttack1")));
		playerDeck.add(new PlayerCard(10, 0, "TestAttack1",DnS.res.getAtlas("pack").findRegion("testAttack1")));
		playerDeck.add(new PlayerCard(10, 0, "TestAttack1",DnS.res.getAtlas("pack").findRegion("testAttack1")));
		playerDeck.add(new PlayerCard(0, 20, "TestHeal1",DnS.res.getAtlas("pack").findRegion("testHeal1")));
		playerDeck.add(new PlayerCard(0, 20, "TestHeal1",DnS.res.getAtlas("pack").findRegion("testHeal1")));
		playerDeck.add(new PlayerCard(0, 20, "TestHeal1",DnS.res.getAtlas("pack").findRegion("testHeal1")));
		playerDeck.add(new PlayerCard(0, 20, "TestHeal1",DnS.res.getAtlas("pack").findRegion("testHeal1")));
		playerDeck.add(new PlayerCard(10, 0, "TestAttack2",DnS.res.getAtlas("pack").findRegion("testAttack2")));
		playerDeck.add(new PlayerCard(10, 0, "TestAttack2",DnS.res.getAtlas("pack").findRegion("testAttack2")));
		playerDeck.add(new PlayerCard(10, 0, "TestAttack2",DnS.res.getAtlas("pack").findRegion("testAttack2")));
		playerDeck.add(new PlayerCard(10, 0, "TestAttack2",DnS.res.getAtlas("pack").findRegion("testAttack2")));
		///////////
		
		
		ArrayList<Card>aiDeck=new ArrayList<>();
		
		turns=new ArrayList<>();
		turns.add(new PlayerTurn());
		player=new BattlePlayer(50, 50, 50, playerDeck);
		player.GetRandomCardsFromDeck(5);
		pc=new PlayerCards(player.cardsinhand);
	}

	@Override
	public void handleInput() {	
		if(Gdx.input.justTouched()){
		
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		//sb.setProjectionMatrix(cam.combined);
		sb.begin();
		pc.render(sb);
		font.setColor(Color.RED);
		font.draw(sb,Integer.toString(player.getHp()), 10,20);
		font.setColor(Color.GREEN);
		font.draw(sb,Integer.toString(player.getSp()), 30,20);
		font.setColor(Color.BLUE);
		font.draw(sb,Integer.toString(player.getMp()), 50,20);
		
		sb.end();
	}

}
