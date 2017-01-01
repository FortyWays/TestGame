package com.battle.card;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.state.BattleState;

public class AICard extends Card{
	
	

	public AICard(int spcost, int mpcost, String cardId,TextureRegion cardArt) {
		super(spcost, mpcost, cardId,cardArt);
	
	}

	@Override
	void GetCardInstructions() {
		CardStorage.AICards.get(cardId);
	}

	

}
