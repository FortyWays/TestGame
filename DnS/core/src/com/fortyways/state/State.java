package com.fortyways.state;



import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.fortyways.dns.DnS;



public abstract class State {
	protected GSM gsm;
	protected OrthographicCamera cam;
	protected Vector3 mouse;
	
	public State(GSM gsm){
		this.gsm=gsm;
		cam=new OrthographicCamera();
		cam.setToOrtho(false, DnS.WIDTH, DnS.HEIGHT);
		mouse=new Vector3();
	}
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	
}
