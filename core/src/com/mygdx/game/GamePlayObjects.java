package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.controler.AnalogStick;
import com.mygdx.players.Player;
import com.mygdx.random.controller.RandomObjectsControllerClient;

public class GamePlayObjects {
	
	public Player player1;
	
	public Player player2;
	
	public AnalogStick touchpad;
	
	public RandomObjectsControllerClient randomObjectsController;
	
	public Label lRoundTime;
	
	Berek game;
	
	public GamePlayObjects(Berek game){
		
		this.game = game;
		
		init();
	}
	
	public void init(){
		
		player1 = new Player(true, game.skin);
		
		player2 = new Player(false, game.skin);
		
		player1.initPlayerNick();
		
		player2.initPlayerNick();
		
		touchpad = new AnalogStick();

		randomObjectsController = new RandomObjectsControllerClient();

		initLRoundTime();
		
	}
	
	private void initLRoundTime() {
		
		lRoundTime = new Label("",game.skin,"big");
		
		lRoundTime.setPosition(Berek.GAME_WIDTH/2 - lRoundTime.getPrefWidth()/2, Berek.GAME_HEIGHT - 100);		
	}
}
