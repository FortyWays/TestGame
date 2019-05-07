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
	public boolean expanding=false;
	public boolean fading=true;
	public FadingAnimation(String message,String color,float x,float y) {
		started=true;
		this.message=makeText(message, color, x, y);
	}
	public FadingAnimation(String message,String color,float x,float y,boolean expanding,boolean fading) {
		started=true;
		this.expanding=expanding;
		this.fading=fading;
		this.message=makeText(message, color, x, y);
	}
	public static ArrayList<Graphic> makeText(String message,String color,float x,float y){
		//started=true;
		ArrayList<Graphic> res;
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
		res=new ArrayList<>();
		for(String lex:lexems){
			if(lex.contains("draw")){
				res.add(new Graphic(curX, curY, DnS.res.getAtlas("pack").findRegion("Draw")));
				curX+=35;
			}
			else if(lex.contains("blocked")){
				res.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Blocked")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("pass")){
				res.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Pass")));
				curX+=DnS.res.getAtlas("pack").findRegion("Pass").getRegionWidth();
			}
			else if(lex.contains("darkpresence")){
				res.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("DarkPresence")));
				curX+=DnS.res.getAtlas("pack").findRegion("DarkPresence").getRegionWidth();
			}
			else if(lex.contains("bleeding")){
				res.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Bleeding")));
				curX+=DnS.res.getAtlas("pack").findRegion("Bleeding").getRegionWidth()-25;
			}
			else if(lex.contains("poisoned")){
				res.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Poisoned")));
				curX+=DnS.res.getAtlas("pack").findRegion("Poisoned").getRegionWidth()-25;
			}
			else if(lex.contains("outofcards")){
				res.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("OutOfCards")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("cured")){
				res.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Cured")));
				curX+=DnS.res.getAtlas("pack").findRegion("Cured").getRegionWidth();
			}
			else if(lex.contains("stunned")){
				res.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Stunned")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("perturnred")){
				res.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnRed")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnRed").getRegionWidth();
			}
			else if(lex.contains("perturngreen")){
				res.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnGreen")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnGreen").getRegionWidth();
			}
			else if(lex.contains("perturnblue")){
				res.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnBlue")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnBlue").getRegionWidth();
			}
			else{
				for(int i=0;i<lex.length();i++){
					char c=lex.charAt(i);
					if(c>='0'&&c<='9'){
						res.add(new Graphic(curX, curY,
								numbers[0][Character.getNumericValue(c)]));
						curX+=numbers[0][Character.getNumericValue(c)].getRegionWidth();
					}
					else if(c=='-'){
						res.add(new Graphic(curX, curY, numbers[0][10]));
						curX+=numbers[0][10].getRegionWidth();
					}
					else if(c=='+'){
						res.add(new Graphic(curX, curY, numbers[0][11]));
						curX+=numbers[0][11].getRegionWidth();
					}
				}
			}
		}
		return res;
		
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
			if(fading)
			finished=true;
		}
			}
			else{
				timer-=0.05f;
			}
		for(Graphic gr:message){
			
			//gr.x+=0.5;
			if(gr.width<gr.image.getRegionWidth()*3){
			gr.y+=0.4;
			}
			//
			if(expanding&&gr.width<gr.image.getRegionWidth()*3){
				gr.width+=0.6;
				gr.height+=0.6;
			}
			//
		
		}
		}
	}
	public void render(SpriteBatch spriteBatch){
		if(fading)
		spriteBatch.setColor(1, 1, 1, alpha);
		for(Graphic gr:message){
			if(started&&!finished&&timer<1)
			gr.render(spriteBatch);
		}
		spriteBatch.setColor(1, 1, 1, 1);
	}
	
	
	
}
