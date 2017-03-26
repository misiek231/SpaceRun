package com.mygdx.random.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Berek;
import com.mygdx.players.Player;

public class TimeBoost extends RandomObject {

	private final float workTime = 10f;
	private final float liveTime = 10f;
	
	public TimeBoost(Berek game) 
	{
		super(game);
		
		init();
	}		

	

	public TimeBoost(Berek game, float x, float y) {
		super(game);
		
		setPosition(x, y);
		
		init();
	}

	private void init() {
		texture = new Texture("randomObjects/TimeBoost.png");
				
				Timer.schedule(new Task() 
				{			
					@Override
					public void run() 
					{
						exist = false;				
					}		
				}, liveTime);
				
			}
	
	@Override
	public void addEffectsToPlayers(Player playerTouchet, Player playerNotTouchet) 
	{
		game.startTime -= 10000;
	}

}
