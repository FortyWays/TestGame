package com.stage.items;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.battle.card.Card;
import com.encounter.EncounterPlayer;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;
import com.fortyways.util.Rectangle;
import com.stage.graphics.ItemPanel;
import com.stage.player.StagePlayer;

public class Item extends Rectangle{
	
	private String name;
	private String description;
	private int cost;
	private ItemEffectAttributes[] itemInstructions;
	private ArrayList<Card> cards=new ArrayList<>();
	private TextureRegion sprite;
	public Graphic equipButton; 
	private TextureRegion itemslot=DnS.res.getAtlas("pack").findRegion("ItemSlot");
	public ItemPanel panel;
	private String className="";
	static public enum Slot { 
		ARMOR, HELM, PRIMARY, SECONDARY, MISCELLANEOUS;};
	private Slot slot;
	private float relativeX;
	private float relativeY;
	public boolean equipped=false;
	
	
	public Item (String name,String description,int cost,
			ItemEffectAttributes[] itemInstructions, TextureRegion texture) {
		super(0, 0, 50, 50);	
		this.itemInstructions=itemInstructions;
		this.sprite=texture;
		this.name=name;
		this.description=description;
		this.cost=cost;
		this.cards=new ArrayList<>();
	}
	
	public Item() {
		super(0,0,0,0);
		//dont use this evah
	}
	
	public void setUpPanel(){
		panel=new ItemPanel(name);
		equipButton=new Graphic(DnS.WIDTH/2, DnS.HEIGHT/2-80, 80, 30,
				DnS.res.getAtlas("pack").findRegion("EquipButton"));
		
	}
	public void update(float dt){
		panel.update(dt);
	}
	public void setWorldCoords(float WorldX,float WorldY){
		this.x=WorldX;
		this.y=WorldY;
		this.relativeX=WorldX;
		this.relativeY=WorldY;
	}
	public void getNewRelative(StagePlayer player){
		relativeX=this.x-player.getWorldX()+DnS.WIDTH/2;
		relativeY=this.y-player.getWorldY()+DnS.HEIGHT/2;
	}
	public void renderButton(SpriteBatch spriteBatch){
		panel.render(spriteBatch);
		equipButton.render(spriteBatch);
	}
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(itemslot, relativeX, relativeY,width,height);
		spriteBatch.draw(sprite, relativeX, relativeY,width,height);
	}

	public void Equip(EncounterPlayer player){
		if(!player.items.contains(this)){
			player.items.add(this);
		}
		for(Item item:player.equipped){
			if(item.slot==this.slot&&slot!=Item.Slot.MISCELLANEOUS){
				item.Unequip(player);	
				break;
			}
		}
		this.equipped=true;
		player.equipped.add(this);
		ItemEffects.ApplyEffect(itemInstructions, player);
	}
	public void Unequip(EncounterPlayer player){
		player.equipped.remove(this);
		this.equipped=false;
		ItemEffects.RemoveEffect(itemInstructions, player);
	}
	public void setRelativeX(float relativeX) {
		this.x=relativeX+width/2;
		this.relativeX = relativeX;
	}
	public void setRelativeY(float relativeY) {
		this.y=relativeY+height/2;
		this.relativeY = relativeY;
	}
	public float getRelativeY() {
		return relativeY;
	}
	public TextureRegion getSprite() {
		return sprite;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	public Slot getSlot() {
		return slot;
	}
	public String getClassName() {
		return className;
	}
	public int getCost() {
		return cost;
	}
	public ItemEffectAttributes[] getItemInstructions() {
		return itemInstructions;
	}
	
}
