package com.mygdx.random.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.hosting.Hosting;
import com.mygdx.players.Player;

@SuppressWarnings("serial")
public abstract class RandomObject extends Rectangle {

	Hosting host;
	
	public boolean exist = true;
	
	protected int liveTime;

	public RandomObject(int liveTime) {				
		super();
		
		this.liveTime = liveTime;
		
		setSize(50,50);
		
		RandomPosition();

		init();
	}

	private void init() {
	
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				
				exist = false;						
			}
		}, liveTime);				
	}

	public void RandomPosition() {

		y = MathUtils.random(300, 1230);
		
		x = MathUtils.random(300, 670);		
	}

	public abstract void addEffectsToPlayers( Player playerTouchet, Player playerNotTouchet, Hosting host);

	public abstract int getType();
	
}
