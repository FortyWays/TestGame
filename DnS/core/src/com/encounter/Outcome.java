package com.encounter;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.FadingAnimation;
import com.battle.player.BattleEnemy;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;
import com.stage.items.Item;

public class Outcome {

	public boolean startBattle=false;
	public int hpgain=0;
	public int spgain=0;
	public int mpgain=0;
	public int dpgain=0;
	public int moneygain=0;
	public int foodgain=0;
	public int famegain=0;
	public boolean itemgain;
	BitmapFont font =new BitmapFont();
	public boolean showingResult=false;
	public Item item;
	public ArrayList<Graphic >cards=new ArrayList<>();
	public ArrayList<FadingAnimation>anims=new ArrayList<>();
	public ArrayList<String> enemyTags;
	private String text;
	
	public Outcome(String text) {
		this.setText(text);
	}
	
	public Outcome(String text,ArrayList<String> enemyTags) {
		this.startBattle=true;
		this.setText(text);
		this.enemyTags=enemyTags;
	}
	public Outcome(String text,Item item){
		this.itemgain=true;
		this.item=item;
		item.setRelativeY(DnS.HEIGHT/2-50);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	float alpha=1;
	public void update(float dt){
		//System.out.println("updating");
		for(Graphic card: cards){
		if(card!=null){
			if(card.width<card.image.getRegionWidth()){
				card.width+=1;
				card.height+=1;
				card.y+=0.5;
				if(itemgain){
					
					//item.setRelativeY(item.getRelativeY()+0.5f);
				}
			}
			else if(card.width<card.image.getRegionWidth()*3){
				if(alpha>0)
				alpha-=0.01f;
				card.width+=1;
				card.height+=1;
				card.y+=0.5;
				if(itemgain){
					//item.setRelativeY(item.getRelativeY()+1);
				}
			}
		}
		}
		for(FadingAnimation fa:anims){
			fa.update(dt);
		}
	}
	public void render(SpriteBatch sb){
		//System.out.println("rendering");
		for(Graphic card: cards){
		//	sb.setColor(1, 1, 1, alpha);
			card.render(sb);
		//	sb.setColor(1, 1, 1, 1);
		}
		for(FadingAnimation fa: anims){
			fa.render(sb);
		}
	}
	public void setUpCards(){
		cards.clear();
		anims.clear();
		alpha=1;
		if(hpgain!=0){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("HealthCard")));
			anims.add(new FadingAnimation(Integer.toString(hpgain), "red", 100+100*anims.size(), DnS.HEIGHT/2-70,true,false));
		}
		if(spgain!=0){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("StaminaCard")));
			anims.add(new FadingAnimation(Integer.toString(spgain), "green", 100+100*anims.size(), DnS.HEIGHT/2-70,true,false));
		}
		if(mpgain!=0){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("ManaCard")));
			anims.add(new FadingAnimation(Integer.toString(mpgain), "blue", 100+100*anims.size(), DnS.HEIGHT/2-70,true,false));
		}
		if(dpgain!=0){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("DPCard")));
			anims.add(new FadingAnimation(Integer.toString(dpgain), "purple", 100+100*anims.size(), DnS.HEIGHT/2-70,true,false));
		}
		if(moneygain!=0){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("MoneyCard")));
			anims.add(new FadingAnimation(Integer.toString(moneygain), "yellow", 100+100*anims.size(), DnS.HEIGHT/2-70,true,false));
		}
		if(foodgain!=0){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("FoodCard")));
			anims.add(new FadingAnimation(Integer.toString(foodgain), "green", 100+100*anims.size(), DnS.HEIGHT/2-70,true,false));
		}
		if(famegain!=0){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("FameCard")));
			anims.add(new FadingAnimation(Integer.toString(famegain), "purple", 100+100*anims.size(), DnS.HEIGHT/2-70,true,false));
		}
		if(itemgain){
			cards.add(new Graphic(100+100*cards.size(), DnS.HEIGHT/2-50, DnS.res.getAtlas("pack").findRegion("ItemCard")));
			
		}
	}
	
	
	
	
}
