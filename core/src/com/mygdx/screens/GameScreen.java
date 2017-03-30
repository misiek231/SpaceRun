package com.mygdx.screens;

import com.mygdx.game.Berek;
import com.mygdx.game.GamePlayObjects;

public class GameScreen extends AbstractScreen{

	public GamePlayObjects gamePlayObjects;
	
	public GameScreen(final Berek game) {
		
		super(game);	
		
		gamePlayObjects = new GamePlayObjects(game);
	}
	

	@Override
	public void show() {

		stage.addActor(gamePlayObjects.touchpad);
		
		stage.addActor(gamePlayObjects.lRoundTime);
		
		stage.addActor(gamePlayObjects.player1.playerNickLabel);
		
		stage.addActor(gamePlayObjects.player2.playerNickLabel);
	}


	@Override
	public void render(float delta) {
		
		if(game.connectionController.host){
			
			game.connectionController.hostController.hostRefresh();			
		}
		
		calculateNamePosition();	

		clearScreen();
		
		drawBackground();
	
		drawBath();
		
		drawStage();
		
		game.connectionController.sendDataToHost(gamePlayObjects.touchpad.getKnobPercentX(), gamePlayObjects.touchpad.getKnobPercentY());
	    	
	}

	private void calculateNamePosition() {
		
		gamePlayObjects.player1.calculateNamePosition();
		
		gamePlayObjects.player2.calculateNamePosition();
		
	}
	
	private void drawBath() {
	
		batch.begin();	

		//game.randomObjectsControler.drowObjects();
			
		batch.draw(gamePlayObjects.player1.getTexture(), gamePlayObjects.player1.getX(), gamePlayObjects.player1.getY(), gamePlayObjects.player1.width, gamePlayObjects.player1.height);
		 	
		batch.draw(gamePlayObjects.player2.getTexture(), gamePlayObjects.player2.getX(), gamePlayObjects.player2.getY(), gamePlayObjects.player2.width, gamePlayObjects.player2.height);
		 
		batch.end();		 
	}
}
