package com.fortyways.state;

import java.awt.Font;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.battle.card.Card;
import com.battle.graphics.BattleGUI;
import com.battle.graphics.FadingAnimation;
import com.battle.graphics.PlayerCards;
import com.battle.player.BattleEnemy;
import com.battle.player.BattleEntity;
import com.battle.player.BattleMinion;
import com.battle.player.BattlePlayer;
import com.battle.turn.PlayerTurn;
import com.battle.turn.Turn;
import com.encounter.EncounterPlayer;
import com.fortyways.dns.DnS;
import com.fortyways.storages.CardStorage;
import com.fortyways.util.Rectangle;
import com.fortyways.util.StageToBattleTransfer;
import com.stage.graphics.DeckPanel;
import com.stage.player.StageEnemy;
import com.stage.player.StagePlayer;

public class BattleState extends State{
	//Entities
	public BattlePlayer player;
	public  ArrayList<BattleEntity> enemyEnties; 
	public  ArrayList<BattleEnemy> enemies; 
	public  ArrayList<BattleMinion> minions;
	public ArrayList<BattleEntity> enties;
	
	
	//Turns
	public ArrayList<Turn> turns;

	
	//GUI
	BitmapFont font =new BitmapFont();
	BattleGUI gui;
	DeckPanel deckPanel;
	//State of the game
	
	boolean waitingForTarget=false;
	private int currentAIAnimatedNumber;
	private int currentAIAnimatedNumberMinions;
	public boolean gameFinished=false;
	public boolean playerWon=false;
	private boolean inPanel=false;
	private boolean animatingminions=true;
	private boolean inDeck=false;
	//Transfer to stage
	public String stageName;
	EncounterPlayer encounterPlayer;
	StageEnemy stageEnemy;
	ArrayList<StageEnemy> removedEnemies;
	
	
	public int DarkPresence=0;
	private BattleEntity selectedEnemy;
	
	private Rectangle panelRect=new Rectangle(DnS.WIDTH/2, DnS.HEIGHT/2, 500, 300);
	public int cardsPlayed=0;
	////
	public BattleState(GSM gsm,BattlePlayer player, ArrayList<BattleEntity> enemies) {
		super(gsm);
		
		this.player=player;
		this.enemyEnties=enemies;
		minions=new ArrayList<>();
		turns=new ArrayList<>();
		turns.add(new PlayerTurn(1));//player goes first as default
		player.FillEmptyHand(5);
		enties=new ArrayList<>();
		enties.add(player);
		for(BattleEntity enemy:enemies){
			enties.add(enemy);
		}
		gui=new BattleGUI(this);
	}
	
