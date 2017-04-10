package com.mygdx.connection;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.mygdx.game.Berek;
import com.mygdx.hosting.GameState;
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
	
	public List<String> potentialPlayersNicks;
	
	
	public ConnectionController(Berek game){
		
		warpController = new WarpController(game, this);	
		
		hostController = new Hosting();
		
		potentialPlayersNicks = new ArrayList<String>();
		
		hostController.gameState = GameState.NotStarted;
	}
	
	public void sendDataToHost(float knobX, float knobY){
		
		try { 
			
			JSONObject data = new JSONObject();  
			
			data.put("toHost", true);
			
			data.put("nickName", nickName);
			
			data.put( "knobX", knobX );  
			
			data.put( "knobY", knobY ); 
			
			WarpClient.getInstance().sendUDPUpdatePeers(data.toString().getBytes());			
						
		} catch (Exception e) {  
			System.out.println("B£AD@@@@@@@@ WYSY£U");
		}  		
	}	
}
