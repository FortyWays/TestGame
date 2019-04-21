package com.battle.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;

public class FadingAnimation {

	private ArrayList<Graphic> message;
	public boolean started=false;
	public boolean finished=false;
	
	public FadingAnimation(String message,String color,float x,float y) {
		started=true;
		String[] lexems=message.split(" ");
		
		float curX=x,curY=y;
		TextureRegion[][] numbers;
		if(color=="yellow"){	
		numbers=DnS.res.getAtlas("pack").findRegion("YellowNumbers").split(10, 15);
		}
		else if(color=="green"){	
		numbers=DnS.res.getAtlas("pack").findRegion("GreenNumbers").split(10, 15);
		}
		else if(color=="blue"){	
		numbers=DnS.res.getAtlas("pack").findRegion("BlueNumbers").split(10, 15);
		}
		else if(color=="purple"){	
		numbers=DnS.res.getAtlas("pack").findRegion("PurpleNumbers").split(10, 15);
		}
		else{
		numbers=DnS.res.getAtlas("pack").findRegion("RedNumbers").split(10, 15);
		}
		this.message=new ArrayList<>();
		for(String lex:lexems){
			if(lex.contains("draw")){
				this.message.add(new Graphic(curX, curY, DnS.res.getAtlas("pack").findRegion("Draw")));
				curX+=35;
			}
			else if(lex.contains("blocked")){
				this.message.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Blocked")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("pass")){
				this.message.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Pass")));
				curX+=DnS.res.getAtlas("pack").findRegion("Pass").getRegionWidth();
			}
			else if(lex.contains("darkpresence")){
				this.message.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("DarkPresence")));
				curX+=DnS.res.getAtlas("pack").findRegion("DarkPresence").getRegionWidth();
			}
			else if(lex.contains("bleeding")){
				this.message.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Bleeding")));
				curX+=DnS.res.getAtlas("pack").findRegion("Bleeding").getRegionWidth()-25;
			}
			else if(lex.contains("poisoned")){
				this.message.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Poisoned")));
				curX+=DnS.res.getAtlas("pack").findRegion("Poisoned").getRegionWidth()-25;
			}
			else if(lex.contains("outofcards")){
				this.message.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("OutOfCards")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("cured")){
				this.message.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Cured")));
				curX+=DnS.res.getAtlas("pack").findRegion("Cured").getRegionWidth();
			}
			else if(lex.contains("stunned")){
				this.message.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Stunned")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("perturnred")){
				this.message.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnRed")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnRed").getRegionWidth();
			}
			else if(lex.contains("perturngreen")){
				this.message.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnGreen")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnGreen").getRegionWidth();
			}
			else if(lex.contains("perturnblue")){
				this.message.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnBlue")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnBlue").getRegionWidth();
			}
			else{
				for(int i=0;i<lex.length();i++){
					char c=lex.charAt(i);
					if(c>='0'&&c<='9'){
						this.message.add(new Graphic(curX, curY,
								numbers[0][Character.getNumericValue(c)]));
						curX+=numbers[0][Character.getNumericValue(c)].getRegionWidth();
					}
					else if(c=='-'){
						this.message.add(new Graphic(curX, curY, numbers[0][10]));
						curX+=numbers[0][10].getRegionWidth();
					}
					else if(c=='+'){
						this.message.add(new Graphic(curX, curY, numbers[0][11]));
						curX+=numbers[0][11].getRegionWidth();
					}
				}
			}
		}
		
	}
	private float alpha=1;
	private float timer=1f;
	public void update(float dt){
		
		if(started&&!finished){
			if(timer<=0){
		if(alpha>0){
			alpha-=0.02f;
		}
		if(alpha<=0){
			alpha=0;
			finished=true;
		}
			}
			else{
				timer-=0.05f;
			}
		for(Graphic gr:message){
			
			//gr.x+=0.5;
			gr.y+=0.5;
			//gr.width+=1;
			//gr.height+=1;
		
		}
		}
	}
	public void render(SpriteBatch spriteBatch){
		spriteBatch.setColor(1, 1, 1, alpha);
		for(Graphic gr:message){
			if(started&&!finished&&timer<1)
			gr.render(spriteBatch);
		}
		spriteBatch.setColor(1, 1, 1, 1);
	}
	
	
	
}
