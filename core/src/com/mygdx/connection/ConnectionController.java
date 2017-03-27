package com.mygdx.connection;

import com.mygdx.game.Berek;

public class ConnectionController {

	public boolean host = false;
	
	public String roomId;
	
	public int connectionResult = 0;
	
	public WarpController warpController;
	
	public boolean serverConnect;
	
	
	public ConnectionController(Berek game){
		
		warpController = new WarpController(game, this);		
	}
	
}
