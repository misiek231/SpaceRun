package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;
import com.mygdx.gui.NameScreenGuiController;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;

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
		
		
		if(game.connectionController.connectionResult != 0){
		
			if(game.connectionController.connectionResult == WarpResponseResultCode.SUCCESS){
				
				game.setScreen(new MenuScreen(game));
				
				Gdx.input.setOnscreenKeyboardVisible(false);
				
				stage.unfocusAll();
				
			}else{
				
				guiController.errorType.setVisible(true);
				
				guiController.connecting = false;
				
			}		
		}						
	}
}
