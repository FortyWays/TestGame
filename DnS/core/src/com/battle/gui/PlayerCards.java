package com.battle.gui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.card.Card;

public class PlayerCards {

	public ArrayList<Card> cardsinhand;
	public ArrayList<Graphic> cards;
	public PlayerCards(ArrayList<Card> cardsinhand) {
		this.cardsinhand=cardsinhand;
		cards=new ArrayList<>();
		for (int i=0;i<cardsinhand.size();i++) {
			Card card=cardsinhand.get(i);
			cards.add(new Graphic(i*50+10, 50, card.cardArt));
		}
	}
	public void handleInput(){
		
	}
	public void update(float dt){
		
	}
	public void render(SpriteBatch sb){
		for (Graphic graphic : cards) {
			graphic.render(sb);
		}
	}
}
