package com.mygdx.random.controller;

import com.badlogic.gdx.graphics.Texture;

public class RandomObjectClient {

	public float x;
	
	public float y;
	
	public float width = 50;
	
	public float height = 50;
	
	public int objectType;
	
	Texture texture;
	
	public RandomObjectClient(float x, float y, int objectType, RandomObjectsTexturesInitializer objectsTexture){
		
		this.objectType = objectType;
		
		this.x = x;
		
		this.y = y;
		
		if(objectType == RandomObjectType.EscapeBoost){
			
			texture = objectsTexture.escapeBoost;			
		}
		
		if(objectType == RandomObjectType.SlowPlayerObject){
			
			texture = objectsTexture.slowPlayerObject;			
		}

		if(objectType == RandomObjectType.SpeedBoster){
	
			texture = objectsTexture.speedBooster;			
		}

		if(objectType == RandomObjectType.TimeBost){
	
			texture = objectsTexture.timeBooster;			
		}
	}
	
	public Texture getTexture(){
		
		return texture;
	}
}
