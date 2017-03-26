package com.mygdx.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Berek;

@SuppressWarnings("serial")
public class Player extends Rectangle{

	public boolean isBerek;
	
	private static final float recoilSpeed = 1.1f;
	private static final float recoilPower = 3;
	public float playerSpeed = 7;
	public boolean checkReflection = true;

	
	private Texture berekTexture;
	private Texture noBerekTexture;
	
	public String nick;
	
	public boolean win = false;
	
	float xTorque=0;
	float yTorque=0;
	
	private Sound soundCollision;
	
	public Player(boolean isBerek){
		
		this.isBerek = isBerek;
		
		berekTexture = new Texture("berek_circle.png");
		noBerekTexture = new Texture("no_berek_circle.png");
		
		soundCollision = Gdx.audio.newSound(Gdx.files.internal("music/soundCollision.mp3"));
		
		setSize(50, 50);
	}


	public Texture getTexture() {		
		if(isBerek)
			return berekTexture;
		else 
			return noBerekTexture;
	}
	
	public void checkReflection() {		
		
		if(checkReflection){
			
			if(x < 0){
				
				xTorque = 50/recoilPower;	
				soundCollision.play();
			}
			
			if(x > Berek.GAME_WIDTH - 50){
				
				xTorque = -50/recoilPower;	
				soundCollision.play();
			}
			
			if(y < 0){
				
				yTorque = 50/recoilPower;	
				soundCollision.play();
			}
			
			if(y > Berek.GAME_HEIGHT - 50){
				
				yTorque = -50/recoilPower;	
				soundCollision.play();
			}
			
			
			
	    	if(xTorque > 1 || xTorque < -1)	    
	 	    	xTorque = xTorque/recoilSpeed;	    
	 	    else
	 	    	xTorque=0;
	 	    
	 	    if(yTorque > 1 || yTorque < -1)		    
	 	    	yTorque = yTorque/recoilSpeed;	    
	 	    else
	 	    	yTorque=0;
	    	
	 	
	 	    
	 	    	x += xTorque;
	 	    	y += yTorque;

		}
	}
}   
