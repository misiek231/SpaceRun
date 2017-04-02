package com.mygdx.random.objects;

import java.io.Serializable;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.hosting.Hosting;
import com.mygdx.players.Player;
import com.mygdx.random.controller.RandomObjectType;


@SuppressWarnings("serial")
public class SpeedBoster extends RandomObject implements Serializable{
	
	private final float addSpeed = 3f;
	
	private final float workTime = 10f;

	
	public SpeedBoster() {		
		super(5);
	
	}

	@Override
	public void addEffectsToPlayers(final Player playerTouchet, Player playerNotTouchet, Hosting host) {
		
		playerTouchet.playerSpeed += addSpeed;
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				playerTouchet.playerSpeed -= addSpeed;
				
			}
		}, workTime);		
	}
	
	@Override
	public int getType() {
		
		return RandomObjectType.SpeedBoster;
	}
}
