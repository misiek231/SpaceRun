package com.mygdx.connection;

import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class WarpController {
	
	//public WarpClient warpClient;
	
	Berek game;

	public WarpController(Berek game){
		
		this.game = game;
		
		initWarp();
		
		try {
			
			addEvents();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	private void initWarp() {
		
		WarpClient.initialize("21f7179644c0942a92b45382f0607de7abfc8a86704878edfb82dfba7e1363ca","34ca1352cb49b74f9c709b4482c997822156cd5e04f295e0559774de64bf837a"); 	
	}

	private void addEvents() throws Exception {
		
		
		
		WarpClient.getInstance().addConnectionRequestListener(new ConListen(game));  
		
		WarpClient.getInstance().addChatRequestListener(new ChatListener()); 
		
		WarpClient.getInstance().addZoneRequestListener(new ZoneListener(game)); 
		
		WarpClient.getInstance().addRoomRequestListener(new RoomListener(game));  
		
		WarpClient.getInstance().addNotificationListener(new NotificationListener(game));
		
	}
	
	public void dispose() throws Exception{  
		  		
		WarpClient.getInstance().deleteRoom(game.roomId);
	
		WarpClient.getInstance().disconnect();	 
	}	
}
