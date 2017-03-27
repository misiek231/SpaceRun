package com.mygdx.game;

import com.mygdx.controler.AnalogStick;
import com.mygdx.players.Player;

public class GamePlayObjects {
	
	public Player player1;
	
	public Player player2;
	
	public AnalogStick touchpad;
	
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
		
	}
}
