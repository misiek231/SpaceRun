package com.mygdx.hosting;

import org.json.JSONObject;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Berek;
import com.mygdx.players.Player;
import com.mygdx.random.objects.RandomObjectsControler;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class hosting {

	Berek game;
	
	private static final float recoilPower = 5;
	private static final float recoilSpeed = 1.1f;
	
	float player1xTorque=0;
	float player1yTorque=0;
	
	private boolean roundStart = false;
	
	int elapsedTime;
	
	public hosting(Berek game){
		
		this.game = game;
		
		Player player1 = new Player(true);
		
		Player player2 = new Player(false);
		
		//game.randomObjectsControler = new RandomObjectsControler(game, batch);
		
	}
	
	

	public void hostRefresh(float delta) {
					
		
		//refreschRooms();
		
		getPlayersData();
		
		refreschRooms();
				
		checkStart();
		
		calculatePosition();
		
		calculateNamePosition();	
		
	
			
		
		if(!game.host){
			
			lRoundTime.setText(game.curentRoundTime);
		}
		
		game.player1.win = !game.player1.isBerek;
		
		game.player2.win = !game.player1.win;
		
		game.player1.checkReflection();
		
		game.player2.checkReflection();

		sendData();
	    	
	}
	
	private void getPlayersData() {
		
		
	}

	private void checkStart() {
		
		if(!game.start)
			game.BreakGame();		
	}
	
	private void calculatePosition() {
		 
		
		if(game.host){
			
			if(roundStart){
				
				game.player1.setX( game.player1.getX() + game.tuchpad.getKnobPercentX() * game.player1.playerSpeed);
		    	game.player1.setY( game.player1.getY() + game.tuchpad.getKnobPercentY() * game.player1.playerSpeed);
		    	
		    	game.player2.setX( game.player2.getX() + game.Knobx2 * game.player2.playerSpeed);
		    	game.player2.setY( game.player2.getY() + game.Knoby2 * game.player2.playerSpeed);	
		    		    	
		    	//System.out.println("Knobx: " + game.Knobx2);
		    	//System.out.println("Knoby: " + game.Knoby2);
		    	
		    	if(game.player1.overlaps(game.player2)){
			    	
			    	game.player1.isBerek = !game.player1.isBerek;
			    	game.player2.isBerek = !game.player1.isBerek;
			    	
			    	player1xTorque = (game.player1.x - game.player2.x)/recoilPower;
			    	player1yTorque = (game.player1.y - game.player2.y)/recoilPower;
			    	
			    	soundCollision.play();
			    }
		    	
		    	
		    	if(player1xTorque > 1 || player1xTorque < -1)	    
		 	    	player1xTorque = player1xTorque/recoilSpeed;	    
		 	    else
		 	    	player1xTorque=0;
		 	    
		 	    if(player1yTorque > 1 || player1yTorque < -1)		    
		 	    	player1yTorque = player1yTorque/recoilSpeed;	    
		 	    else
		 	    	player1yTorque=0;
		    	
		 	
		 	    
		 	    	game.player1.x += player1xTorque;
		 	    	game.player1.y += player1yTorque;
		  		
		 	    	game.player2.x += -player1xTorque;
		 	    	game.player2.y += -player1yTorque; 
				
			}	
			
		}else{	    	
			    	
			game.player1.setX( game.x1);
			game.player1.setY( game.y1);
			    	
			game.player2.setX( game.x2);
			game.player2.setY( game.y2);		    	
		}
	}
	
	
private void sendData() {
		
		if(game.host){
			try { 
				
				JSONObject data = new JSONObject();  
				
				data.put("x1", game.player1.getX());  
				data.put("y1", game.player1.getY());  
				data.put("x2", game.player2.getX());  
				data.put("y2", game.player2.getY());  
				data.put("b1", game.player1.isBerek); 
				data.put("time", lRoundTime.getText());
				//data.put("objects", game.randomObjectsControler.randomObjects);
				
				System.out.println("Server wysy³a");
				WarpClient.getInstance().sendUDPUpdatePeers(data.toString().getBytes());			
				
				WarpClient.getInstance().getl
				
			} catch (Exception e) {  
				System.out.println("B£AD@@@@@@@@ WYSY£U");
			}  
		}
		else{
			
			try { 
				
				JSONObject data = new JSONObject();  
				
				data.put("knobX", game.tuchpad.getKnobPercentX());  
				data.put("knobY", game.tuchpad.getKnobPercentY());  				  
						
				WarpClient.getInstance().sendUDPUpdatePeers(data.toString().getBytes());
				System.out.println("Client wysy³a");	
			} catch (Exception e) {  
				System.out.println("B£AD@@@@@@@@ WYSY£U");
			}  				
		}		
	}
	
	private void GameTime() {
		
		if(game.host){
			
			game.startTime = System.currentTimeMillis();
			
			Timer.schedule(new Task(){

				@Override
				public void run() {
					
					elapsedTime = (int)( ( System.currentTimeMillis() - game.startTime ) /1000 );
					
					lRoundTime.setText(Integer.toString( 3 - elapsedTime ) );
					
					
					
					if(elapsedTime >= 3){
						
						game.startTime = System.currentTimeMillis();
						
						
						
						Timer.schedule(new Task(){

							@Override
							public void run() {

								if(!game.start)
									this.cancel();
								
								System.out.println("startTime" + game.startTime);
								
								elapsedTime = (int)( (System.currentTimeMillis() - game.startTime)/1000 );
							
								String s = Integer.toString( ( game.ROUND_TIME * 60 - elapsedTime )%60 );
								
								if( ( (game.ROUND_TIME * 60 - elapsedTime ) % 60 ) < 10){

									s = "0" + s;
								}
								
								lRoundTime.setText( Integer.toString( ( game.ROUND_TIME * 60 - elapsedTime )/60 ) + ":" + s );
										
								lRoundTime.setPosition(Berek.GAME_WIDTH/2 - lRoundTime.getPrefWidth()/2, Berek.GAME_HEIGHT - 100);
								
							
								
								if(elapsedTime >= game.ROUND_TIME * 60){
									
									game.BreakGame();
									this.cancel();
								}
							}						
						}, 0, 0.1f);
						
						roundStart = true;
						this.cancel();
					}
				}

						
			}, 0, 0.1f);		
		}
		else{
			
			
			game.startTime = System.currentTimeMillis();
			
			Timer.schedule(new Task(){

				@Override
				public void run() {
					
					elapsedTime = (int)( ( System.currentTimeMillis() - game.startTime ) /1000 );
					
					if(elapsedTime >= 4){

						roundStart = true;
						game.randomObjectsControler.startRandom();
						this.cancel();
					}
				}

						
			}, 0, 0.1f);		
			
		}
		
	}
	
}
