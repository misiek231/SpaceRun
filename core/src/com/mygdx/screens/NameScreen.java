package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class NameScreen extends AbstractScreen {

	TextField name;
	
	TextButton setName;
	
	Label usedName;
	
	private Label lConnecting;
	
	boolean connecting = false;
	
	int i = 0;

	public NameScreen(Berek game) {
		
		super(game);
		
		initControls();		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		
		connecting();
		
		connectingResult();
		
		
		clearScreen();
		
		drawBackground();		
		
		drawStage();

	}

	private void connectingResult() {
		
		if(game.connected){
			game.setScreen(new MenuScreen(game));
			Gdx.input.setOnscreenKeyboardVisible(false);
			stage.unfocusAll();
		}
					
		if(game.connectErr){
			
			usedName.setVisible(true);
			
			connecting = false;

		}		
	}

	private void connecting() {

	
		
		if(connecting){
			
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
		
		initName();
		
		initUsedName();
		
		initSetName();
		
		initLconnecting();

		
		stage.addActor(name);
		stage.addActor(usedName);
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
				
				game.Name  = name.getText();
				
				Gdx.input.setOnscreenKeyboardVisible(false);
				
				stage.unfocus(name);
				
				if(!connecting){
				
					connecting = true;
				
					try {
					
						WarpClient.getInstance().connectWithUserName(name.getText());
					//game.setScreen(new GameScreen(game));
					} catch (Exception e) {

						System.out.println("B£AD £ACZENIA");
						
						usedName.setVisible(true);
					}
				}
				
				super.clicked(event, x, y);
			};			
		});
		
	}

	private void initUsedName() {
		
		usedName = new Label("Nazwa zajeta", game.skin);
		
		usedName.setPosition(Berek.GAME_WIDTH/2 - usedName.getWidth()/2, name.getY() - 30);
		
		usedName.setColor(Color.RED);
		
		usedName.setVisible(false);
		
	}

	private void initName() {
		
		name = new TextField("", game.skin);	
		
		name.setSize(300, 50);
		
		name.setPosition(Berek.GAME_WIDTH/2 - name.getWidth()/2, Berek.GAME_HEIGHT - 100);				
		
	}
}
