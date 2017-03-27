package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.mygdx.connection.ConnectionController;
import com.mygdx.connection.WarpController;
import com.mygdx.controler.AnalogStick;
import com.mygdx.players.Player;
import com.mygdx.random.objects.RandomObjectsControler;
import com.mygdx.screens.GameOverScreen;
import com.mygdx.screens.NameScreen;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;

public class Berek extends Game {
	
	public static final int GAME_WIDTH = 720;
	
	public static final int GAME_HEIGHT = 1280;	//1920×1080

	public final int ROUND_TIME = 5;

	public Skin skin;
	
	public ConnectionController connectionController;
	
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
