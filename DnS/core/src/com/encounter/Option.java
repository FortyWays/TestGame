package com.encounter;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;
import com.stage.items.Item;

public class Option {
	private int number;
	public Graphic background;
	private String text;
	public String needClass="none";
	public boolean dprequired=false;
	public boolean famerequired=false;
	public boolean itemrequired=false;
	
	
	public int dp;
	public int fame;
	public Item item;
	BitmapFont font=new BitmapFont();
	public Option(String text,int number) {
		this.text=text;
		this.number=number;
		background=new Graphic(
			DnS.WIDTH/2, 
			DnS.HEIGHT/2-50*(number+1),
			DnS.res.getAtlas("pack").findRegion("Option").getRegionWidth()*2,
			DnS.res.getAtlas("pack").findRegion("Option").getRegionHeight()*2,
			DnS.res.getAtlas("pack").findRegion("Option"));}
	
	ArrayList<Outcome> outcomes;
	
	
	public void render(SpriteBatch sb){
		background.render(sb);
		font.draw(sb, text, 290, background.y);
	}
	
	public void setOutcomes(ArrayList<Outcome> outcomes) {
		this.outcomes = outcomes;
	}
	public Outcome receiveOutcome(){
		Random r=new Random();
		int num=r.nextInt(outcomes.size());
		Outcome res=outcomes.get(num);
		res.setUpCards();
		return res;
	}
}
