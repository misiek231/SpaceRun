package com.mygdx.connection;

import org.json.JSONObject;

import com.mygdx.game.Berek;
import com.mygdx.hosting.Hosting;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class ConnectionController {

	public boolean host = false;
	
	public String roomId;
	
	public int connectionResult = 100;
	
	public WarpController warpController;
	
	public boolean serverConnect;
	
	public String nickName;
	
	public Hosting hostController;
	
	
	public ConnectionController(Berek game){
		
		warpController = new WarpController(game, this);	
		
		hostController = new Hosting();
	}
	
	public void sendDataToHost(float knobX, float knobY){
		
		try { 
			
			JSONObject data = new JSONObject();  
			
			data.put("toHost", true);
			
			data.put("nickName", nickName);
			
			data.put("knobX", knobX);  
			
			data.put("knobY", knobY); 
			
			//data.put("objects", game.randomObjectsControler.randomObjects);
			
			System.out.println("client wysy³a");
			
			WarpClient.getInstance().sendUDPUpdatePeers(data.toString().getBytes());			
			
			
		} catch (Exception e) {  
			System.out.println("B£AD@@@@@@@@ WYSY£U");
		}  
		
		
	}
	
}