	public BattleState(GSM gsm,StagePlayer stagePlayer,StageEnemy stageEnemy,ArrayList<StageEnemy> removed) {
		super(gsm);
		
		turns=new ArrayList<>();
		turns.add(new PlayerTurn(1));
		this.player=StageToBattleTransfer.trasferPlayer(stagePlayer);
		DarkPresence+=player.getDpMod();
		this.removedEnemies=removed;
		this.encounterPlayer=stagePlayer;
		this.stageName=stagePlayer.stageName;
		this.stageEnemy=stageEnemy;
		minions=new ArrayList<>();
		enemies=new ArrayList<>();
		enemies.add(new BattleEnemy(stageEnemy.getEntityName(),0,stageName));
		
		for(BattleEnemy enemy:enemies){
			enemy.setUpAi(this);
		}
		//enemies.get(0).setUpAi(this );

		enemyEnties=new ArrayList<BattleEntity>();
		for(BattleEnemy enemy:enemies){
			enemyEnties.add(enemy);
		}
		
		//enemyEnties.add(enemies.get(1));
		for(BattleEnemy enemy:enemies){
			//enemy.setUpAi(this);
			enemy.FillEmptyHand(7);
		}
		
		//enemyEnties.get(1).FillEmptyHand(7);
	
		player.FillEmptyHand(player.getStartingCardAmount());
		enties=new ArrayList<>();
		enties.add(player);
		for(BattleEntity enemy:enemyEnties){
			enties.add(enemy);
		}
		gui=new BattleGUI(this);
	
	}
	public BattleState(GSM gsm,EncounterPlayer encounterPlayer,ArrayList<String>enemyTags) {
		super(gsm);
		
		turns=new ArrayList<>();
		turns.add(new PlayerTurn(1));
		this.encounterPlayer=encounterPlayer;
		this.player=StageToBattleTransfer.trasferPlayer(encounterPlayer);
		DarkPresence+=player.getDpMod();

		this.stageName=encounterPlayer.stageName;
		minions=new ArrayList<>();
		enemies=new ArrayList<>();
		int i=0;
		for(String name: enemyTags){
		enemies.add(new BattleEnemy(name,i,stageName));i++;}
		
		for(BattleEnemy enemy:enemies){
			enemy.setUpAi(this);
		}
		
		enemyEnties=new ArrayList<BattleEntity>();
		for(BattleEnemy enemy:enemies){
			enemyEnties.add(enemy);
		}
		
	
		for(BattleEnemy enemy:enemies){
			enemy.FillEmptyHand(7);
		}
		
		player.FillEmptyHand(player.getStartingCardAmount());
		enties=new ArrayList<>();
		enties.add(player);
		for(BattleEntity enemy:enemyEnties){
			enties.add(enemy);
		}
		gui=new BattleGUI(this);
	
	}
	public BattleState(GSM gsm) {
		super(gsm);
		
		ArrayList<Card>playerDeck=new ArrayList<>();
		//Generating test decks
		playerDeck.add(CardStorage.getCard("Mark"));
		playerDeck.add(CardStorage.getCard("Mark"));
		playerDeck.add(CardStorage.getCard("Quick Shot"));
		playerDeck.add(CardStorage.getCard("Quick Shot"));
		playerDeck.add(CardStorage.getCard("Quick Shot"));
		playerDeck.add(CardStorage.getCard("Steady Shot"));
		playerDeck.add(CardStorage.getCard("Steady Shot"));
		playerDeck.add(CardStorage.getCard("Steady Shot"));

		turns=new ArrayList<>();
		turns.add(new PlayerTurn(1));
		player=new BattlePlayer(50, 50, 50,5,5,5, playerDeck,"player-ranger");
		enemies=new ArrayList<>();
		minions=new ArrayList<>();
		enemies.add(new BattleEnemy("goblin-berserk",0,stageName));
		enemies.get(0).setUpAi(this);
		//enemies.add(new BattleEnemy("goblin-archer",1));
		//enemies.get(1).setUpAi(this);
		
		enemyEnties=new ArrayList<BattleEntity>();
		enemyEnties.add(enemies.get(0));
		//enemyEnties.add(enemies.get(1));
		enemyEnties.get(0).FillEmptyHand(7);
		//enemyEnties.get(1).FillEmptyHand(7);
	
		player.FillEmptyHand(4);
		enties=new ArrayList<>();
		enties.add(player);
		for(BattleEntity enemy:enemyEnties){
			enties.add(enemy);
		}
		gui=new BattleGUI(this);
	}

