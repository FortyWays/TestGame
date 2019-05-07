package com.stage.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.card.Card;
import com.battle.graphics.CustomFont;
import com.fortyways.dns.DnS;
import com.fortyways.storages.ItemStorage;
import com.fortyways.util.Graphic;
import com.stage.items.Item;

public class ItemPanel {


	private Item item;
	private Graphic panel;
	private Graphic sprite;
	private CustomFont name;
	private CustomFont description;
	private TextureRegion background;
	private ArrayList<Card> deck;
	private ArrayList<Graphic> cards;
	private int selnum=0;

	public Graphic descArt;
	private BitmapFont font=new BitmapFont();
	public boolean selected=false;
	public ItemPanel(String itemName) {
		this.item=ItemStorage.getItem(itemName);
		panel=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2, 
				DnS.res.getAtlas("pack").findRegion("Panel1").getRegionWidth()*5,
				DnS.res.getAtlas("pack").findRegion("Panel1").getRegionHeight()*5,
				DnS.res.getAtlas("pack").findRegion("Panel1"));
		background=DnS.res.getAtlas("pack").findRegion("Background1");
		sprite=new Graphic(DnS.WIDTH/2-180, DnS.HEIGHT/2+85,40,40, item.getSprite());
		name=new CustomFont(" "+item.getName(), DnS.WIDTH/2-30, DnS.HEIGHT/2+60, false);
		description=new CustomFont(" "+item.getDescription(), DnS.WIDTH/2-60, DnS.HEIGHT/2+40, false);
		if(!item.getCards().isEmpty()){
			cards=new ArrayList<>();
		ArrayList<Card> sortedDeck=new ArrayList<>();
		for(Card card:item.getCards()){
			if(!sortedDeck.contains(card)){
				sortedDeck.add(card);
			}
		}
		this.deck=sortedDeck;
		for(int i=0;i<sortedDeck.size();i++){
			cards.add(new Graphic(DnS.WIDTH/2-180+i*60, DnS.HEIGHT/2-80,
					sortedDeck.get(i).cardArt));
		}
		}
	}
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(background, DnS.WIDTH/2-220, DnS.HEIGHT/2+22, 200, 120);
		sprite.render(spriteBatch);
		panel.render(spriteBatch);
		name.render(spriteBatch);
		description.render(spriteBatch);
		if(!item.getCards().isEmpty()){
		if(selected&&descArt!=null&&cards.size()>0){
			descArt.render(spriteBatch);
		}
		for(Graphic gr:cards){
			if(gr!=null){
				if(cards.indexOf(gr)==selnum && selected && descArt!=null &&descArt.x>gr.x){
				font.setColor(Color.BLACK);
				font.draw(spriteBatch, deck.get(selnum).description, descArt.x-60, descArt.y+80);
			}
			gr.render(spriteBatch);
		}
		}
		}
	
	}
	public void handleInput(float x,float y){
		if(!item.getCards().isEmpty())
		for(int i=0;i<cards.size();i++){
			Graphic gr=cards.get(i);
			if(gr.Touched(x, y)){
				if(cards.get(selnum).y==DnS.HEIGHT/2-80){
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
			if(gr.y<DnS.HEIGHT/2+70){
				gr.y+=100*0.1;
				descArt.y+=100*0.1;
			}
			
			if(gr.height<DnS.HEIGHT/2-30){
				gr.width+=100*0.1;
				gr.height+=160*0.1;
				descArt.width+=100*0.1;
				descArt.height+=160*0.1;
			}
			else if(descArt.x<gr.x+descArt.width){
				descArt.x+=100*0.1;
			}
		}
		else if(cards!=null&&cards.size()>0){
			
			Graphic gr=cards.get(selnum);
			if(gr.y>DnS.HEIGHT/2-80){
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
}
