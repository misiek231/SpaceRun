package com.mygdx.players;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Berek;

@SuppressWarnings("serial")
public class Player extends Rectangle implements java.io.Serializable{

	public boolean isBerek;
	
	private static final float recoilSpeed = 1.1f;
	
	private static final float recoilPower = 3;
	
	public float playerSpeed = 7;
	
	public boolean checkReflection = true;

	public Label playerNickLabel;
	
	public String nick;
	
	public boolean win = false;
	
	public float xTorque=0;
	
	public float yTorque=0;

	private Skin nameSkin;
	
	public float knobX;
	
	public float knobY;
	
	PlayerTexturesInitializer textures;
	
	
	public Player(boolean isBerek, String nick) {
		
		this.isBerek = isBerek;
		
		this.nick = nick;
		
		set(MathUtils.random(300, 670), MathUtils.random(300, 1230), 50, 50);

	}
	
	public Player(boolean isBerek, String nick, Skin nameSkin, PlayerTexturesInitializer playersTextures){
		
		this(isBerek, nick);
		
		this.nameSkin = nameSkin;
		
		textures = playersTextures;
	}

	public Texture getTexture() {		
		
		if(isBerek)
			return textures.berekTexture;
		else 
			return textures.noBerekTexture;
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
	
	public void checkReflection(ParticleEffect pe) {		
		
		if(checkReflection){
			
			if(x < 10){
				
				 pe.getEmitters().first().setPosition(0 ,y + height/2);
				   
				 pe.start();				
			}
			
			if(x > Berek.GAME_WIDTH - 60){
				 pe.getEmitters().first().setPosition(Berek.GAME_WIDTH, y + height / 2);
				   
				 pe.start();
									
			}
			
			if(y < 10){
				 pe.getEmitters().first().setPosition( x + width / 2 , 0);
				   
				 pe.start();
									
			}
			
			if(y > Berek.GAME_HEIGHT - 60){
				
				 pe.getEmitters().first().setPosition(x + width / 2, Berek.GAME_HEIGHT);
				   
				 pe.start();
			}
		}
	}
	
	public void initPlayerNick() {
		
		playerNickLabel = new Label("nick", nameSkin);
		
		playerNickLabel.setPosition(getX() - playerNickLabel.getWidth() , getY() - 20 );
		
		playerNickLabel.setFontScale(2f);
		
	}
	
	public void calculateNamePosition() {
		
		playerNickLabel.setPosition(getX() + width/2 - playerNickLabel.getPrefWidth()/2, getY() - 40 );
		
		playerNickLabel.setText(nick);
		
	}
}   
