package com.fortyways.dns;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.CardStorage;
import com.fortyways.state.BattleState;
import com.fortyways.state.GSM;
import com.fortyways.util.Content;

public class DnS extends ApplicationAdapter {
	SpriteBatch sb;
	
	public static int WIDTH = 480;
	public static int HEIGHT = 800;
	public static Content res;
	private GSM gsm;
	@Override
	public void create () {
		res=new Content();
		res.loadAtlas("pack.pack", "pack");
		
		gsm=new GSM();
		sb = new SpriteBatch();
		
		CardStorage.init();
		gsm.set(new BattleState(gsm));
		
		
	}

	@Override
	public void render () {

		gsm.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		gsm.render(sb);
		
	}
}
