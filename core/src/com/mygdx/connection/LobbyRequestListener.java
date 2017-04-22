package com.mygdx.connection;

import com.mygdx.game.Berek;

public class LobbyRequestListener{

	Berek game; 
	
	public LobbyRequestListener(Berek game) {
		
		this.game = game;
	}

	
}
	
/*	
	@Override
	public void onGetLiveLobbyInfoDone(LiveRoomInfoEvent arg0) {
				

		if( arg0.getJoinedUsers().length >= Berek.PlayersInGame ){
			
			for(int i = arg0.getJoinedUsers().length - 1 ; i >= arg0.getJoinedUsers().length - Berek.PlayersInGame ; i--){
			
				game.connectionController.potentialPlayersNicks.add( arg0.getJoinedUsers()[i] );			
			}
					
			game.connectionController.host = true;
			
			createRoom();
		
		}
	}

	private void createRoom() {
    	
		HashMap<String, Object> data = new HashMap<String, Object>();
		
    	data.put("result", "");  
       
    	try {
			
    		WarpClient.getInstance().createRoom("Berek", "Berek", Berek.PlayersInGame, data);
		
    	} catch (Exception e) {
			
			e.printStackTrace();
		}  
    	
		
	}

	@Override
	public void onJoinLobbyDone(LobbyEvent arg0) {
		
		System.out.println("onJoinLobbyDone");
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
*/