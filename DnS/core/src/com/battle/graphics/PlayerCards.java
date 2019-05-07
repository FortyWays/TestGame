package com.battle.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;
import com.battle.player.BattleEntity;
import com.battle.player.BattlePlayer;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;

public class PlayerCards {
	
	private Graphic hpIcon,spIcon,mpIcon;
	private BitmapFont font=new BitmapFont();
	public ArrayList<Graphic> cards;
	public Graphic descArt;
	public ArrayList<Card> cardsinhand;
	
	public boolean selected;
	public int selnum=0;
	public PlayerCards(ArrayList<Card> cardsinhand) {
		/*hpIcon.width*=4;
		hpIcon.height*=4;
		hpIcon=new Graphic(0, 0, DnS.res.getAtlas("pack").findRegion("Health"));
		hpIcon.width*=2;
		hpIcon.height*=2;
		spIcon=new Graphic(0, 0, DnS.res.getAtlas("pack").findRegion("Stamina"));
		spIcon.width*=2;
		spIcon.height*=2;
		mpIcon=new Graphic(0, 0, DnS.res.getAtlas("pack").findRegion("Mana"));
		mpIcon.width*=2;
		mpIcon.height*=2;
		*/
		this.cardsinhand=cardsinhand;
		/*for(int i=0;i<cardsinhand.size();i++){
			this.cardsinhand.add(cardsinhand.get(i));
		}*/
		
		cards=new ArrayList<>();
		for (int i=0;i<cardsinhand.size();i++) {
			Card card=cardsinhand.get(i);
			cards.add(new Graphic(i*60+240, 50,
					card.cardArt.getRegionWidth(),card.cardArt.getRegionHeight(),card.cardArt));
		}
		selected=false;
	}
	public void handleInput(float x,float y){
		//
		for(int i=0;i<cards.size();i++){
			Graphic gr=cards.get(i);
			if(gr.Touched(x, y)){
				if(cards.get(selnum).y==50){
				selnum=i;
				descArt=new Graphic(gr.x, gr.y, gr.width, gr.height, 
						DnS.res.getAtlas("pack").findRegion("DescriptionArt"));
				selected=true;

				/*
				hpIcon.x=cards.get(selnum).x-10;
				hpIcon.y=DnS.HEIGHT/2+140;
				spIcon.x=cards.get(selnum).x-15;
				spIcon.y=DnS.HEIGHT/2+140;
				mpIcon.x=cards.get(selnum).x-10;
				mpIcon.y=DnS.HEIGHT/2+140;*/
				}
				break;
			}
			if(i==cards.size()-1){
				selected=false;
			}
		}
	}
	public void update(float dt){
		PlayerCardsAnimation.update(dt, this);
		if(selected&&cards.size()>0){
			Graphic gr=cards.get(selnum);
			if(gr.y<DnS.HEIGHT/2){
				gr.y+=100*0.1;
				descArt.y+=100*0.1;
			}
			
			if(gr.height<DnS.HEIGHT/2){
				gr.width+=100*0.1;
				gr.height+=160*0.1;
				descArt.width+=100*0.1;
				descArt.height+=160*0.1;
			}
			else if(descArt.x<gr.x+descArt.width){
				descArt.x+=100*0.1;
			}
		}
		else if(cards.size()>0){
			
			Graphic gr=cards.get(selnum);
			if(gr.y>50){
				gr.y-=100*0.1;
				descArt.y-=100*0.1;
			}
			if(gr.height>cards.get(0).image.getRegionHeight()){
				gr.width-=100*0.1;
				gr.height-=160*0.1;
				descArt.width-=100*0.1;
				descArt.height-=160*0.1;
			}
		}
	}
	public void render(SpriteBatch sb){
		/*
		if(selected){
			if(cardsinhand.get(selnum).spcost>0){
				spIcon.render(sb);
				font.setColor(Color.GREEN);
				font.draw(sb,Integer.toString(cardsinhand.get(selnum).spcost)
						, spIcon.x+15,  spIcon.y);
				
			}
			else if(cardsinhand.get(selnum).mpcost>0){
				mpIcon.render(sb);
				font.setColor(Color.BLUE);
				
				font.draw(sb,Integer.toString(cardsinhand.get(selnum).mpcost)
						, mpIcon.x+15,  mpIcon.y);
				
			}
			
		}*/
		if(selected&&descArt!=null&&cards.size()>0){
			descArt.render(sb);
		}
		for (Graphic graphic : cards) {
			if(graphic!=null){
				if(cards.indexOf(graphic)==selnum && selected && descArt!=null &&descArt.x>graphic.x){
				font.setColor(Color.BLACK);
				font.draw(sb, cardsinhand.get(selnum).description, descArt.x-60, descArt.y+80);
			}
			graphic.render(sb);
			
			}
		}
	}
	public Card getSelectedCard(){
		return cardsinhand.get(selnum);
	}
	
	public void checkForNewCards(BattleEntity player){
		
		if(player.drewThisTurn){
			//System.out.println("goten");
			PlayerCardsAnimation.startAnimation(player, this);
			//player.drewThisTurn=false;
		}
	}
	
	public void deleteSelectedCard(){
		cards.remove(selnum);
		cardsinhand.remove(selnum);
		
		selnum=0;
	}
	
}
