package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.connection.ConnectionController;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.NameScreen;

public class Berek extends Game {
	
	public static final int GAME_WIDTH = 720;
	
	public static final int GAME_HEIGHT = 1280;	//1920×1080
	
	public static final int PlayersInGame = 6;

	public Skin skin;
	
	public ConnectionController connectionController;
	
	public GameScreen gameScreen;
	
	public Berek(){		
		
		super();		
	}
	
	@Override
	public void create () {
		
		init();				
	}
	
	@Override
	public void dispose() {
		
	}

	public void init() {
		
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));	
		
		connectionController = new ConnectionController(this);

		this.setScreen(new NameScreen(this));		
	}
}
