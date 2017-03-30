package com.mygdx.hosting;

import org.json.JSONObject;

import com.mygdx.players.Player;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class Hosting {

	private static final float recoilPower = 5;
	
	public final int ROUND_TIME = 5;
	
	public Player player1;
	
	public Player player2;
	
	int elapsedTime;
	
	String leftGameTime;

	private long startTime;
	
	public int gameState;
	
	public Hosting(){
		
		player1 = new Player(true);
		
		player2 = new Player(false);

		gameState=GameState.NotStarted;
		
		//game.randomObjectsControler = new RandomObjectsControler(game, batch);
		
	}

	public void hostRefresh() {
		
		calculatePosition();
		
		GameTime();

		sendData();  	
	}
	
	public void initPlayersNick(String player1Nick, String player2Nick){
		
		player1.nick = player1Nick;
		
		player2.nick = player2Nick;
		
		System.out.println("player1nick " + player1.nick );
		
		System.out.println("player2nick " + player2.nick );
		
	}
	
	public void setPlayersData(String nick, float knobX, float knobY) {
		
		//System.out.println(nick + " " + knobX+ " " + knobY);
		
		if( player1.nick.equals(nick) ){
			
			player1.knobX = knobX;
			
			player1.knobY = knobY;		
		}
		
		if( player2.nick.equals(nick) ){
			
			player2.knobX = knobX;
			
			player2.knobY = knobY;	
		}		
	}
	
	private void calculatePosition() {
		 
		if(gameState == GameState.Playing){
			
			player1.setX( player1.getX() + player1.knobX * player1.playerSpeed);
			
	    	player1.setY( player1.getY() + player1.knobY * player1.playerSpeed);
	    	
	    	player2.setX( player2.getX() + player2.knobX * player2.playerSpeed);
	    	
	    	player2.setY( player2.getY() + player2.knobY * player2.playerSpeed);
	    	
	    	if(player1.overlaps(player2)){
		    	
		    	player1.isBerek = !player1.isBerek;
		    	
		    	player2.isBerek = !player1.isBerek;
		    	
		    	player1.xTorque = (player1.x - player2.x)/recoilPower;
		    	
		    	player1.yTorque = (player1.y - player2.y)/recoilPower;
		    	
		    	player2.xTorque = -(player1.x - player2.x)/recoilPower;
		    	
		    	player2.yTorque = -(player1.y - player2.y)/recoilPower;
		    }	
	    	
			player1.checkReflection();
			
			player2.checkReflection();
		}		    								
	}
	
	
private void sendData() {

		try { 
		
			JSONObject data = new JSONObject(); 
			
			data.put("toHost", false);
			
			data.put(player1.nick + "x", player1.getX()); 
			
			data.put(player1.nick + "y", player1.getY()); 
			
			data.put(player2.nick + "x", player2.getX()); 
			
			data.put(player2.nick + "y", player2.getY()); 
			
			data.put(player1.nick + "b", player1.isBerek); 
			
			data.put(player2.nick + "b", player2.isBerek); 
			
			data.put("time", leftGameTime);

			//data.put("objects", game.randomObjectsControler.randomObjects);
			
			WarpClient.getInstance().sendUDPUpdatePeers(data.toString().getBytes());			

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
			
			System.out.println(elapsedTime);
			
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
		
		startTime = System.currentTimeMillis();
		
		gameState = GameState.CountingDown;
		
	}

	
}
