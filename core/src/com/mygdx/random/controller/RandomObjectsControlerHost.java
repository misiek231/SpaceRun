package com.mygdx.random.controller;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.hosting.Hosting;
import com.mygdx.players.Player;
import com.mygdx.random.objects.EscapeBoost;
import com.mygdx.random.objects.RandomObject;
import com.mygdx.random.objects.SlowPlayerObject;
import com.mygdx.random.objects.SpeedBoster;
import com.mygdx.random.objects.TimeBoost;

public class RandomObjectsControlerHost {
	
	public ArrayList<RandomObject> randomObjects;
	
	Hosting host;
	
	public boolean sendData = false;

	private int removeObjectIndex;
	
	public RandomObjectsControlerHost(Hosting host) {

		this.host = host;
		
		randomObjects = new ArrayList<RandomObject>();
	}
	
	public void startRandom(){
		
		Timer.schedule(new Task(){

			@Override
			public void run() {
			
				if(randomObjects.size() < 10){
					
					switch (MathUtils.random(1,4)) {
					
						case 1:
						
							randomObjects.add(new SpeedBoster()); 
							
						
							break;
							
						case 2:
							
							randomObjects.add(new SlowPlayerObject());
							
						
							break;
							
						case 3:
							
							randomObjects.add(new EscapeBoost()); 
							
						
							break;
							
						case 4:
							
							randomObjects.add(new TimeBoost()); 
							
						
							break;
						
						default:
							break;
					}	
					
					sendData = true;
				}	
			}
		}, 1, 5);			
	}	

	public String GetObjectsData(){
		
		ArrayList<RandomObjectData> randomObjectsData = new ArrayList<RandomObjectData>();
		
		for (RandomObject randomObject : randomObjects) {
			
			randomObjectsData.add( new RandomObjectData( randomObject.x, randomObject.y, randomObject.getType() ) );			
		}
		
/*		try {
			
			
			return ClassSerializer.toString(randomObjectsData);

		}catch (IOException e) {
			
			e.printStackTrace();
			
			System.out.println("b³¹d serializacji");
		}*/
		
		return null;				
	}

	public void refreschObjects(){
		
		removeObjectIndex = -1;

		for (RandomObject randomObject : randomObjects) {
			
			for (Player player : host.players) {
				
				if(player.overlaps(randomObject)){
					
					randomObject.addEffectsToPlayers(player, player, host);	
					
					randomObject.exist = false;
				}
			}
			
			if(!randomObject.exist){
				removeObjectIndex = randomObjects.indexOf(randomObject);
				
				sendData = true;
			}
		}	

		if(removeObjectIndex != -1){
			
			randomObjects.remove(removeObjectIndex);
		}
	}
}
