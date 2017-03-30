package com.mygdx.players;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Berek;

@SuppressWarnings("serial")
public class Player extends Rectangle{

	public boolean isBerek;
	
	private static final float recoilSpeed = 1.1f;
	
	private static final float recoilPower = 3;
	
	public float playerSpeed = 7;
	
	public boolean checkReflection = true;

	public Label playerNickLabel;
	
	private Texture berekTexture;
	
	private Texture noBerekTexture;
	
	public String nick;
	
	public boolean win = false;
	
	public float xTorque=0;
	
	public float yTorque=0;

	private Skin nameSkin;
	
	public float knobX;
	
	public float knobY;
	
	
	public Player(boolean isBerek) {
		
		this.isBerek = isBerek;
		
		setSize(50, 50);
		
	}
	
	public Player(boolean isBerek, Skin nameSkin){
		
		this.isBerek = isBerek;
		
		this.nameSkin = nameSkin;
		
		berekTexture = new Texture("berek_circle.png");
		
		noBerekTexture = new Texture("no_berek_circle.png");

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
			}
			
			if(x > Berek.GAME_WIDTH - 50){
				
				xTorque = -50/recoilPower;					
			}
			
			if(y < 0){
				
				yTorque = 50/recoilPower;					
			}
			
			if(y > Berek.GAME_HEIGHT - 50){
				
				yTorque = -50/recoilPower;	
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
	
	public void initPlayerNick() {
		
		playerNickLabel = new Label(nick, nameSkin);
		
		playerNickLabel.setPosition(getX() - playerNickLabel.getWidth() , getY() - 20 );
		
		playerNickLabel.setFontScale(2f);
		
	}
	
	public void calculateNamePosition() {
		
		playerNickLabel.setPosition(getX() + width/2 - playerNickLabel.getPrefWidth()/2, getY() - 40 );
		
		playerNickLabel.setText(nick);
		
		System.out.println("playerPosition " + getX() + " " + getY());
		
		System.out.println("labelPosition " + playerNickLabel.getX() + " " + playerNickLabel.getY());
		
	}
}   
