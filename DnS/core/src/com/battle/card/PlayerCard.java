package com.battle.card;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.state.BattleState;

public class PlayerCard extends Card{

	

	public PlayerCard(int spcost, int mpcost, String cardId,TextureRegion cardArt) {
		super(spcost, mpcost, cardId,cardArt);
	}

	@Override
	void GetCardInstructions()
	{
		CardInstructions=CardStorage.PlayerCards.get(cardId);
		
	}

	
	

}
