package com.mygdx.random.controller;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RandomObjectsControllerClient {

	public List<RandomObjectClient> randomObjects;
	
	public RandomObjectsTexturesInitializer ObjectsTexture;

	public RandomObjectsControllerClient() {
		
		randomObjects = new ArrayList<RandomObjectClient>();
		
		ObjectsTexture = new RandomObjectsTexturesInitializer();
	}
	
	public void updateObjects(ArrayList<RandomObjectData> randomObjectsData){
	
		
		randomObjects.clear();
		
		for (RandomObjectData randomObjectData : randomObjectsData) {
			
			randomObjects.add( new RandomObjectClient (randomObjectData.x, randomObjectData.y, randomObjectData.objectType, ObjectsTexture ) );
		}	
	}
	
	public void drowObjects(SpriteBatch batch){
		
		for(RandomObjectClient randomObject : randomObjects) {

			batch.draw( randomObject.getTexture(), randomObject.x, randomObject.y, randomObject.width, randomObject.height );		
		}
	}		
}
