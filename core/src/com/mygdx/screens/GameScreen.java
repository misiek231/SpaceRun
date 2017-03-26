package com.mygdx.screens;

import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Berek;
import com.mygdx.random.objects.RandomObjectsControler;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class GameScreen extends AbstractScreen{

	float player1xTorque=0;
	float player1yTorque=0;
	
	private static final float recoilPower = 5;
	private static final float recoilSpeed = 1.1f;
	
	
	
	
	int elapsedTime;
	
	Label lRoundTime;
	
	Label player1Nick;
	
	Label player2Nick;
	
	
	
	private boolean roundStart = false;
	
	private Music gameMusic;
	
	private Sound soundCollision;
		
	public GameScreen(final Berek game) {
		super(game);
		
		
	}
	

	@Override
	public void show() {

		game.randomObjectsControler = new RandomObjectsControler(game, batch);
						
		initPlayer1Nick();

		initPlayer2Nick();

		initLRoundTime();
		
		GameTime();	
		
		stage.addActor(game.tuchpad);
		stage.addActor(lRoundTime);
		stage.addActor(player1Nick);
		stage.addActor(player2Nick);
		
		game.menuMusic.stop();
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/backgroundGameMusic.mp3"));
		gameMusic.setLooping(true);;
		gameMusic.play();
		
		soundCollision = Gdx.audio.newSound(Gdx.files.internal("music/soundCollision.mp3"));
	}


	@Override
	public void render(float delta) {
						
		refreschRooms();
				
		checkStart();
		
		calculatePosition();
		
		calculateNamePosition();	
		
	
			
		
		if(!game.server){
			
			lRoundTime.setText(game.curentRoundTime);
		}
		
		game.player1.win = !game.player1.isBerek;
		
		game.player2.win = !game.player1.win;
		
		game.player1.checkReflection();
		
		game.player2.checkReflection();
		
		
		
		clearScreen();
		
		drawBackground();
	
		drawBath();
		
		drawStage();

		sendData();
	    	
	}





	private void calculateNamePosition() {
		
		player1Nick.setPosition(game.player1.getX() + game.player1.width/2 - player1Nick.getPrefWidth()/2, game.player1.getY() - 40 );
		
		player2Nick.setPosition(game.player2.getX() + game.player2.width/2 - player2Nick.getPrefWidth()/2, game.player2.getY() - 40 );		
	}

	private void checkStart() {
		
		if(!game.start)
			game.BreakGame();		
	}

	private void drawBath() {
		
		
		
		batch.begin();	
		
			if(roundStart){
		
				game.randomObjectsControler.drowObjects();
	
				System.out.println("drawing ojects");
			}
		 	batch.draw(game.player1.getTexture(), game.player1.getX(), game.player1.getY(), game.player1.width, game.player1.height);
		 	batch.draw(game.player2.getTexture(), game.player2.getX(), game.player2.getY(), game.player2.width, game.player2.height);
		 
		 batch.end();		 
	}

	private void calculatePosition() {
		 
			
		if(game.server){
			
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
		
		if(game.server){
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
		
		if(game.server){
			
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
	


	private void initLRoundTime() {
		lRoundTime = new Label("",game.skin,"big");
		
		lRoundTime.setPosition(Berek.GAME_WIDTH/2 - lRoundTime.getPrefWidth()/2, Berek.GAME_HEIGHT - 100);
		
	}

	private void initPlayer2Nick() {
		player2Nick = new Label(game.player2.nick, game.skin);
		
		player2Nick.setPosition(game.player2.getX() - player1Nick.getMinWidth(), game.player2.getY() - 20 );
		
		player2Nick.setFontScale(2f);
		
	}

	private void initPlayer1Nick() {
		player1Nick = new Label(game.player1.nick, game.skin);
		
		player1Nick.setPosition(game.player1.getX() - player1Nick.getWidth() , game.player1.getY() - 20 );
		
		player1Nick.setFontScale(2f);
		
	}

}
