package com.mygdx.random.objects;

import java.io.Serializable;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.hosting.Hosting;
import com.mygdx.players.Player;
import com.mygdx.random.controller.RandomObjectType;


@SuppressWarnings("serial")
public class SlowPlayerObject extends RandomObject implements Serializable{
	
	private final float addSlow = -3f;
	
	private final float workTime = 10f;
	
	public SlowPlayerObject() {

		super(6);
	}
	
	@Override
	public void addEffectsToPlayers(Player playerTouchet, final Player playerNotTouchet, Hosting host) {
		// TODO Auto-generated method stub
		playerNotTouchet.playerSpeed += addSlow;
		
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				playerNotTouchet.playerSpeed -= addSlow;
				
			}
		}, workTime);
	}
	
	@Override
	public int getType() {
		
		return RandomObjectType.SlowPlayerObject;
	}
}
