package com.mygdx.random.objects;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class RandomObjectsControler {

	Berek game;
	SpriteBatch batch;
	
	int removeObjectIndex = -1;
	
	int randomObjectNumber = 0;
	
	public List<RandomObject> randomObjects;
	
	public RandomObjectsControler(Berek game, SpriteBatch batch) {
		
		this.game = game;
		this.batch = batch;
		
		randomObjects = new ArrayList<RandomObject>();
	}
	
	public void startRandom(){
		
		Timer.schedule(new Task(){

			@Override
			public void run() {
			
				if(randomObjectNumber < 3){
					
					switch (MathUtils.random(1,4)) {
					
						case 1:
						
							SpeedBoster speedBooster = new SpeedBoster(game);
							
							sendObjectsData(speedBooster, 1);
							
							randomObjects.add(speedBooster); 
							
							System.out.println("object added");
						
							break;
							
						case 2:
							
							SlowPlayerObject slowPlayerObject = new SlowPlayerObject(game);
							
							sendObjectsData(slowPlayerObject, 2);
							
							randomObjects.add(slowPlayerObject); 
							System.out.println("object added");
						
							break;
							
						case 3:
							
							EscapeBoost escapeBoost = new EscapeBoost(game);
							
							sendObjectsData(escapeBoost, 3);
							
							randomObjects.add(escapeBoost); 
							System.out.println("object added");
						
							break;
							
						case 4:
							
							TimeBoost timeBoost = new TimeBoost(game);
							
							sendObjectsData(timeBoost, 4);
							
							randomObjects.add( timeBoost); 
							System.out.println("object added");
						
							break;
						
						default:
							break;
					}	
				}	
			}
		}, 1, 20);	
		
	}	
	
	public void drowObjects(){
		
		removeObjectIndex = -1;
		
		System.out.println("ilosc" + randomObjects.size());
		
		for (RandomObject randomObject : randomObjects) {
			
			if(game.player1.overlaps(randomObject)){
				
				randomObject.addEffectsToPlayers(game.player1 , game.player2);	
				randomObject.exist = false;
			}
		
			if(game.player2.overlaps(randomObject)){
				
				randomObject.addEffectsToPlayers(game.player2, game.player1);		
				randomObject.exist = false;
			}
			
			System.out.println("rysowanie " + randomObject.getX());
		
				batch.draw(randomObject.getTexture(), randomObject.getX(), randomObject.getY(), randomObject.getWidth(), randomObject.getHeight());
			
			
			if(!randomObject.exist)
				removeObjectIndex = randomObjects.indexOf(randomObject);

			
			System.out.println(removeObjectIndex);
		}	
		
		if(removeObjectIndex != -1){
			
			randomObjects.remove(removeObjectIndex);
		}
	}	
	
	public void sendObjectsData(RandomObject obj, int type){
		
		
		JSONObject data = new JSONObject(); 
		
		
		
		try {
			data.put("objx", obj.x);
			data.put("objy", obj.x);
			data.put("objtyp", type);
			
			WarpClient.getInstance().sendUDPUpdatePeers(data.toString().getBytes());
			
			System.out.println("obj Wys³any");
			
		}catch (Exception e) {
			System.out.println("b³¹d wysy³u");
		}	
	}
	
	
	
	public void AddObject(float x, float y, int type){
		
		System.out.println("AddObject function ");
		
		switch (type) {
		
		case 1:
					
			randomObjects.add(new SpeedBoster(game, x, y)); 
			
			System.out.println("object added");
		
			break;
			
		case 2:
			
			randomObjects.add(new SlowPlayerObject(game, x, y)); 
			System.out.println("object added");
		
			break;
			
		case 3:
			
			randomObjects.add(new EscapeBoost(game, x, y)); 
			System.out.println("object added");
		
			break;
			
		case 4:
			

			
			randomObjects.add(new TimeBoost(game, x, y)); 
			System.out.println("object added");
		
			break;
		
		default:
			break;
	}	
		
		
	}
}
