package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Berek;
import com.mygdx.gui.NameScreenGuiController;

public class NameScreen extends AbstractScreen {

	NameScreenGuiController guiController;
	
	public NameScreen(Berek game) {		
		super(game);
		
		guiController = new NameScreenGuiController(game);
		
		guiController.initControls();		
		
		guiController.addToStage(stage);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		
		guiController.connecting();
		
		connectingResult();
		
		
		clearScreen();
		
		drawBackground();		
		
		drawStage();

	}

	private void connectingResult() {
		
		if(game.connectionController.connectionResult != 100){
			
			if(game.connectionController.connectionResult == 1){
				
				game.setScreen(new MenuScreen(game));
				
				Gdx.input.setOnscreenKeyboardVisible(false);
				
				stage.unfocusAll();
				
			}else{
				
				if(game.connectionController.connectionResult == 2){
					
					guiController.errorType.setText("Nick zajêty. Spróbuj ponownie");
				}
				
				if(game.connectionController.connectionResult == 3){
					
					guiController.errorType.setText("Unknown host: 192.168.56.1");
				}
				
				if(game.connectionController.connectionResult == 4){
					
					guiController.errorType.setText("No I/O");
				}
				
				guiController.errorType.setVisible(true);
				
				guiController.connecting = false;		
			}
		}
	}
}
