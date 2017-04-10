package com.mygdx.hosting;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.mygdx.players.Player;
import com.mygdx.random.controller.RandomObjectsControlerHost;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class Hosting{

	private static final float recoilPower = 5;
	
	public final int ROUND_TIME = 5;
	
	public List<Player> players;

	int elapsedTime;
	
	String leftGameTime;

	public long startTime;
	
	public int gameState;
	
	RandomObjectsControlerHost randomObjectsControler;
	
	public Hosting(){
		
		players = new ArrayList<Player>();
		
		randomObjectsControler = new RandomObjectsControlerHost(this);

		gameState = GameState.NotStarted;
	}

	public void hostRefresh() {
		
		calculatePosition();
		
		refreschRandomObjects();

		GameTime();

		sendData();  	
	}
	
	private void refreschRandomObjects() {
		
		randomObjectsControler.refreschObjects();		
	}
	
	public void setPlayersData(String nick, float knobX, float knobY) {
		
		//System.out.println(arg0);
		
		for (Player player : players) {
			
			if( player.nick.equals(nick) ){
				
				player.knobX = knobX;
				
				player.knobY = knobY;					
			}
		}		
	}
	
	private void calculatePosition() {
		 
		if(gameState == GameState.Playing){
			
			for (Player player : players) {
			
				player.setX( player.getX() + player.knobX * player.playerSpeed );
			
				player.setY( player.getY() + player.knobY * player.playerSpeed );
	    		
			}
			
	    	
	    	/*if(player1.overlaps(player2)){
		    	
		    	player1.isBerek = !player1.isBerek;
		    	
		    	player2.isBerek = !player1.isBerek;
		    	
		    	player1.xTorque = (player1.x - player2.x)/recoilPower;
		    	
		    	player1.yTorque = (player1.y - player2.y)/recoilPower;
		    	
		    	player2.xTorque = -(player1.x - player2.x)/recoilPower;
		    	
		    	player2.yTorque = -(player1.y - player2.y)/recoilPower;
		    }	*/
	    	
	    	for (Player player : players) {
	    		
	    		player.checkReflection();
	    	}
		}		    								
	}
		
	private void sendData() {

		try { 
		
			JSONObject data = new JSONObject(); 
			
			data.put("toHost", false);
			
			for (Player player : players) {
			
				data.put(player.nick + "x", player.getX()); 
			
				data.put(player.nick + "y", player.getY()); 
				
				data.put(player.nick + "b", player.isBerek);
			
			}
			
			data.put("time", leftGameTime);
			
			data.put("isObjects", randomObjectsControler.sendData );
			
			if(randomObjectsControler.sendData == true){

				data.put("objects", randomObjectsControler.GetObjectsData() );
				
				randomObjectsControler.sendData = false;			
			}
			
			WarpClient.getInstance().sendUDPUpdatePeers( data.toString().getBytes() );			

		} catch (Exception e) {  
			System.out.println("B£AD@@@@@@@@ WYSY£U");
		} 		
	}

	private void GameTime() {

		if(gameState == GameState.CountingDown){
	
			countingDownTime();				
		}	

		if(gameState == GameState.Playing){
	
			countingTime();			
		}
	}

	private void countingDownTime(){
				
		elapsedTime = (int)( ( System.currentTimeMillis() - startTime ) /1000 );
		
		leftGameTime = Integer.toString(3 - elapsedTime);
		
		if(elapsedTime >= 3){

			randomObjectsControler.startRandom();
			
			startTime = System.currentTimeMillis();
			
			gameState = GameState.Playing;		
		}		
	}
	
	private void countingTime(){
		
		elapsedTime = (int)( (System.currentTimeMillis() - startTime)/1000 );
	
		String s = Integer.toString( ( ROUND_TIME * 60 - elapsedTime )%60 );
		
		if( ( (ROUND_TIME * 60 - elapsedTime ) % 60 ) < 10){

			s = "0" + s;
		}
		
		leftGameTime = Integer.toString( ( ROUND_TIME * 60 - elapsedTime )/60 ) + ":" + s;

		if(elapsedTime >= ROUND_TIME * 60){
			
			gameState = GameState.GameBreak;									
		}		
	}

	public void startGame() {
		
		String textToSend = "StartGame";
		
		for (Player player : players) {
			
			textToSend += "," + player.nick;			
		}
				
		try {
			
			WarpClient.getInstance().sendChat(textToSend);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		startTime = System.currentTimeMillis();
		
		gameState = GameState.CountingDown;

	}
}
