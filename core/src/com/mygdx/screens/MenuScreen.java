package com.mygdx.screens;

import com.mygdx.game.Berek;
import com.mygdx.gui.MenuGuiController;

public class MenuScreen extends AbstractScreen {

	MenuGuiController guiController;

	public MenuScreen(Berek game) {
		super(game);
		
	}

	@Override
	public void show() {
		
		System.out.println("jestem");
		
		guiController = new MenuGuiController(game);
		
		guiController.init();
		
		guiController.addToStage(stage);

	}



	@Override
	public void render(float delta) {
		
		guiController.searching();

		clearScreen();
			
		drawBackground();
		
		drawStage();
		
		//	fps.setText( Integer.toString( Gdx.graphics.getFramesPerSecond() ) );
	}
		
}
	


	