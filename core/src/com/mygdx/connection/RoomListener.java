package com.mygdx.connection;

import java.util.HashMap;

import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.LiveRoomInfoEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.RoomRequestListener;

public class RoomListener implements RoomRequestListener {

	Berek berek;
	private String roomId;

	public RoomListener( Berek berek) {
		
		this.berek = berek;
	}

	@Override
	public void onGetLiveRoomInfoDone(LiveRoomInfoEvent arg0) {
		
		try{
		
			if(arg0.getJoinedUsers().length==1){
				
				berek.server = true;
				berek.start = false;
			}
		
			if(berek.start && arg0.getJoinedUsers().length==1){
				
				WarpClient.getInstance().deleteRoom(arg0.getData().getId());
				berek.start = false;
			}
			
		
			if(arg0.getJoinedUsers().length==2){
				
				berek.player1.nick = arg0.getJoinedUsers()[0];
				berek.player2.nick = arg0.getJoinedUsers()[1];
			
				berek.start = true;			
			}
		
			berek.roomId = arg0.getData().getId();
		
		}catch (Exception e) {
			
			berek.start = false;
		}
	}

	@Override
	public void onJoinAndSubscribeRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onJoinRoomDone(RoomEvent event) {
		
		if(event.getResult()==WarpResponseResultCode.SUCCESS){ 
			 
			this.roomId = event.getData().getId();  
		          
			try {
				
				WarpClient.getInstance().subscribeRoom(roomId);
				
				WarpClient.getInstance().getLiveRoomInfo(roomId);
			
			} catch (Exception e) {

				e.printStackTrace();
			} 
		     
		   	System.out.println("roomID " + roomId);
			     
		    System.out.println("Succes Connect");
		      
		}else {
		    		    	
		    if(event.getResult()==WarpResponseResultCode.RESOURCE_NOT_FOUND){
		        
		    	HashMap<String, Object> data = new HashMap<String, Object>();
		    		
		    	data.put("result", "");  
		       
		    	try {
					
		    		WarpClient.getInstance().createRoom("Berek", "Berek", 2, data);
					
					WarpClient.getInstance().joinRoomInRange(0, 2, false);
				
		    	} catch (Exception e) {
					
					e.printStackTrace();
				}  
		    	
		    	berek.server = true;
		    		
		    }else{  
		        
		    	try {
		    		
					WarpClient.getInstance().disconnect();
					berek.BreakGame();
					berek.init();
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}  
		        System.out.println("B£AD " + event.getResult());	        		        
		    }  
		}
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
