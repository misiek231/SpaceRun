package com.mygdx.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;
import com.mygdx.screens.CustumGameScreen;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;
import com.mygdx.screens.OptionsScreen;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class MenuGuiController {
	
	Berek game;
	
	public TextButton btPlay;
	
	public TextButton btCustom;
	
	public TextButton btOpcje;
	
	public TextButton btWyjscie;
	
	public Label lSearching;
	
	boolean searching = false;
	
	int searchingDotCounter = 0;
	
	
	public MenuGuiController(Berek game){
		
		this.game = game;
	}

	public void init(){
		
		initlSearching();
		
		initBtPlay();
			        
		initBtCustom();
		        
		initBtOpcje();
		        
		initBtWyjscie();
		
	}
	
	public void addToStage(Stage stage){
		
		stage.addActor(btPlay);
		
	    stage.addActor(btCustom);
	    
	    stage.addActor(btOpcje);
	    
	    stage.addActor(btWyjscie);
	    
	    stage.addActor(lSearching);		    
	}
	
	private void initBtWyjscie() {
		
		btWyjscie = new TextButton("Wyjœcie",game.skin);
		
		btWyjscie.setPosition(Berek.GAME_WIDTH/2 - btWyjscie.getWidth()/2, 400);
		
		btWyjscie.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				Gdx.app.exit();
				
				super.clicked(event, x, y);
			}	
		});
		
	}

	private void initBtOpcje() {
		
		btOpcje = new TextButton("Opcje",game.skin);
		
		btOpcje.setPosition(Berek.GAME_WIDTH/2 - btOpcje.getWidth()/2, 600);
		
		btOpcje.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				game.setScreen(new OptionsScreen(game));
				
				super.clicked(event, x, y);
			}	
		});
	}

	private void initBtCustom() {
		
		btCustom = new TextButton("Graj ze znajomym",game.skin);
		btCustom.setPosition(Berek.GAME_WIDTH/2 - btCustom.getWidth()/2, 800);
		
		btCustom.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				game.setScreen(new CustumGameScreen(game));
				
				super.clicked(event, x, y);
			}
			
		});		
		
	}

	private void initBtPlay() {
		
		btPlay = new TextButton("Graj",game.skin);
		
		btPlay.setPosition(Berek.GAME_WIDTH/2 - btPlay.getWidth()/2, 1000);
		
		btPlay.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				if(!searching){
					
					try {
				
						game.gameScreen = new GameScreen(game);
						
						game.gameScreen.gamePlayObjects.player1.nick = game.connectionController.nickName;
						
						WarpClient.getInstance().joinLobby();
						
						WarpClient.getInstance().subscribeLobby();
						
						WarpClient.getInstance().getLiveLobbyInfo();
	
					} catch (Exception e) {

						System.out.println("B£AD £ACZENIA");
				
						e.printStackTrace();
					}				
					
					btPlay.setText("Anuluj");
					
					btPlay.setPosition(Berek.GAME_WIDTH/2 - btPlay.getWidth()/2, 1000);
					
					searching = true;
				}
				else{
					
					
					game.setScreen(new MenuScreen(game));
				}
				
				super.clicked(event, x, y);
			}
			
		});		
	}

	private void initlSearching() {
		
		lSearching = new Label("",game.skin,"big");
		
	}
	
	public void searching() {
		
		if(searching){
			
			lSearching.setVisible(true);
			
			if(searchingDotCounter>0)
				lSearching.setText("Szukanie gracza");
			
			if(searchingDotCounter>50)
				lSearching.setText("Szukanie gracza.");
			
			if(searchingDotCounter>100)
				lSearching.setText("Szukanie gracza..");
			
			if(searchingDotCounter>150)
				lSearching.setText("Szukanie gracza...");
			
			if(searchingDotCounter>200) searchingDotCounter = 0;
			
			lSearching.setPosition(Berek.GAME_WIDTH/2 - lSearching.getPrefWidth()/2, 200);
			
			searchingDotCounter++;			
		}	
		else
			lSearching.setVisible(false);
		
	}
}
