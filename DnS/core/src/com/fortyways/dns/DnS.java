package com.fortyways.dns;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.state.BattleState;
import com.fortyways.state.GSM;
import com.fortyways.state.HeroSelectState;
import com.fortyways.state.MainMenuState;
import com.fortyways.state.StageState;
import com.fortyways.storages.BattleEntityStorage;
import com.fortyways.storages.CardStorage;
import com.fortyways.storages.ItemStorage;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.storages.StageStorage;
import com.fortyways.util.Content;

public class DnS extends ApplicationAdapter {
	SpriteBatch sb;
	
	public static int WIDTH = 800;
	public static int HEIGHT = 480;
	public static Content res;
	private GSM gsm;
	@Override
	public void create () {
		res=new Content();
		res.loadAtlas("pack.pack", "pack");
		
		gsm=new GSM();
		sb = new SpriteBatch();
		
		CardStorage.init();
		SpriteStorage.init();
		BattleEntityStorage.init();
		StageStorage.init();
		ItemStorage.init();
		//gsm.set(new StageState(gsm));
		gsm.set(new MainMenuState(gsm));
		//gsm.set(new BattleState(gsm));
		//gsm.set(new HeroSelectState(gsm));
		
	}

	@Override
	public void render () {
		
		gsm.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		gsm.render(sb);
		
	}
}
