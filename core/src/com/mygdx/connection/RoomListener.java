package com.mygdx.connection;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;


public class RoomListener implements RoomRequestListener {

	Berek game;

	public RoomListener( Berek game) {
		
		this.game = game;
	}

	@Override
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent arg0) {

	}

	@Override
	public void onJoinAndSubscribeRoomDone(RoomEvent arg0) {

		System.out.println("onJoinAndSubscribeRoomDone");
		
		if(arg0.getResult()==WarpResponseResultCode.SUCCESS){ 
			
			if(game.connectionController.host){
	
				try {
					
					WarpClient.getInstance().sendPrivateChat(game.gameScreen.gamePlayObjects.player2.nick, "game," + arg0.getData().getId());
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}		
			}else{
				
				try {
					
					WarpClient.getInstance().sendPrivateChat(game.gameScreen.gamePlayObjects.player2.nick, "gameOk");
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}	
				
				Timer.schedule(new Task() {
					
					@Override
					public void run() {
						game.setScreen(game.gameScreen);
						
					}
	
				}, 1);
			}
			
		}else {
  
		}		
	}

	@Override
	public void onJoinRoomDone(RoomEvent event) {
		
	}

	@Override
	public void onLeaveAndUnsubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLeaveRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSetCustomRoomDataDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSubscribeRoomDone(RoomEvent arg0) {
		System.out.println("onSubscribeRoomDone");
		   
	}

	@Override
	public void onUnSubscribeRoomDone(RoomEvent arg0) {
		
		
	}

	@Override
	public void onUnlockPropertiesDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdatePropertyDone(LiveRoomInfoEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
