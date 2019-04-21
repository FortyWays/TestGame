package com.battle.graphics;




import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fortyways.util.Rectangle;

public class Animation extends Rectangle{

	protected TextureRegion[][] frames;
	protected TextureRegion[][] backupframes;
	protected TextureRegion curFrame;
	private int curFrameRow=0,curFrameColumn=0;
	protected float timePerFrame;
	protected float backuptimePerFrame;
	protected float time=0;
	protected boolean tempAnimInProgress=false;
	

	public Animation(float x,float y,float width,float height,
			TextureRegion textureRegion,float timePerFrame,int frameWidth,int frameHeight ) {
		super(x, y, width, height);
		//this.setX(x);
		//this.setY(y);
		//this.setWidth(width);
		//this.setHeight(height);
		this.timePerFrame=timePerFrame;
		setUpFrames(textureRegion, frameWidth, frameHeight,false,timePerFrame);
	}
	
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(curFrame,x-width/2,y-height/2,width,height);
	}
	
	public void update(float dt){
	if(time<20){
		time+=timePerFrame/5;
	}
	else{
		time=0;
		getNextFrame();
	}
	}
	public void switchAnimation(TextureRegion textureRegion,int frameWidth,int frameHeight
			,boolean tempAnim,float timePerFrame){
		setUpFrames(textureRegion, frameWidth, frameHeight,tempAnim,timePerFrame);
		time=0;
	}
	public void setUpFrames(TextureRegion textureRegion,int frameWidth,int frameHeight,
			boolean tempAnim,float timePerFrame){
		this.tempAnimInProgress=tempAnim;
		if(tempAnim){
			backupframes=frames.clone();
			backuptimePerFrame=this.timePerFrame;
		}
			this.timePerFrame=timePerFrame;
			frames=textureRegion.split(frameWidth, frameHeight);
			curFrameRow=0;
			curFrameColumn=0;
		curFrame=frames[0][0];
	}
	
	public void getNextFrame(){
		if(curFrameRow==frames.length-1 && curFrameColumn==frames[0].length-1){
			curFrameRow=0;
			curFrameColumn=0;
			if(tempAnimInProgress){
				tempAnimInProgress=false;
				frames=backupframes;
				timePerFrame=backuptimePerFrame;
			}
			
		}
		else if(curFrameColumn==frames[0].length-1){
			curFrameRow++;
			curFrameColumn=0;
		}
		else{
			curFrameColumn++;
		}
		
		curFrame=frames[curFrameRow][curFrameColumn];
		
	}

	
	
	
	
	
}
