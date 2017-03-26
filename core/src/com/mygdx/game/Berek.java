package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
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
	
	public Player player1;
	public Player player2;
	
	public Touchpad tuchpad;
	
	public float Knobx2=0;
	public float Knoby2=0;
	
	public float x1=0;
	public float y1=0;
	
	public float x2=0;
	public float y2=0;
	
	public Skin skin;
	public Skin msgskin;
	
	public WarpController warpController;
	
	public boolean server = false;
	
	public String roomId;
	
	public boolean start = false;
	
	public String Name;
	
	public boolean connected = false;
	
	public boolean connectErr;
	
	public boolean userExist;
	
	public String searchedUserName;
	
	public Stage stage;
	
	public boolean wyzwanie;
	
	public String curentRoundTime;
	
	public long startTime;
	
	public Music menuMusic;
	
	public RandomObjectsControler randomObjectsControler;
	
	public Berek(){		
		
		super();		
	}
	
	@Override
	public void create () {
		
		init();		
		
	}
	
	@Override
	public void dispose() {
		
		try{
			
			warpController.dispose();
		
			System.out.println("DelRoom:  " + roomId);
		
			super.dispose();
			
		}catch (Exception e) {
			System.out.println("B³¹d usuwania");
			
			e.printStackTrace();
		}
	}

	public void init() {
						
		msgskin = new Skin(Gdx.files.internal("clean/skin/clean-crispy-ui.json"));	
		
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));		
		
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/backgroundMenuMusic.mp3"));
		menuMusic.setLooping(true);;
		menuMusic.play();
		
		tuchpad = new AnalogStick();
			
		player1 = new Player(true);
		
		player2 = new Player(false);
		
		player1.setPosition(0, 0);
		player2.setPosition(670, 1220);
		
		warpController = new WarpController(this);
		
		this.setScreen(new NameScreen(this));		
	}

	public void BreakGame() {
		
		cancelGame();

		this.setScreen(new GameOverScreen(this));		
	}

	public void cancelGame() {
		

		player1.isBerek = true;
		player2.isBerek = false;
		
		player1.setPosition(0, 0);
		player2.setPosition(0, 0);
		
		x1=0;
		y1=0;
		
		x2=0;
		y2=0;
		
		//server = false;
		
		start = false;
		
		connected = false;
		
		try {
			
			System.out.println("DelRoom: " + roomId);

			WarpClient.getInstance().deleteRoom(roomId);
		
		} catch (Exception e) {
					
			System.out.println("B³¹d usuwania " + "RoomID: " + roomId);
			
			e.printStackTrace();
		}
		
	}
}
