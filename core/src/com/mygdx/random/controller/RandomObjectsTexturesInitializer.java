package com.mygdx.random.controller;

import com.badlogic.gdx.graphics.Texture;

public class RandomObjectsTexturesInitializer {
	
	public Texture escapeBoost;
	
	public Texture slowPlayerObject;
	
	public Texture speedBooster;
	
	public Texture timeBooster;
	
	public RandomObjectsTexturesInitializer(){
		
		escapeBoost = new Texture("randomObjects/EscapeBoost.png");
		
		slowPlayerObject = new Texture("randomObjects/SlowBoost.png");
		
		speedBooster = new Texture("randomObjects/SpeedBoost.png");
		
		timeBooster = new Texture("randomObjects/TimeBoost.png");
		
	}
}
