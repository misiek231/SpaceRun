package com.mygdx.random.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Berek;
import com.mygdx.players.Player;

public class SlowPlayerObject extends RandomObject {
	
	private final float addSlow = -3f;
	private final float workTime = 10f;
	private final float liveTime = 10f;
	

	public SlowPlayerObject(Berek game) {
		super(game);
		init();
		
	}



	public SlowPlayerObject(Berek game, float x, float y) {
		super(game);
		
		setPosition(x, y);
		
		init();
	}

	private void init() {
		
		texture = new Texture("randomObjects/SlowBoost.png");
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				//exist = false;
				
			}
		}, liveTime);
		
	}
	
	@Override
	public void addEffectsToPlayers(Player playerTouchet, final Player playerNotTouchet) {
		// TODO Auto-generated method stub
		playerNotTouchet.playerSpeed += addSlow;
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				playerNotTouchet.playerSpeed -= addSlow;
				
			}
		}, workTime);
	}

}