	@Override
	public void handleInput() {	
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			
			if(inPanel){
				if(!panelRect.Touched(mouse.x, mouse.y)){
					inPanel=false;
				}
				else
				selectedEnemy.panel.handleInput(mouse.x, mouse.y);
			}
			if(inDeck){
				if(!panelRect.Touched(mouse.x, mouse.y)){
					inDeck=false;
				}
				else
				deckPanel.handleInput(mouse.x, mouse.y);
			}
			
		if(isPlayersTurn()&&!gameFinished&&!inPanel){
		
			
			if(!waitingForTarget)
			for(BattleEntity enemy:enties){
				if(enemy.getIdleAnim().Touched(mouse.x, mouse.y) && enemy!=player){
					selectedEnemy=enemy;
					inPanel=true;
					break;
				}
			}
			
			if(gui.deck.Touched(mouse.x, mouse.y)){
				deckPanel=new DeckPanel(player.getDeck());
				inDeck=true;
				
			}
			//TODO SORT THIS OUT
			
			if(!waitingForTarget){
			if(gui.playerCards.selected&&!player.cardsInHand.isEmpty()){
				if(gui.selectButton.Touched(mouse.x, mouse.y)){
					if(!player.cardsInHand.isEmpty()
							&&gui.playerCards.getSelectedCard().CheckCost(player)
							&&gui.playerCards.getSelectedCard().CheckDarkPresence(DarkPresence)
							&&cardsPlayed<player.cardsPerTurn){
						if(gui.playerCards.getSelectedCard().needsTarget()=="none"){
					gui.PlayCard(player, player);
					if(!player.cardsInHand.isEmpty())
					gui.playerCards.selected=false;
					cardsPlayed++;
						}
						else if(gui.playerCards.getSelectedCard().needsTarget()=="all"){
							gui.PlayCard(player, this);
							if(!player.cardsInHand.isEmpty())
								gui.playerCards.selected=false;
							cardsPlayed++;
						}
						else{
							waitingForTarget=true;
						}
					}
				}
			}
			
			else{
				if(gui.endTurnButton.Touched(mouse.x, mouse.y)&&!gui.switchingTurnSigns){
					switchTurn();
					processAITurn();
				}
			}
			gui.handleInput(mouse.x, mouse.y);
			}
			else{
				chooseTarget(mouse);
			}
			//
		}
		else if(gameFinished){
			handleGameFinishedInput(mouse.x, mouse.y);
		}
		}
		
	}
	public void handleGameFinishedInput(float x, float y) {
		
		if(gui.VictoryScreen.Touched(x,y)&&playerWon){
			//removedEnemies.add(stageEnemy);
			encounterPlayer.setStats(player.getHp(), player.getSp(), player.getMp());
			gsm.set(new EncounterState(gsm,encounterPlayer));
			//gsm.set(new StageState(gsm, encounterPlayer, removedEnemies));
			//
		}
		else if(gui.DefeatScreen.Touched(x, y)&&!playerWon){
			gsm.set(new MainMenuState(gsm));
		
		}
		
	}
	private void chooseTarget(Vector3 mouse){
		if(gui.cancelButton.Touched(mouse.x, mouse.y)){
			waitingForTarget=false;
		}
		BattleEntity target=null;
		for(BattleEntity enemy:enemies){
			if(enemy.Touched(mouse.x, mouse.y)&&!enemy.isDead()){
				target=enemy;
				break;
			}
		}
		if(target!=null){
		gui.playerCards.getSelectedCard().Play(player, target);
		cardsPlayed++;
		if(!player.cardsInHand.isEmpty())
			gui.playerCards.selected=false;
		gui.playerCards.deleteSelectedCard();
		waitingForTarget=false;}
		
	}
	private void processAITurn(){
		
		for(BattleMinion minion:minions){
			if(!minion.getStunned()&&!minion.isDead())
				minion.getAi().MakeDecision();
			if(!minion.getAi().decided()&&!minion.isDead()&&!minion.getStunned()){
				minion.addMessageAnimation("pass", "red", 90);
			}
			else if(minion.hasNoPlay()&&!minion.isDead()&&!minion.getStunned()){
				minion.addMessageAnimation("outofcards", "red", 90);
			}
		}
		for(int i=0;i<minions.size();i++){
			if(!minions.get(i).getStunned()
					&&!minions.get(i).isDead()
					&&minions.get(i).getAi().decided()
					){
				minions.get(i).getAi().setAnimating(true);
				currentAIAnimatedNumberMinions=i;
				break;
			}
		}
		
		
		
		
		for(BattleEnemy enemy:enemies){
			if(!enemy.getStunned()&&!enemy.isDead())
			enemy.getAi().MakeDecision();
			if(!enemy.getAi().decided()&&!enemy.isDead()&&!enemy.getStunned()){
				enemy.addMessageAnimation("pass", "red", 90);
			}
			else if(enemy.hasNoPlay()&&!enemy.isDead()&&!enemy.getStunned()){
				enemy.addMessageAnimation("outofcards", "red", 90);
			}
		}
		for(int i=0;i<enemies.size();i++){
			if(!enemies.get(i).getStunned()
					&&!enemies.get(i).isDead()
					&&enemies.get(i).getAi().decided()
					){
				enemies.get(i).getAi().setAnimating(true);
				currentAIAnimatedNumber=i;
				break;
			}
			else if((i==enemies.size()-1&&(enemies.get(i).getStunned()
					||enemies.get(i).isDead()
					||!enemies.get(i).getAi().decided()
					))
					&&!alreadySwitchin
					){
				alreadySwitchin=true;
				Timer.schedule(new Task(){
					@Override
					public void run() {
						if(!gui.switchingTurnSigns){
							cardsPlayed=0;
							switchTurn();
						}
					
				//switchTurn();
						}
				}, 2);
			}
			}
		
		
		
		
		
		
		/*Timer.schedule(new Task(){
			@Override
			public void run() {
			
				turns.add(turns.get(turns.size()-1).EndTurn(enties,gui));
			}
		}, 5);
		*/
		
	}
	boolean alreadySwitchin=false;
	@Override
	public void update(float dt) {
		handleInput();
		
		gui.update(dt);
		player.update(dt);
		
		if(currentAIAnimatedNumberMinions<minions.size()){
		
		if(!isPlayersTurn()&& currentAIAnimatedNumberMinions<minions.size()){
			if(minions.get(currentAIAnimatedNumberMinions).getAi().isAnimating())
				minions.get(currentAIAnimatedNumberMinions).getAi().update(dt);
			else{
				currentAIAnimatedNumberMinions++;
				
				if(currentAIAnimatedNumberMinions<minions.size()
						&&!minions.get(currentAIAnimatedNumberMinions).getStunned()
								&&!minions.get(currentAIAnimatedNumberMinions).isDead()
								&&minions.get(currentAIAnimatedNumberMinions).getAi().decided()){
					minions.get(currentAIAnimatedNumberMinions).getAi().setAnimating(true);
				}
			}
		}
		}
		else
		if(!isPlayersTurn()&& currentAIAnimatedNumber<enemies.size()){
			if(enemies.get(currentAIAnimatedNumber).getAi().isAnimating())
			enemies.get(currentAIAnimatedNumber).getAi().update(dt);
			else{
				currentAIAnimatedNumber++;
				
				if(currentAIAnimatedNumber<enemies.size()
						&&!enemies.get(currentAIAnimatedNumber).getStunned()
								&&!enemies.get(currentAIAnimatedNumber).isDead()
								&&enemies.get(currentAIAnimatedNumber).getAi().decided()){
				enemies.get(currentAIAnimatedNumber).getAi().setAnimating(true);
				}
				else if(currentAIAnimatedNumber>=enemies.size()){
					if(!gui.switchingTurnSigns&&!alreadySwitchin){
						alreadySwitchin=true;
					Timer.schedule(new Task(){
					@Override
					public void run() {
					switchTurn();
							}
					}, 1);}
					//switchTurn();
				}
			}
		}
		for(BattleMinion minion:minions){
			minion.update(dt);
		}
		for(BattleEntity enemy:enemies){
			enemy.update(dt);
			
		}
		if(inPanel){
			selectedEnemy.panel.update(dt);
		}
		if(inDeck){
			deckPanel.update(dt);
		}
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.begin();
		
		gui.renderBackground(spriteBatch);
		for(BattleEntity ent:enties){
			ent.render(spriteBatch);
		}
		
		gui.render(spriteBatch,waitingForTarget);
		player.renderBars(spriteBatch);
		
		if(currentAIAnimatedNumberMinions<minions.size()){
			
		if(!isPlayersTurn()&&currentAIAnimatedNumberMinions<minions.size()){
			minions.get(currentAIAnimatedNumberMinions).getAi().render(spriteBatch);
		}
		}else
		if(!isPlayersTurn()&&currentAIAnimatedNumber<enemies.size()){
				enemies.get(currentAIAnimatedNumber).getAi().render(spriteBatch);
		}
		if(inPanel){
			selectedEnemy.panel.render(spriteBatch);
		}
		if(inDeck){
			deckPanel.render(spriteBatch);
		}
		
		spriteBatch.end();
	}
	
	private boolean isPlayersTurn(){
		return turns.get(turns.size()-1).getClass()==PlayerTurn.class;
	}
	private void switchTurn(){
		alreadySwitchin=false;
		turns.add(turns.get(turns.size()-1).EndTurn(enties,gui));
		gameFinished=turns.get(turns.size()-1).gameFinished;
		playerWon=turns.get(turns.size()-1).playerWon;
		cardsPlayed=0;
	}

	public boolean hasDeadEnemies() {
		for(BattleEntity enemy:enemies){
			if(enemy.isDead()){
				return true;
			}
		}
		return false;
	}
	public void increaseDP(int amount){
		DarkPresence+=amount;
		gui.updateDP();
	}
}
