package com.battle.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.battle.graphics.Animation;
import com.battle.graphics.ColorSwitchAnimation;
import com.battle.graphics.FadingAnimation;
import com.fortyways.dns.DnS;
import com.fortyways.util.Graphic;

public class AdditionalAnimationHandler {
	protected ColorSwitchAnimation onHitAnimation;
	public ArrayList<FadingAnimation> anims;
	private Animation fireAnim;
	private BattleEntity ent;
	private ArrayList<Graphic> icons;
	public AdditionalAnimationHandler(BattleEntity ent) {
		this.ent=ent;
		onHitAnimation=new ColorSwitchAnimation(true, false);
		anims=new ArrayList<>();
		icons=new ArrayList<>();
		
		//
	}
	public void setFireAnim(){
		fireAnim=new Animation(ent.idleAnim.x,  DnS.HEIGHT/2,
				26*5, 29*5, DnS.res.getAtlas("pack").findRegion("fireanim"), 
				20, 26, 29);
	}
	public void render(SpriteBatch spriteBatch){
		if(!ent.isDead())
		for(Graphic gr:icons){
			gr.render(spriteBatch);
		}
		for(FadingAnimation fa:anims){
			fa.render(spriteBatch);
		}
		if(ent.isOnFire()){
			fireAnim.render(spriteBatch);
		}
	}
	public void update(float dt){
		if(ent.isOnFire()){
				fireAnim.update(dt);
			}
		for(FadingAnimation fa:anims){
			if(fa.started&&!fa.finished){
				fa.update(dt);
				break;
			}
			if(fa.started&&fa.finished){
				anims.remove(fa);
				break;
			}
			
		}
		onHitAnimation.update(dt);
		
		}
	public void setColorForAnimation(SpriteBatch spriteBatch){
		onHitAnimation.setColor(spriteBatch);
	}
	public void setColorBack(SpriteBatch spriteBatch){
		onHitAnimation.setColorBack(spriteBatch);
	}
	public void setPoisonedIcon(boolean poisoned){
		if(poisoned){
			if(icons.size()!=0){
				icons.add(new Graphic(icons.get(icons.size()-1).x+18,
								ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Poison")));
			}
			else{
				icons.add(new Graphic(ent.getIdleAnim().x-30,
						ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Poison")));
			}
		}
		else{
			for(Graphic icon:icons){
				if(icon.image==DnS.res.getAtlas("pack").findRegion("Poison")){
					icons.remove(icon);
					break;
				}
			}
		}
	}
	public void setMarkedIcon(boolean marked){
		if(marked){
			if(icons.size()!=0){
				icons.add(new Graphic(icons.get(icons.size()-1).x+18,
								ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Marked")));
			}
			else{
				icons.add(new Graphic(ent.getIdleAnim().x-30,
						ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Marked")));
			}
		}
		else{
			for(Graphic icon:icons){
				if(icon.image==DnS.res.getAtlas("pack").findRegion("Marked")){
					icons.remove(icon);
					break;
				}
			}
		}
	}
	public void setOnFireIcon(boolean onfire){
		if(onfire){
			if(icons.size()!=0){
				icons.add(new Graphic(icons.get(icons.size()-1).x+18,
								ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Fire")));
			}
			else{
				icons.add(new Graphic(ent.getIdleAnim().x-30,
						ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Fire")));
			}
		}
		else{
			for(Graphic icon:icons){
				if(icon.image==DnS.res.getAtlas("pack").findRegion("Fire")){
					icons.remove(icon);
					break;
				}
			}
		}
	}
	public void setRangedBlockIcon(boolean rangedBlock){
		if(rangedBlock){
			if(icons.size()!=0){
				icons.add(new Graphic(icons.get(icons.size()-1).x+18,
								ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("BlockRanged")));
			}
			else{
				icons.add(new Graphic(ent.getIdleAnim().x-30,
						ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("BlockRanged")));
			}
		}
		else{
			for(Graphic icon:icons){
				if(icon.image==DnS.res.getAtlas("pack").findRegion("BlockRanged")){
					icons.remove(icon);
					break;
				}
			}
		}
	}
	public void setStunnedIcon(boolean stunned){
		if(stunned){
			if(icons.size()!=0){
				icons.add(new Graphic(icons.get(icons.size()-1).x+18,
								ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Stun")));
			}
			else{
				icons.add(new Graphic(ent.getIdleAnim().x-30,
						ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Stun")));
			}
		}
		else{
			for(Graphic icon:icons){
				if(icon.image==DnS.res.getAtlas("pack").findRegion("Stun")){
					icons.remove(icon);
					break;
				}
			}
		}
	}
	public void setMeleeBlockIcon(boolean meleeBlock){
		if(meleeBlock){
			if(icons.size()!=0){
				icons.add(new Graphic(icons.get(icons.size()-1).x+18,
								ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("BlockMelee")));
			}
			else{
				icons.add(new Graphic(ent.getIdleAnim().x-30,
						ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("BlockMelee")));
			}
		}
		else{
			for(Graphic icon:icons){
				if(icon.image==DnS.res.getAtlas("pack").findRegion("BlockMelee")){
					icons.remove(icon);
					break;
				}
			}
		}
	}
	public void setMagicBlockIcon(boolean magicBlock){
		if(magicBlock){
			if(icons.size()!=0){
				icons.add(new Graphic(icons.get(icons.size()-1).x+18,
								ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("BlockMagic")));
			}
			else{
				icons.add(new Graphic(ent.getIdleAnim().x-30,
						ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("BlockMagic")));
			}
		}
		else{
			for(Graphic icon:icons){
				if(icon.image==DnS.res.getAtlas("pack").findRegion("BlockMagic")){
					icons.remove(icon);
					break;
				}
			}
		}
	}
	public void setBleedingIcon(boolean bleeding){
			if(bleeding){
				if(icons.size()!=0){
					icons.add(new Graphic(icons.get(icons.size()-1).x+18,
									ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Bleed")));
				}
				else{
					icons.add(new Graphic(ent.getIdleAnim().x-30,
							ent.getIdleAnim().y+100, DnS.res.getAtlas("pack").findRegion("Bleed")));
				}
			}
			else{
				for(Graphic icon:icons){
					if(icon.image==DnS.res.getAtlas("pack").findRegion("Bleed")){
						icons.remove(icon);
						break;
					}
				}
			}
		}
	
}
