package com.mygdx.connection;

import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.LobbyEvent;

public class LobbyRequestListener implements com.shephertz.app42.gaming.multiplayer.client.listener.LobbyRequestListener {

	@Override
	public void onGetLiveLobbyInfoDone(LiveRoomInfoEvent arg0) {
		
		arg0.getJoinedUsers()[0];

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
