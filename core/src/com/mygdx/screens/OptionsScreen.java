package com.mygdx.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Berek;

public class OptionsScreen extends AbstractScreen {
	
	TextButton resetName;
	TextButton menu;
	
	Label labelMasterVolume;
	Slider masterVolume;
	
	Label labelMusicVolume;
	Slider musicVolume;

	Label labelSoundsVolume;
	Slider soundsVolume;
	
	public OptionsScreen(final Berek game) {
		super(game);
		
		labelMasterVolume = new Label("Glosnosc", game.skin, "big");		
		labelMasterVolume.setFontScale(0.8f);		
		labelMasterVolume.setPosition(Berek.GAME_WIDTH/2 - labelMasterVolume.getPrefWidth()/2 , Berek.GAME_HEIGHT - 100);
		
		masterVolume = new Slider(0, 10, 0.1f, false, game.skin);
		masterVolume.setWidth(600);
		masterVolume.setPosition(Berek.GAME_WIDTH/2 - masterVolume.getWidth()/2 , Berek.GAME_HEIGHT - 150);
		
		
		labelMusicVolume = new Label("Glosnosc muzyki", game.skin, "big");
		labelMusicVolume.setFontScale(0.8f);
		labelMusicVolume.setPosition(Berek.GAME_WIDTH/2 - labelMusicVolume.getPrefWidth()/2 , Berek.GAME_HEIGHT - 250);
		
		musicVolume = new Slider(0, 10, 0.1f, false, game.skin);
		musicVolume.setWidth(600);
		musicVolume.setPosition(Berek.GAME_WIDTH/2 - musicVolume.getWidth()/2 , Berek.GAME_HEIGHT - 300);
		
		
		labelSoundsVolume = new Label("Glosnosc dzwiekow", game.skin, "big");	
		labelSoundsVolume.setFontScale(0.8f);
		labelSoundsVolume.setPosition(Berek.GAME_WIDTH/2 - labelSoundsVolume.getPrefWidth()/2 , Berek.GAME_HEIGHT - 400);
		
		soundsVolume = new Slider(0, 10, 0.1f, false, game.skin);
		soundsVolume.setWidth(600);
		soundsVolume.setPosition(Berek.GAME_WIDTH/2 - soundsVolume.getWidth()/2 , Berek.GAME_HEIGHT - 450);
		
		
		resetName = new TextButton("Zmien nazwe", game.skin);
		resetName.setPosition(Berek.GAME_WIDTH/2 - resetName.getPrefWidth()/2 , Berek.GAME_HEIGHT - 600);
		resetName.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.BreakGame();
				game.dispose();
				game.setScreen(new NameScreen(game));
				super.clicked(event, x, y);
			}			
		});
		
		
		menu = new TextButton("Menu", game.skin);		
		menu.setPosition( Berek.GAME_WIDTH / 2 - menu.getPrefWidth()/2, Berek.GAME_HEIGHT - 750);		
		menu.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MenuScreen(game));
				super.clicked(event, x, y);
			}			
		});
		
		
		stage.addActor(labelMasterVolume);
		stage.addActor(masterVolume);
		
		stage.addActor(labelMusicVolume);
		stage.addActor(musicVolume);
		
		stage.addActor(labelSoundsVolume);
		stage.addActor(soundsVolume);
		
		stage.addActor(resetName);
		stage.addActor(menu);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		
		clearScreen();
			
		drawBackground();
		
		drawStage();
		
	}

}
