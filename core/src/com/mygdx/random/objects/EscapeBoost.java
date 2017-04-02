package com.mygdx.random.objects;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.hosting.Hosting;
import com.mygdx.players.Player;
import com.mygdx.random.controller.RandomObjectType;

@SuppressWarnings("serial")
public class EscapeBoost extends RandomObject {
	
	
	private final float workTime = 10f;

	public EscapeBoost() {
		
		super(3);
	}	

	@Override
	public void addEffectsToPlayers(final Player playerTouchet, Player playerNotTouchet, Hosting host) {

		playerTouchet.checkReflection = false;
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				playerTouchet.checkReflection = true;
				
			}
		}, workTime);
	}

	@Override
	public int getType() {
		
		return RandomObjectType.EscapeBoost;
	}
}
