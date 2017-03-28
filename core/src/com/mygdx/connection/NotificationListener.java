package com.mygdx.connection;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.events.ChatEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyData;
import com.shephertz.app42.gaming.multiplayer.client.events.MoveEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.UpdateEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.NotifyListener;

public class NotificationListener implements NotifyListener {

	Berek game;

	
	public NotificationListener(Berek game) {
		this.game = game;
	}

	@Override
	public void onChatReceived(ChatEvent arg0) {
		System.out.println(arg0.getMessage());
	}

	@Override
	public void onGameStarted(String arg0, String arg1, String arg2) {
		System.out.println("onGameStarted");

	}

	@Override
	public void onGameStopped(String arg0, String arg1) {
		System.out.println("onGameStopped");

	}

	@Override
	public void onMoveCompleted(MoveEvent arg0) {
		
		
	}

	@Override
	public void onNextTurnRequest(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrivateChatReceived(String arg0, final String arg1) {

	}

	@Override
	public void onPrivateUpdateReceived(String arg0, byte[] arg1, boolean arg2) {
		
		System.out.println("onPrivateUpdateReceived " + arg1.toString());
		
		
	}

	@Override
	public void onRoomCreated(RoomData arg0) {
		
	}

	@Override
	public void onRoomDestroyed(RoomData arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onUpdatePeersReceived(UpdateEvent arg0) {
		
		System.out.println("onUpdatePeersReceived");

		try {
			
			JSONObject data = new JSONObject(new String( arg0.getUpdate() ) );
			
			if(data.getBoolean("toHost")){

				game.connectionController.hostController.setPlayersData(data.getString("nickName"), (float)data.getDouble("knobX"), (float)data.getDouble("knobY"));

			}else{

				game.gameScreen.gamePlayObjects.player1.setPosition( (float)data.getDouble( game.connectionController.nickName + "x" ), (float)data.getDouble( game.connectionController.nickName + "y" ));

				game.gameScreen.gamePlayObjects.player2.setPosition( (float)data.getDouble( game.gameScreen.gamePlayObjects.player2.nick + "x" ), (float)data.getDouble( game.gameScreen.gamePlayObjects.player2.nick + "y" ));
					
				game.gameScreen.gamePlayObjects.player1.isBerek = (boolean)data.getBoolean(game.connectionController.nickName + "b");
					
				game.gameScreen.gamePlayObjects.player2.isBerek = !game.gameScreen.gamePlayObjects.player2.isBerek;						           
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void onUserChangeRoomProperty(RoomData arg0, String arg1, HashMap<String, Object> arg2,
			HashMap<String, String> arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserJoinedLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserJoinedRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserLeftLobby(LobbyData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserLeftRoom(RoomData arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserPaused(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserResumed(String arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub

	}

}
