package com.mygdx.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;

public class GameOverScreen extends AbstractScreen {

	Label lGameResult;
	
	Label player1Time;
	
	Label player2Time;
	
	TextButton menu;
		
	public GameOverScreen(Berek game) {
		
		super(game);		
	}

	@Override
	public void show() {
		
		lGameResult = new Label("", game.skin,"big");
		
		System.out.println("Game over Player1: " + game.player1.win);
		
		System.out.println("Game over Player2: " + game.player2.win);
		
		if(game.server){
			
			System.out.println("game server true");
			
			if(game.player1.win){
				lGameResult.setText("Gratulacje Wygrales!!");
			}else{
				
				lGameResult.setText("Niestety Przegrales");
			}
		}else{
			
			System.out.println("game server false");
			
			if(game.player2.win){
				lGameResult.setText("Gratulacje Wygrales!!");
			}else{
				
				lGameResult.setText("Niestety Przegrales");
			}			
		}

		lGameResult.setPosition(Berek.GAME_WIDTH/2 - lGameResult.getPrefWidth()/2,Berek.GAME_HEIGHT - 200);
		
		
		menu = new TextButton("Menu", game.skin);
		
		menu.setPosition(Berek.GAME_WIDTH/2 - menu.getPrefWidth()/2,Berek.GAME_HEIGHT - 400);
		
		menu.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MenuScreen(game));
				super.clicked(event, x, y);
			}			
		});
		
		stage.addActor(lGameResult);
		stage.addActor(menu);

	}

	@Override
	public void render(float delta) {

		clearScreen();
		
		drawBackground();		
		
		drawStage();
	}
}
