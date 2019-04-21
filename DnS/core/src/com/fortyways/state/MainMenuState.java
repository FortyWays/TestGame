package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.CustomFont;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;
import com.stage.graphics.InfoPanel;
import com.stage.world.WorldBuilder;

public class MainMenuState extends State{

	Graphic PlayButton;
	Graphic TutorialButton;
	BitmapFont font=new BitmapFont();
	Graphic Background;
	Graphic Logo;
	CustomFont cf;
	public MainMenuState(GSM gsm) {
		super(gsm);
		cf=new CustomFont("fortyways", DnS.WIDTH/2-20, 50,true);
		PlayButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2,
				DnS.res.getAtlas("pack").findRegion("PlayButton"));
		PlayButton.width*=4;
		PlayButton.height*=4;
		Logo=new Graphic(DnS.WIDTH/2, DnS.HEIGHT-100, DnS.res.getAtlas("pack").findRegion("tempLogo"));
		Logo.width*=4;
		Logo.height*=4;
		
		TutorialButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2-PlayButton.height, 
				DnS.res.getAtlas("pack").findRegion("TutorialButton"));
		TutorialButton.width*=4;
		TutorialButton.height*=4;
		Background=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2-300,DnS.WIDTH*4,DnS.HEIGHT*4,
				DnS.res.getAtlas("pack").findRegion("Background"));
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			mouse.x=Gdx.input.getX();
			mouse.y=Gdx.input.getY();
			cam.unproject(mouse);
			if(PlayButton.Touched(mouse.x, mouse.y)){
				gsm.set(new TransitionFadeState(gsm, this, new HeroSelectState(gsm)));
			}
		}
		
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		cf.update(dt);
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		Background.render(sb);
		Logo.render(sb);
		PlayButton.render(sb);
		TutorialButton.render(sb);
		font.setColor(Color.RED);
		font.draw(sb, "Tutorial not yet available.\nMostly cause gamesux", DnS.WIDTH/2-60, 145);
		font.setColor(Color.WHITE);
		cf.render(sb);
		//font.draw(sb, "FortyWays", DnS.WIDTH/2-20, 50);
		//font.draw(sb, "v0.1", DnS.WIDTH/2-20, 30);
		sb.end();
	}

}
