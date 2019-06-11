package com.encounter;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.CustomFont;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;
import com.stage.items.Item;

public class Option {
	private int number;
	public Graphic background;
	public Graphic costIcon;
	private String text;
	public String needClass="none";
	public boolean dprequired=false;
	public boolean famerequired=false;
	public boolean itemrequired=false;
	private int requiredMoney=0;
	private int requiredFood=0;
	private CustomFont customFont;
	
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
		if(requiredMoney>0||requiredFood>0){
			costIcon.render(sb);
			customFont.render(sb);
		}
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
	public void setRequiredMoney(int requiredMoney) {
		this.requiredMoney = requiredMoney;
		costIcon=new Graphic(250, background.y, 
				DnS.res.getAtlas("pack").findRegion("Money").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("Money").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("Money"));
		customFont=new CustomFont(Integer.toString(requiredMoney), 235, background.y, "yellow");
	}
	public void setRequiredFood(int requiredFood) {
		this.requiredFood = requiredFood;
		costIcon=new Graphic(250, background.y, 
				DnS.res.getAtlas("pack").findRegion("Food").getRegionWidth(),
				DnS.res.getAtlas("pack").findRegion("Food").getRegionHeight(),
				DnS.res.getAtlas("pack").findRegion("Food"));
		customFont=new CustomFont(Integer.toString(requiredFood), 235, background.y, "green");
	}
	public boolean requiresMoney(){
		if (requiredMoney>0)
		return true;
		else
		return false;
				
	}
	public boolean requiresFood(){
		if (requiredFood>0)
		return true;
		else
		return false;
				
	}
	public int getRequiredFood() {
		return requiredFood;
	}
	public int getRequiredMoney() {
		return requiredMoney;
	}
}
