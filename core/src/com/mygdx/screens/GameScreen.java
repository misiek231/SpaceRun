package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.mygdx.game.Berek;
import com.mygdx.game.GamePlayObjects;
import com.mygdx.hosting.GameState;
import com.mygdx.players.Player;
import com.mygdx.players.PlayerTexturesInitializer;

public class GameScreen extends AbstractScreen{

	public GamePlayObjects gamePlayObjects;
	
	int gameState;
	
	PlayerTexturesInitializer playerTextures;
	
	ParticleEffect pe;
	
	public GameScreen(final Berek game) {
		
		super(game);	
		
		gameState = GameState.NotStarted;
		
		gamePlayObjects = new GamePlayObjects(game);
		
		playerTextures = new PlayerTexturesInitializer();
		
		pe = new ParticleEffect();
	    
		pe.load(Gdx.files.internal("particle"),Gdx.files.internal(""));
	}
	

	@Override
	public void show() {

		stage.addActor(gamePlayObjects.touchpad);
		
		stage.addActor(gamePlayObjects.lRoundTime);

	}


	@Override
	public void render(float delta) {

		
		if(gameState == GameState.Playing){
		
			
			calculateNamePosition();	

			clearScreen();
		
			drawBackground();
	
			drawBath();
		
			drawStage();
			
			/*for (Player player : gamePlayObjects.players) {

				player.checkReflection(pe);					
			}*/
			
		
			//pe.update(delta);
			
			game.connectionController.sendDataToHost(gamePlayObjects.touchpad.getKnobPercentX(), gamePlayObjects.touchpad.getKnobPercentY());

		}	
	}

	private void calculateNamePosition() {
		
		for (Player player : gamePlayObjects.players) {
			
			player.calculateNamePosition();		
		}		
	}
	
	private void drawBath() {
	
		batch.begin();	

		//pe.draw(batch);

		gamePlayObjects.randomObjectsController.drowObjects(batch);
		
		for (Player player : gamePlayObjects.players) {
					
			batch.draw(player.getTexture() , player.getX(), player.getY(), player.width, player.height);	
		}

		batch.end();		 
	}
	

	public void startGame(String[] recivedText) {
		
		gamePlayObjects.players.clear();
		
		
		
		for(int i = 1 ;i < recivedText.length; i++){
						
			System.out.println(recivedText[i]);
			
			gamePlayObjects.players.add( new Player(false, recivedText[i], game.skin, playerTextures));		
		}
		
		for (Player player : gamePlayObjects.players) {
			
			player.initPlayerNick();
			
			stage.addActor(player.playerNickLabel);
		}
	
		gameState = GameState.Playing;
	}

	public void setPlayerData(int i, float x, float y, boolean isBerek) {
		
		
		
		gamePlayObjects.players.get(i).x = x;
		
	//	System.out.println(gamePlayObjects.players.get(i).nick + " x: " + gamePlayObjects.players.get(i).x);
		
		gamePlayObjects.players.get(i).y = y;
		
	//	System.out.println(gamePlayObjects.players.get(i).nick + " y: " + gamePlayObjects.players.get(i).y);
		
		gamePlayObjects.players.get(i).isBerek = isBerek;	
		
	}
}
