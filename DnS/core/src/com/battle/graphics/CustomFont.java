package com.battle.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;

public class CustomFont {

	private boolean animated=false;
	private ArrayList<Graphic> text;
	private int currentNum=0;
	private float x,y;
	private float timer=1f;
	private boolean blackText=false;

	public CustomFont(String message,float x,float y,float width,float height,boolean animated,boolean color){
		this(message,x,y,width,height,animated);
		this.blackText=color;
	}
	public CustomFont(String message,float x,float y,float width,float height){
		this.x=x;
		this.y=y;
		TextureRegion[][] alphabet=DnS.res.getAtlas("pack").findRegion("Font").split(7, 7);
		TextureRegion[][] numbers=DnS.res.getAtlas("pack").findRegion("Numbers").split(5, 5);
		text=new ArrayList<>();
		float curX=x;
		for(int i=0;i<message.length();i++){
			char c=message.charAt(i);	
			if(c>='a'&&c<='z'){
				text.add(new Graphic(curX, y,width,height, alphabet[0][(int)c-(int)('a')]));
				curX+=width*0.9;
			}
			else
			if(c>='A'&&c<='Z'){
				text.add(new Graphic(curX, y,width,height, alphabet[0][(int)c-(int)('A')]));
				curX+=width*0.9;
			}
			else if(c>='0'&&c<='9'){
				text.add(new Graphic(curX, y,width,height, numbers[0][(int)c-(int)('0')]));
				curX+=width*0.9;
			}
			else if(c=='/'){
				text.add(new Graphic(curX, y,width,height, numbers[0][10]));
				curX+=width*0.9;
			}
			else if(c==' '){
				curX+=width*0.9;
			}
		}
	}
	public CustomFont(String message,float x,float y,boolean animated){
		this(message,x,y,7,7);
		this.animated=animated;
	}
	public CustomFont(String message,float x,float y,float width,float height,boolean animated){
		this(message,x,y,width,height);
		this.animated=animated;
	}
	public CustomFont(String message,float x,float y,String color){
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
		text=new ArrayList<>();
		for(String lex:lexems){
			if(lex.contains("draw")){
				this.text.add(new Graphic(curX, curY, DnS.res.getAtlas("pack").findRegion("Draw")));
				curX+=35;
			}
			else if(lex.contains("blocked")){
				this.text.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Blocked")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("bleeding")){
				this.text.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Bleeding")));
				curX+=DnS.res.getAtlas("pack").findRegion("Bleeding").getRegionWidth()-25;
			}
			else if(lex.contains("poisoned")){
				this.text.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Poisoned")));
				curX+=DnS.res.getAtlas("pack").findRegion("Poisoned").getRegionWidth()-25;
			}
			else if(lex.contains("outofcards")){
				this.text.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("OutOfCards")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("cured")){
				this.text.add(new Graphic(curX, curY,
						DnS.res.getAtlas("pack").findRegion("Cured")));
				curX+=DnS.res.getAtlas("pack").findRegion("Cured").getRegionWidth();
			}
			else if(lex.contains("stunned")){
				this.text.add(new Graphic(curX, curY, 
						DnS.res.getAtlas("pack").findRegion("Stunned")));
				curX+=DnS.res.getAtlas("pack").findRegion("Stunned").getRegionWidth();
			}
			else if(lex.contains("perturnred")){
				this.text.add(new Graphic(curX+40, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnRed")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnRed").getRegionWidth();
			}
			else if(lex.contains("perturngreen")){
				this.text.add(new Graphic(curX+40, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnGreen")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnGreen").getRegionWidth();
			}
			else if(lex.contains("perturnblue")){
				this.text.add(new Graphic(curX+40, curY,
						DnS.res.getAtlas("pack").findRegion("PerTurnBlue")));
				curX+=DnS.res.getAtlas("pack").findRegion("PerTurnBlue").getRegionWidth();
			}
			else{
				for(int i=0;i<lex.length();i++){
					char c=lex.charAt(i);
					if(c>='0'&&c<='9'){
						this.text.add(new Graphic(curX, curY,
								numbers[0][Character.getNumericValue(c)]));
						curX+=numbers[0][Character.getNumericValue(c)].getRegionWidth();
					}
					else if(c=='-'){
						this.text.add(new Graphic(curX, curY, numbers[0][10]));
						curX+=numbers[0][10].getRegionWidth();
					}
					else if(c=='+'){
						this.text.add(new Graphic(curX, curY, numbers[0][11]));
						curX+=numbers[0][11].getRegionWidth();
					}
				}
			}
		}
		
	}
	public void update(float dt){
		if(animated){
		if(timer>=0){
			timer-=0.2f;
		}
		else {
			timer=1f;
			currentNum++;
		}
		}
	}
	public void render(SpriteBatch spriteBatch){
		if(blackText){
			spriteBatch.setColor(Color.BLACK);
		}
		if(animated){
		for(int i=0;i<Math.min(currentNum, text.size());i++){
			text.get(i).render(spriteBatch);
		}
		}
		else{
		for(int i=0;i<text.size();i++){
			text.get(i).render(spriteBatch);
		}
		}
		if(blackText){
		spriteBatch.setColor(1,1,1,1);
		}
	}
	

}
