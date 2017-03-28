package com.mygdx.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class NameScreenGuiController {
	
	Berek game;
	
	public TextField name;
	
	public TextButton setName;
	
	public Label errorType;
	
	public Label lConnecting;
	
	public boolean connecting = false;
	
	public int searchingDotCounter = 0;
	
	public NameScreenGuiController(Berek game){
		
		this.game = game;
	}
	
	public void initControls() {;
		
		initName();
		
		initUsedName();
		
		initSetName();
		
		initLconnecting();
 
	}
	
	public void addToStage(Stage stage){
		
		stage.addActor(name);
		
		stage.addActor(errorType);
		
		stage.addActor(setName);
		
		stage.addActor(lConnecting); 	    
	}
	
	private void initLconnecting() {
		lConnecting = new Label("",game.skin,"big");
		
	}

	private void initSetName() {
		
		setName = new TextButton("Zatwierdz", game.skin);
		
		setName.setPosition(Berek.GAME_WIDTH/2 - setName.getWidth()/2, name.getY()  - 200);
		
		setName.addListener(new ClickListener(){
			
			@Override
			public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {

				Gdx.input.setOnscreenKeyboardVisible(false);
			
				if(!connecting){
				
					connecting = true;
					
					
				
					try {
					
						
						game.connectionController.nickName = name.getText();
						
						WarpClient.getInstance().connectWithUserName(name.getText());
						
					//game.setScreen(new GameScreen(game));
					} catch (Exception e) {

						System.out.println("B£AD £ACZENIA");
						
						errorType.setVisible(true);
					}
				}
				
				super.clicked(event, x, y);
			};			
		});
		
	}

	private void initUsedName() {
		
		errorType = new Label("B³¹d laczenia", game.skin);
		
		errorType.setPosition(Berek.GAME_WIDTH/2 - errorType.getWidth()/2, name.getY() - 30);
		
		errorType.setColor(Color.RED);
		
		errorType.setVisible(false);
		
	}

	private void initName() {
		
		name = new TextField("", game.skin);	
		
		name.setSize(300, 50);
		
		name.setPosition(Berek.GAME_WIDTH/2 - name.getWidth()/2, Berek.GAME_HEIGHT - 100);				
		
	}
	
	public void connecting() {

	
		
		if(connecting){
			
			lConnecting.setVisible(true);
			
			if(searchingDotCounter>0)
				lConnecting.setText("Laczenie");
			
			if(searchingDotCounter>50)
				lConnecting.setText("Laczenie.");
			
			if(searchingDotCounter>100)
				lConnecting.setText("Laczenie..");
			
			if(searchingDotCounter>150)
				lConnecting.setText("Laczenie...");
			
			if(searchingDotCounter>200) searchingDotCounter = 0;
			
			lConnecting.setPosition(Berek.GAME_WIDTH/2 - lConnecting.getPrefWidth()/2, 200);
			
			searchingDotCounter++;			
	
		}else{
			
			lConnecting.setVisible(false);
		}		
	}

}
