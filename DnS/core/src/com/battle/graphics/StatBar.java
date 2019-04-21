package com.battle.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;

public class StatBar {
//HE DID IT
//THE MADMAN
	//ADMIN HES DOING IT SIDEWAYS
	private Graphic EmptyBar;
	private TextureRegion Bar;
	private int max;
	private int current;
	private float width;
	public float height;
	private float percent;
	private CustomFont font;
	//private BitmapFont font=new BitmapFont();
	public StatBar(float x,float y,int max,String type) {
		this.max=max;
		this.current=max;
		font=new CustomFont(current+"/"+max, x-20, y,10,10, false);
		
		EmptyBar=new Graphic(x,y,
				DnS.res.getAtlas("pack").findRegion("ProgressBarEmpty").getRegionWidth()*2,
				DnS.res.getAtlas("pack").findRegion("ProgressBarEmpty").getRegionHeight()*3,
				DnS.res.getAtlas("pack").findRegion("ProgressBarEmpty"));
		height=EmptyBar.height;
		if(type=="hp"){
		Bar=new TextureRegion(DnS.res.getAtlas("pack").findRegion("RedBar"));
		}
		else if(type=="sp"){
			Bar=new TextureRegion(DnS.res.getAtlas("pack").findRegion("GreenBar"));	
		}
		else if(type=="mp"){
			Bar=new TextureRegion(DnS.res.getAtlas("pack").findRegion("BlueBar"));
			}
		else if(type=="hp2"){
			EmptyBar=new Graphic(x,y,
					DnS.res.getAtlas("pack").findRegion("HealthBar").getRegionWidth(),
					DnS.res.getAtlas("pack").findRegion("HealthBar").getRegionHeight(),
					DnS.res.getAtlas("pack").findRegion("HealthBar"));
			Bar=new TextureRegion(DnS.res.getAtlas("pack").findRegion("RedBar2"));
		}
		else if(type=="sp2"){
			EmptyBar=new Graphic(x,y,
					DnS.res.getAtlas("pack").findRegion("StaminaBar").getRegionWidth(),
					DnS.res.getAtlas("pack").findRegion("StaminaBar").getRegionHeight(),
					DnS.res.getAtlas("pack").findRegion("StaminaBar"));
			Bar=new TextureRegion(DnS.res.getAtlas("pack").findRegion("GreenBar2"));
		}
		else if(type=="mp2"){
			EmptyBar=new Graphic(x,y,
					DnS.res.getAtlas("pack").findRegion("ManaBar").getRegionWidth(),
					DnS.res.getAtlas("pack").findRegion("ManaBar").getRegionHeight(),
					DnS.res.getAtlas("pack").findRegion("ManaBar"));
			Bar=new TextureRegion(DnS.res.getAtlas("pack").findRegion("BlueBar2"));
		}
		width=EmptyBar.width;
		percent=1;
	}
	
	public void UpdateBar(int newcurrent){
		this.current=newcurrent;
		percent=newcurrent*1f/max*1f;
		font=new CustomFont(current+"/"+max, EmptyBar.x-20, EmptyBar.y,10,10, false);
		//Bar.x-=(Bar.width-Bar.width*percent)/2f;
		
	}
	
	
	public void render(SpriteBatch spriteBatch){
		EmptyBar.render(spriteBatch);
		//Bar.render(spriteBatch);
		if(Bar.getRegionWidth()>41)
		spriteBatch.draw(Bar, 
				EmptyBar.x-width/2+26*(1-percent), EmptyBar.y-height/2, 0, 0, width, height, percent, 1, 0);
		else{
			spriteBatch.draw(Bar, 
					EmptyBar.x-width/2+6*(1-percent), EmptyBar.y-height/2, 0, 0, width, height, percent, 1, 0);
			
		}
		//font.setColor(Color.WHITE);
		//font.getData().setScale(1f);
		font.render(spriteBatch);
		//font.draw(spriteBatch, current+"/"+max, EmptyBar.x-20, EmptyBar.y+5);
		
	}
	
	
}
