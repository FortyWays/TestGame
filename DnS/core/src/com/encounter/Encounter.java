package com.encounter;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;

public class Encounter {
	private boolean finished=false;
	public boolean fightended=false;
	public boolean chosen=false;
	
	private String name;
	private Graphic slide;
	private String text;
	private ArrayList<Option> options;
	public Option chosenOption;
	public Outcome chosenOutcome;
	public Option continueOption;
	BitmapFont font=new BitmapFont();
	public Encounter(String name,TextureRegion image,String text) {
		slide=new Graphic(
				DnS.WIDTH/2, 
				DnS.HEIGHT/2,
				image.getRegionWidth()*2,
				image.getRegionHeight()*2,
				image);
		this.name=name;
		this.text=text;
		continueOption=new Option("Continue your journey", 0);
	}
	
	public Encounter() {
		// TODO Auto-generated constructor stub
	}
	 public Encounter(Encounter another) {
		    this.slide = another.slide;
		    this.text=another.text;
		    this.finished=another.finished;
		    this.fightended=another.fightended;
		    this.chosen=another.chosen;
		    this.name=another.name;
		    this.options=another.options;
		    this.chosenOption=another.chosenOption;
		    this.chosenOutcome=another.chosenOutcome;
		    this.continueOption=another.continueOption;
		    this.font=another.font;
	 }
	public void handleInput(float x,float y,EncounterPlayer player){
		for(Option option: options){
				if(option.background.Touched(x, y)
						&&player.getFood()>=option.getRequiredFood()
						&&player.getMoney()>=option.getRequiredMoney()){
					chosen=true;
					chosenOption=option;
					player.food-=option.getRequiredFood();
					player.money-=option.getRequiredMoney();
				}
		}
	}
	
	public void render(SpriteBatch sb){
		if(!finished){
		slide.render(sb);
		if(chosenOutcome==null){
		font.draw(sb, text, 290, DnS.HEIGHT/2+50);
		for(Option option: options){
			option.render(sb);
		}
		}
		else{
		if((fightended&&chosenOutcome.startBattle)||!chosenOutcome.startBattle){
		continueOption.render(sb);
		font.draw(sb, chosenOutcome.getText(), 290, DnS.HEIGHT/2+50);
		chosenOutcome.render(sb);	
		}
		}
		}
	}
	public void setChosenOutcome(Outcome chosenOutcome) {
		this.chosenOutcome = chosenOutcome;
		chosenOutcome.setUpCards();
	}
	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public void update(float dt) {
		if(chosenOutcome!=null)
		chosenOutcome.update(dt);
		
	}

}
