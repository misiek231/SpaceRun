package com.mygdx.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class CustumGameScreen extends AbstractScreen {

	Label lSearch;
	
	TextField name;
	
	TextButton setName;
	
	Label usedName;
	
	TextButton menu;
	
	private Label lConnecting;
	
	boolean searching = false;
	
	int i = 0;
	
	public CustumGameScreen(Berek game) {
		
		super(game);
		
		initControls();	
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		connecting();
		
		refreschRooms();
		
		connectingResult();
		
		
		clearScreen();
		
		drawBackground();		
		
		drawStage();

	}
	
	private void connectingResult() {
		
		if(game.start)
			game.setScreen(new GameScreen(game));	
	}

	private void connecting() {

		if(searching){
			
			lConnecting.setVisible(true);
			
			if(i>0)
				lConnecting.setText("Laczenie");
			
			if(i>50)
				lConnecting.setText("Laczenie.");
			
			if(i>100)
				lConnecting.setText("Laczenie..");
			
			if(i>150)
				lConnecting.setText("Laczenie...");
			
			if(i>200) i = 0;
			
			lConnecting.setPosition(Berek.GAME_WIDTH/2 - lConnecting.getPrefWidth()/2, 200);
			
			i++;			
	
		}else{
			
			lConnecting.setVisible(false);
		}		
	}

	private void initControls() {
		
		initSearch();
		
		initName();
		
		initUsedName();
		
		initSetName();
		
		initLconnecting();
		
		initMenuButton();

		stage.addActor(lSearch);
		stage.addActor(name);
		stage.addActor(usedName);
		stage.addActor(setName);
		stage.addActor(lConnecting);  
		stage.addActor(menu);
	}
	
	private void initMenuButton() {
		
		menu = new TextButton("Menu", game.skin);
		
		menu.setPosition(Berek.GAME_WIDTH/2 - menu.getPrefWidth()/2, name.getY() - 400);
		
		menu.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MenuScreen(game));
				super.clicked(event, x, y);
			}			
		});
		
	}

	private void initSearch() {
		lSearch = new Label("Podaj nick gracza",game.skin,"big");
		lSearch.setPosition(Berek.GAME_WIDTH/2 - lSearch.getPrefWidth()/2, Berek.GAME_HEIGHT - 100);
	}

	private void initLconnecting() {
		lConnecting = new Label("",game.skin,"big");
		
	}

	private void initSetName() {
		
		setName = new TextButton("Zatwierdz", game.skin);
		
		setName.setPosition(Berek.GAME_WIDTH/2 - setName.getWidth()/2, name.getY()  - 150);
		
		setName.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				try {
					
					game.wyzwanie = true;
					
					game.searchedUserName = name.getText();
					
					WarpClient.getInstance().getOnlineUsers();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			};			
		});		
	}

	private void initUsedName() {
		
		usedName = new Label("Nazwa zajeta", game.skin);
		
		usedName.setPosition(Berek.GAME_WIDTH/2 - usedName.getWidth()/2, name.getY() - 100);
		
		usedName.setColor(Color.RED);
		
		usedName.setVisible(false);		
	}

	private void initName() {
		
		name = new TextField("Nazwa uzytkownika", game.skin);	
		
		name.setSize(300, 50);
		
		name.setPosition(Berek.GAME_WIDTH/2 - name.getWidth()/2, Berek.GAME_HEIGHT - 200);		
	}
}
