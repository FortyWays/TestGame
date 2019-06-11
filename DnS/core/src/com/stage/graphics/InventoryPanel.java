package com.stage.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;
import com.encounter.EncounterPlayer;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;
import com.fortyways.util.Rectangle;
import com.stage.items.Item;

public class InventoryPanel {
	private Graphic panel;
	private ArrayList<Graphic> cards;
	
	private ArrayList<Item> helm=new ArrayList<>();
	private ArrayList<Item> armor=new ArrayList<>();
	private ArrayList<Item> primary=new ArrayList<>();
	private ArrayList<Item> secondary=new ArrayList<>();
	private ArrayList<Item> misc=new ArrayList<>();
	private ArrayList<Item> uneqiupped=new ArrayList<>();
	private BitmapFont font=new BitmapFont();
	
	private Item chosenItem;
	
	//TODO EQUIP BUTTON sprite
	//private Graphic equipButton=new Graphic(DnS.WIDTH/2+200,DnS.HEIGHT/2+200 , DnS.res.getAtlas("pack").findRegion(""));
	private ArrayList<Card> deck;
	public Graphic descArt;
	private int selnum=0;
	public boolean selected=false;
	public static Rectangle rect=new Rectangle(DnS.WIDTH/2, DnS.HEIGHT/2, 
				DnS.res.getAtlas("pack").findRegion("Panel3").getRegionWidth()*6,
				DnS.res.getAtlas("pack").findRegion("Panel3").getRegionHeight()*6);
	
	public InventoryPanel() {
		panel=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2, 
				DnS.res.getAtlas("pack").findRegion("Panel3").getRegionWidth()*6,
				DnS.res.getAtlas("pack").findRegion("Panel3").getRegionHeight()*6,
				DnS.res.getAtlas("pack").findRegion("Panel3"));
		
	}
	public InventoryPanel(EncounterPlayer player) {
		panel=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2, 
				DnS.res.getAtlas("pack").findRegion("Panel3").getRegionWidth()*6,
				DnS.res.getAtlas("pack").findRegion("Panel3").getRegionHeight()*6,
				DnS.res.getAtlas("pack").findRegion("Panel3"));
		sortItems(player);
	}
	public void sortItems(EncounterPlayer player){
		//System.out.println("SORTING STARTED");

		for(Item item:player.items){
			
			if(!item.equipped){
				uneqiupped.add(item);
				item.setRelativeX(DnS.WIDTH/2-250+uneqiupped.size()*30);
				item.setRelativeY(DnS.HEIGHT/2-150);
				//System.out.println(item.getName()+" to "+"UNEQIUPPED");
			}
			else if(item.getSlot()==Item.Slot.HELM){
				helm.add(item);
				item.setRelativeX(DnS.WIDTH/2-250+helm.size()*30);
				item.setRelativeY(DnS.HEIGHT/2+100);
				//System.out.println(item.getName()+" to "+"HELM");
			}
			else if(item.getSlot()==Item.Slot.ARMOR){
				armor.add(item);
				item.setRelativeX(DnS.WIDTH/2-250+armor.size()*30);
				item.setRelativeY(DnS.HEIGHT/2+50);
				//System.out.println(item.getName()+" to "+"ARMOR");
			}
			else if(item.getSlot()==Item.Slot.PRIMARY){
				primary.add(item);
				item.setRelativeX(DnS.WIDTH/2-250+primary.size()*30);
				item.setRelativeY(DnS.HEIGHT/2);
				//System.out.println(item.getName()+" to "+"PRIMARY");
			}
			else if(item.getSlot()==Item.Slot.SECONDARY){
				secondary.add(item);
				item.setRelativeX(DnS.WIDTH/2-250+secondary.size()*30);
				item.setRelativeY(DnS.HEIGHT/2-50);
				//System.out.println(item.getName()+" to "+"SECONDARY");
			}
			else if(item.getSlot()==Item.Slot.MISCELLANEOUS){
				misc.add(item);
				item.setRelativeX(DnS.WIDTH/2-250+misc.size()*30);
				item.setRelativeY(DnS.HEIGHT/2-100);
				//System.out.println(item.getName()+" to "+"MISCELLANEOUS");
			}
			
		}
		//This is only safe if player always has a primary Item. Is this true though???
		setChosenItem(primary.get(0));
		
	}
	public void render(SpriteBatch sb){
		panel.render(sb);
	
		font.draw(sb,"UNEQIUPPED",DnS.WIDTH/2-280,DnS.HEIGHT/2-140);
		font.draw(sb,"MISCELLANEOUS",DnS.WIDTH/2-280,DnS.HEIGHT/2-90);
		font.draw(sb,"SECONDARY",DnS.WIDTH/2-280,DnS.HEIGHT/2-40);
		font.draw(sb,"PRIMARY",DnS.WIDTH/2-280,DnS.HEIGHT/2+10);
		font.draw(sb,"ARMOR",DnS.WIDTH/2-280,DnS.HEIGHT/2+60);
		font.draw(sb,"HELM",DnS.WIDTH/2-280,DnS.HEIGHT/2+110);
		
		font.draw(sb,chosenItem.getName(),DnS.WIDTH/2+75,DnS.HEIGHT/2+90);
		font.draw(sb,chosenItem.getDescription(),DnS.WIDTH/2+50,DnS.HEIGHT/2+70);
		for(Item item:uneqiupped){
			item.render(sb);
		}
		for(Item item:helm){
			item.render(sb);
		}
		for(Item item:armor){
			item.render(sb);
		}
		for(Item item:primary){
			item.render(sb);
		}
		for(Item item:secondary){
			item.render(sb);
		}
		for(Item item:misc){
			item.render(sb);
		}
		chosenItem.render(sb);
		if(cards.size()>0){
			for(Graphic gr:cards){
				gr.render(sb);
			}
		}
	}
	
	public void handleInput(float x, float y) {
		//System.out.println("HANDLE INPUT IN INVENTORY PANEL");
		for(Item item:uneqiupped){
			if(item.Touched(x, y)){
				setChosenItem(item);
			}
		}
		for(Item item:helm){
			if(item.Touched(x, y)){
				setChosenItem(item);
			}
		}
		for(Item item:armor){
			if(item.Touched(x, y)){
				setChosenItem(item);
			}
		}
		for(Item item:primary){
			if(item.Touched(x, y)){
				setChosenItem(item);
			}
		}
		for(Item item:secondary){
			if(item.Touched(x, y)){
				setChosenItem(item);
			}
		}
		for(Item item:misc){
			if(item.Touched(x, y)){
				setChosenItem(item);
			}
		}
	}
	
	public void update(float dt){
		
	}
	
	public void Refresh(EncounterPlayer player){
		
	}
	private void setChosenItem(Item item){
		//System.out.println("SET ITEM");
		chosenItem=new Item(item.getName(), item.getDescription(),
				item.getCost(), item.getItemInstructions(), item.getSprite());
		chosenItem.setRelativeX(DnS.WIDTH/2+75);
		chosenItem.setRelativeY(DnS.HEIGHT/2+100);
		chosenItem.width*=2;
		chosenItem.height*=2;
		
		if(item.getCards().size()>0||item.getCards()!=null){
			chosenItem.setCards(item.getCards());
			deck=chosenItem.getCards();
			cards=new ArrayList<>();
			for(int i=0;i<deck.size();i++){
				cards.add(new Graphic(DnS.WIDTH/2+10+(i%7)*60,DnS.HEIGHT/2-100,
				deck.get(i).cardArt));
		
			
			}
		}
	}
	
	
	
	
	
	
}
