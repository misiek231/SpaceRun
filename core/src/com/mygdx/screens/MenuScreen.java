package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class MenuScreen extends AbstractScreen {

	private TextButton btPlay;
	
	private TextButton btCustom;
	
	private TextButton btOpcje;
	
	private TextButton btWyjscie;
	
	private Label lSearching;
	
	boolean searching = false;
	
	int i = 0;
	
	public MenuScreen(Berek game) {
		super(game);
		
	}

	@Override
	public void show() {
		
		initlSearching();
		
		initBtPlay();
			        
		initBtCustom();
		        
		initBtOpcje();
		        
		initBtWyjscie();
		
		stage.addActor(btPlay);
	    stage.addActor(btCustom);	        
	    stage.addActor(btOpcje);
	    stage.addActor(btWyjscie);
	    stage.addActor(lSearching);	        
	}



	@Override
	public void render(float delta) {
		
		searching();
		
		refreschRooms();
		
		checkStart();
		
		
		clearScreen();
			
		drawBackground();
		
		drawStage();
		
		//	fps.setText( Integer.toString( Gdx.graphics.getFramesPerSecond() ) );
	}
		
	private void checkStart() {
		
		if(game.start)
			game.setScreen(new GameScreen(game));		
	}
	
	private void searching() {
		
		if(searching){
			
			lSearching.setVisible(true);
			
			if(i>0)
				lSearching.setText("Szukanie gracza");
			
			if(i>50)
				lSearching.setText("Szukanie gracza.");
			
			if(i>100)
				lSearching.setText("Szukanie gracza..");
			
			if(i>150)
				lSearching.setText("Szukanie gracza...");
			
			if(i>200) i = 0;
			
			lSearching.setPosition(Berek.GAME_WIDTH/2 - lSearching.getPrefWidth()/2, 200);
			
			i++;			
		}	
		else
			lSearching.setVisible(false);
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
					game.wyzwanie = false;
						WarpClient.getInstance().joinRoomInRange(0, 2, false);
					
					} catch (Exception e) {

						System.out.println("B£AD £ACZENIA");
				
						e.printStackTrace();
					}				
					btPlay.setText("Anuluj");
					btPlay.setPosition(Berek.GAME_WIDTH/2 - btPlay.getWidth()/2, 1000);
					searching = true;
				}
				else{
					
					game.cancelGame();
					game.setScreen(new MenuScreen(game));
				}
				
				super.clicked(event, x, y);
			}
			
		});		
	}

	private void initlSearching() {
		lSearching = new Label("",game.skin,"big");
		
	}
}
