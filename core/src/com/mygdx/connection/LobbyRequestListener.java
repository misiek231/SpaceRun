package com.mygdx.connection;

import java.util.HashMap;

import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyEvent;

public class LobbyRequestListener implements com.shephertz.app42.gaming.multiplayer.client.listener.LobbyRequestListener {

	Berek game; 
	
	public LobbyRequestListener(Berek game) {
		
		this.game = game;
	}

	@Override
	public void onGetLiveLobbyInfoDone(LiveRoomInfoEvent arg0) {
				
		if( arg0.getJoinedUsers().length != 0 ){
						
			//game.player2.nick = arg0.getJoinedUsers()[0];
			
			createRoom();
			        
			//game.host = true;
		
		}
	}

	private void createRoom() {
    	
		HashMap<String, Object> data = new HashMap<String, Object>();
		
    	data.put("result", "");  
       
    	try {
			
    		WarpClient.getInstance().createRoom("Berek", "Berek", 2, data);
			
			WarpClient.getInstance().joinRoomInRange(0, 2, false);
		
    	} catch (Exception e) {
			
			e.printStackTrace();
		}  
    	
		
	}

	@Override
	public void onJoinLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeaveLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribeLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnSubscribeLobbyDone(LobbyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
