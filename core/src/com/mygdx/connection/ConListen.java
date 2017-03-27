package com.mygdx.connection;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Berek;
import com.mygdx.screens.MenuScreen;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;

public class ConListen implements ConnectionRequestListener{

	Berek game;

	public ConListen(Berek game) {

		this.game = game;
	}

	@Override      
    public void onConnectDone(ConnectEvent event) {          
    			
		game.connectionController.connectionResult = event.getResult();
		
		if(event.getResult() == WarpResponseResultCode.SUCCESS){                          
    	
			System.out.println("yipee I have connected");    

			try {
				
				WarpClient.getInstance().initUDP();
		
			} catch (Exception e) {
		
				e.printStackTrace();
			} 			
    	}
	}      
   
    @Override      
    public void onDisconnectDone(ConnectEvent event) {
    	System.out.println("On Disconnected invoked");      
    }

	@Override
	public void onInitUDPDone(byte arg0) {
		System.out.println("InitUDPDone"); 
	}  
}
