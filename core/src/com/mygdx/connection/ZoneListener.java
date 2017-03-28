package com.mygdx.connection;

import com.mygdx.game.Berek;
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
  
	}

	@Override
	public void onDeleteRoomDone(RoomEvent arg0) {

	}

	@Override
	public void onGetAllRoomsCountDone(AllRoomsEvent arg0) {

	}

	@Override
	public void onGetAllRoomsDone(AllRoomsEvent arg0) {

	}

	@Override
	public void onGetLiveUserInfoDone(LiveUserInfoEvent arg0) {
	
	}

	@Override
	public void onGetMatchedRoomsDone(MatchedRoomsEvent arg0) {

	}

	@Override
	public void onGetOnlineUsersCountDone(AllUsersEvent arg0) {

	}

	@Override
	public void onGetOnlineUsersDone(AllUsersEvent arg0) {
	
	}

	@Override
	public void onGetUserStatusDone(LiveUserInfoEvent arg0) {

	}

	@Override
	public void onSetCustomUserDataDone(LiveUserInfoEvent arg0) {

	}

}
