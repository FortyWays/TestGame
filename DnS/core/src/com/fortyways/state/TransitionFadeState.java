package com.fortyways.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TransitionFadeState extends State{
	State s1,s2;
	float timer=2f;
	float mtime=2f;
	protected TransitionFadeState(GSM gsm,State s1,State s2) {
		super(gsm);
		
		this.s1=s1;
		this.s2=s2;
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update(float dt) {
		if(timer>0){
			timer-=mtime/100;
			
		}
		else{
			gsm.set(s2);
		}
		if(timer>=mtime/2){
		//	s1.update(dt);
		}
		else{
			s2.update(dt);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
	//	sb.begin();
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		if(timer>=mtime/2){
		
		sb.setColor(1-(2-timer) ,1-(2-timer), 1-(2-timer),1);
		s1.render(sb);
		sb.setColor(1, 1, 1, 1);}
		else{
		if(1-timer>1){
			sb.setColor(1, 1, 1,1);
		}
		else
		sb.setColor(1-(timer), 1-(timer), 1-(timer),1);
		s2.render(sb);
		sb.setColor(1, 1, 1, 1);}
	}

}