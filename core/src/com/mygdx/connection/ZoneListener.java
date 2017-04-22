package com.mygdx.connection;

import com.mygdx.game.Berek;

public class ZoneListener {

	Berek game;
	
	public ZoneListener(Berek game) {
		this.game = game;
	}
}
	/*@Override
	public void onCreateRoomDone(RoomEvent arg0) {
		
		try {
					
			System.out.println("onCreateRoomDone");
						
			for (String potentialPlayerNick : game.connectionController.potentialPlayersNicks) {
					
				WarpClient.getInstance().sendPrivateChat(potentialPlayerNick, "game," + arg0.getData().getId());	
					
				System.out.println("invite player: " + potentialPlayerNick);
			}		
			
		} catch (Exception e) {

			e.printStackTrace();
		}  
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
*/