package com.stage.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;
import com.battle.graphics.Animation;
import com.battle.graphics.CustomFont;
import com.battle.graphics.StatBar;
import com.fortyways.dns.DnS;
import com.fortyways.storages.BattleEntityStorage;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.storages.StageStorage;
import com.fortyways.util.Graphic;

public class DeckPanel {
	private Graphic panel;
	private ArrayList<Graphic> cards;
	private ArrayList<Card> deck;
	public Graphic descArt;
	private BitmapFont font=new BitmapFont();
	private int selnum=0;
	public boolean selected=false;
	
	public DeckPanel(ArrayList<Card> playerDeck) {
		panel=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2, 
				DnS.res.getAtlas("pack").findRegion("Panel2").getRegionWidth()*5,
				DnS.res.getAtlas("pack").findRegion("Panel2").getRegionHeight()*5,
				DnS.res.getAtlas("pack").findRegion("Panel2"));
		
		deck=playerDeck;
		cards=new ArrayList<>();
		for(int i=0;i<deck.size();i++){
			
			cards.add(new Graphic(DnS.WIDTH/2-180+(i%7)*60, getHeightForCardGraphic(i),
					deck.get(i).cardArt));
	
		
		}
	}
	public void handleInput(float x,float y){
		for(int i=0;i<cards.size();i++){
			Graphic gr=cards.get(i);
			if(gr.Touched(x, y)){
				if(cards.get(selnum).y==getHeightForCardGraphic(selnum)){
				selnum=i;
				descArt=new Graphic(gr.x, gr.y, gr.width, gr.height, 
						DnS.res.getAtlas("pack").findRegion("DescriptionArt"));
				selected=true;
				}
				break;
			}
			if(i==cards.size()-1){
				selected=false;
			}
		}
	}
	public void update(float dt){
	
		if(selected&&cards.size()>0){
			Graphic gr=cards.get(selnum);
			if(gr.y<getHeightForCardGraphic(selnum)+40){
				gr.y+=100*0.1;
				descArt.y+=100*0.1;
			}
			
			if(gr.height<DnS.HEIGHT/2+10){
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
			if(gr.y>getHeightForCardGraphic(selnum)){
				gr.y-=100*0.1;
				descArt.y-=100*0.1;
			}
			if(gr.height>80){
				gr.width-=100*0.1;
				gr.height-=160*0.1;
				descArt.width-=100*0.1;
				descArt.height-=160*0.1;
			}
		}
	}
	
	public void render(SpriteBatch spriteBatch){
		panel.render(spriteBatch);
		
		for(Graphic gr:cards){
			if(gr!=null){
				
				
			if(cards.indexOf(gr)!=selnum)
			gr.render(spriteBatch);
			
		}
		}
		if(selected&&descArt!=null&&cards.size()>0){
			descArt.render(spriteBatch);
		}
		if(selected && descArt!=null &&descArt.x>cards.get(selnum).x){
			font.setColor(Color.BLACK);
			font.draw(spriteBatch, deck.get(selnum).description, descArt.x-60, descArt.y+80);
		}
		cards.get(selnum).render(spriteBatch);
		
	}
	public float getHeightForCardGraphic(int num){
		
		return DnS.HEIGHT/2+100-(DnS.res.getAtlas("pack").findRegion("Panel2").getRegionHeight()*1.5f*(num/7));
	}
}
