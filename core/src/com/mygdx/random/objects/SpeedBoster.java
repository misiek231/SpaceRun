package com.mygdx.random.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Berek;
import com.mygdx.players.Player;


public class SpeedBoster extends RandomObject {

	private static final long serialVersionUID = 1L;
	
	
	
	private final float addSpeed = 3f;
	private final float workTime = 10f;
	private final float liveTime = 10f;
	
	
	public SpeedBoster(Berek game) {		
		super(game);
		
		init();
		
	}





	public SpeedBoster(Berek game, float x, float y) {
		super(game);
		
		setPosition(x, y);
		
		init();
	}

	
	private void init() {
		texture = new Texture("randomObjects/SpeedBoost.png");
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				exist = false;
				
			}
		}, liveTime);
		
	}

	@Override
	public void addEffectsToPlayers(final Player playerTouchet, Player playerNotTouchet) {
		
		playerTouchet.playerSpeed += addSpeed;
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				playerTouchet.playerSpeed -= addSpeed;
				
			}
		}, workTime);
		
	}
}
