package com.mygdx.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Berek;
import com.mygdx.game.GamePlayObjects;

public class GameScreen extends AbstractScreen{
	
	Label lRoundTime;

	GamePlayObjects gamePlayObjects;
	
	public GameScreen(final Berek game) {
		
		super(game);	
		
		gamePlayObjects = new GamePlayObjects(game);
	}
	

	@Override
	public void show() {

		initLRoundTime();

		stage.addActor(gamePlayObjects.touchpad);
		
		stage.addActor(lRoundTime);
		
		stage.addActor(gamePlayObjects.player1.playerNickLabel);
		
		stage.addActor(gamePlayObjects.player2.playerNickLabel);
	}


	@Override
	public void render(float delta) {
		
		calculateNamePosition();	

		clearScreen();
		
		drawBackground();
	
		drawBath();
		
		drawStage();
	    	
	}

	private void calculateNamePosition() {
		
		gamePlayObjects.player1.calculateNamePosition();
		
		gamePlayObjects.player2.calculateNamePosition();
		
	}
	
	private void drawBath() {
	
		batch.begin();	

		//game.randomObjectsControler.drowObjects();
	
		//System.out.println("drawing ojects");
			
		batch.draw(gamePlayObjects.player1.getTexture(), gamePlayObjects.player1.getX(), gamePlayObjects.player1.getY(), gamePlayObjects.player1.width, gamePlayObjects.player1.height);
		 	
		batch.draw(gamePlayObjects.player2.getTexture(), gamePlayObjects.player2.getX(), gamePlayObjects.player2.getY(), gamePlayObjects.player2.width, gamePlayObjects.player2.height);
		 
		batch.end();		 
	}

	private void initLRoundTime() {
		
		lRoundTime = new Label("",game.skin,"big");
		
		lRoundTime.setPosition(Berek.GAME_WIDTH/2 - lRoundTime.getPrefWidth()/2, Berek.GAME_HEIGHT - 100);		
	}
}
