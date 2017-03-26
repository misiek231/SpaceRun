package com.mygdx.connection;

import java.util.HashMap;

import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.events.AllRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.AllUsersEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveUserInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.MatchedRoomsEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ZoneRequestListener;

public class ZoneListener implements ZoneRequestListener {

	Berek game;
	
	public ZoneListener(Berek game) {
		this.game = game;
	}

	@Override
	public void onCreateRoomDone(RoomEvent arg0) {
        
        game.roomId = arg0.getData().getId();
		
        if(game.wyzwanie){
        
        	try {
			
        		WarpClient.getInstance().joinRoom(game.roomId);
			
        		game.server = true;
			
        		WarpClient.getInstance().sendPrivateChat(game.searchedUserName, "wyzwanie," + game.roomId);
			
        		System.out.println("Wys³ano wyzwanie");
		
        	} catch (Exception e) {
        		System.out.println("RoomJoin False " + game.roomId);
        		e.printStackTrace();
        	};
        }
        System.out.println("CreateRoomDone: " + game.roomId);
        System.out.println("MaxUsers: " + arg0.getData().getMaxUsers());
	}

	@Override
	public void onDeleteRoomDone(RoomEvent arg0) {
		System.out.println("onDeleteRoomDone");

	}

	@Override
	public void onGetAllRoomsCountDone(AllRoomsEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetAllRoomsDone(AllRoomsEvent arg0) {
	
		for (String iterable_element : arg0.getRoomIds()) {
			System.out.println(iterable_element);
		}
	}

	@Override
	public void onGetLiveUserInfoDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetMatchedRoomsDone(MatchedRoomsEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetOnlineUsersCountDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetOnlineUsersDone(AllUsersEvent arg0) {
		// TODO Auto-generated method stub		
		game.userExist = false;
		
		System.out.println("onGetOnlineUsersDone");
		
		for (String name : arg0.getUserNames()) {
			
			if(game.searchedUserName.equals(name))	{
				
				try {
					
					HashMap<String, Object> data = new HashMap<String, Object>();
		    		
			    	data.put("result", "");  
			       
			    	WarpClient.getInstance().createRoom("Berek", "Berek", 2, data);
						

				
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
						
		}
	}

	@Override
	public void onGetUserStatusDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {
		// TODO Auto-generated method stub

	}

}
