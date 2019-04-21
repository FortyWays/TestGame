package com.stage.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.card.Card;
import com.battle.graphics.Animation;
import com.battle.graphics.CustomFont;
import com.battle.graphics.StatBar;
import com.fortyways.dns.DnS;
import com.fortyways.storages.BattleEntityStorage;
import com.fortyways.storages.SpriteStorage;
import com.fortyways.storages.StageStorage;
import com.fortyways.util.Graphic;

public class InfoPanel {

	private String entityName;
	private Graphic panel;
	private Animation idleAnim;
	private TextureRegion background;
	private ArrayList<Graphic> cards;
	private ArrayList<Card> deck;
	private CustomFont nameTag;
	private StatBar hpBar;
	private StatBar spBar;
	private StatBar mpBar;
	private CustomFont regenHp;
	private CustomFont regenSp;
	private CustomFont regenMp;
	
	private int selnum=0;
	public Graphic descArt;
	private BitmapFont font=new BitmapFont();
	public boolean selected=false;
	public InfoPanel(String entityName,String stageName) {
		this.entityName=entityName;
		panel=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2, 
				DnS.res.getAtlas("pack").findRegion("Panel1").getRegionWidth()*5,
				DnS.res.getAtlas("pack").findRegion("Panel1").getRegionHeight()*5,
				DnS.res.getAtlas("pack").findRegion("Panel1"));
		
		background=StageStorage.getBackground(stageName);
		cards=new ArrayList<>();
		nameTag=new CustomFont(entityName, DnS.WIDTH/2, DnS.HEIGHT/2+100, false);
		hpBar=new StatBar(DnS.WIDTH/2-70, DnS.HEIGHT/2+110,
				BattleEntityStorage.getStats(entityName)[0], "hp2");
		spBar=new StatBar(DnS.WIDTH/2-70, DnS.HEIGHT/2+80,
				BattleEntityStorage.getStats(entityName)[1], "sp2");
		mpBar=new StatBar(DnS.WIDTH/2-70, DnS.HEIGHT/2+50,
				BattleEntityStorage.getStats(entityName)[2], "mp2");
		regenHp=new CustomFont
				("+"+BattleEntityStorage.getRegens(entityName)[0]+" perturnred",
				DnS.WIDTH/2-20, DnS.HEIGHT/2+110, "red");
		regenSp=new CustomFont
				("+"+BattleEntityStorage.getRegens(entityName)[1]+" perturngreen",
				DnS.WIDTH/2-20, DnS.HEIGHT/2+80, "green");
		regenMp=new CustomFont
				("+"+BattleEntityStorage.getRegens(entityName)[2]+" perturnblue",
				DnS.WIDTH/2-20, DnS.HEIGHT/2+50, "blue");
		ArrayList<Card> deck=BattleEntityStorage.getDeck(entityName);
		
		ArrayList<Card> sortedDeck=new ArrayList<>();
		for(Card card:deck){
			if(!sortedDeck.contains(card)){
				sortedDeck.add(card);
			}
		}
		this.deck=sortedDeck;
		for(int i=0;i<sortedDeck.size();i++){
			cards.add(new Graphic(DnS.WIDTH/2-180+i*60, DnS.HEIGHT/2-80,
					sortedDeck.get(i).cardArt));
		}
		idleAnim = new Animation( DnS.WIDTH/2-180, DnS.HEIGHT/2+85,
				SpriteStorage.getFrameProportions(entityName)[0]*2,
				SpriteStorage.getFrameProportions(entityName)[1]*2,
				SpriteStorage.getIdleAnimationSpriteSheet(entityName)
				,20, SpriteStorage.getFrameProportions(entityName)[0]
				,SpriteStorage.getFrameProportions(entityName)[1]);

		selected=false;
	}
	public void handleInput(float x,float y){
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
		idleAnim.update(dt);
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
		else if(cards.size()>0){
			
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
	public void updateBars(int hp,int sp,int mp){
		hpBar.UpdateBar(hp);
		spBar.UpdateBar(sp);
		mpBar.UpdateBar(mp);
	}
	
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(background, DnS.WIDTH/2-220, DnS.HEIGHT/2+22, 200, 120);
		idleAnim.render(spriteBatch);
		panel.render(spriteBatch);
		hpBar.render(spriteBatch);
		spBar.render(spriteBatch);
		mpBar.render(spriteBatch);
		regenHp.render(spriteBatch);
		regenSp.render(spriteBatch);
		regenMp.render(spriteBatch);
		//nameTag.render(spriteBatch);
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
