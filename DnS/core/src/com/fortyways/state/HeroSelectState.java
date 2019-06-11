package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.Animation;
import com.battle.graphics.PlayerCards;
import com.encounter.EncounterPlayer;
import com.fortyways.dns.DnS;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.util.Graphic;
import com.fortyways.util.StageToBattleTransfer;
import com.stage.graphics.DeckPanel;

public class HeroSelectState extends State{

	private Animation warriorAnim;
	private Animation rangerAnim;
	private Graphic warriorSign;
	private Graphic rangerSign;
	private PlayerCards deckPanel;
	public HeroSelectState(GSM gsm) {
		super(gsm);
		String name="player-warrior";
		warriorAnim=new Animation(DnS.WIDTH/2+80, DnS.HEIGHT/2,
				SpriteStorage.getFrameProportions(name)[0]*5,
				SpriteStorage.getFrameProportions(name)[1]*5,
				SpriteStorage.getIdleAnimationSpriteSheet(name)
				,20, SpriteStorage.getFrameProportions(name)[0]
				,SpriteStorage.getFrameProportions(name)[1]);
		name="player-ranger";
		rangerAnim=new Animation(DnS.WIDTH/2-80, DnS.HEIGHT/2,
				SpriteStorage.getFrameProportions(name)[0]*5,
				SpriteStorage.getFrameProportions(name)[1]*5,
				SpriteStorage.getIdleAnimationSpriteSheet(name)
				,20, SpriteStorage.getFrameProportions(name)[0]
				,SpriteStorage.getFrameProportions(name)[1]);
		warriorSign=new Graphic(warriorAnim.x, warriorAnim.y+70, 37*3, 13*3, 
				DnS.res.getAtlas("pack").findRegion("WarriorSign"));
		rangerSign=new Graphic(rangerAnim.x, rangerAnim.y+70, 37*3, 13*3, 
				DnS.res.getAtlas("pack").findRegion("RangerSign"));
		deckPanel=new PlayerCards(StageToBattleTransfer.
				trasferPlayer(new EncounterPlayer("player-warrior")).getDeck());
	}

	@Override
	public void handleInput() {
		if(Gdx.input.isTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			deckPanel.handleInput(mouse.x, mouse.y);
			if(warriorSign.Touched(mouse.x, mouse.y)){
				//gsm.set(new TransitionFadeState(gsm, this, new StageState(gsm,"player-warrior")));
			}
			else if(rangerSign.Touched(mouse.x, mouse.y)){
				//gsm.set(new TransitionFadeState(gsm, this, new StageState(gsm,"player-ranger")));
			}
		}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		rangerAnim.update(dt);
		warriorAnim.update(dt);
		deckPanel.update(dt);
	}

	@Override
	public void render(SpriteBatch sb) {
		//Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		//rangerAnim.render(sb);
		deckPanel.render(sb);
		warriorAnim.render(sb);
		warriorSign.render(sb);
		//rangerSign.render(sb);
		sb.end();
	}

}
