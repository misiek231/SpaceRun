package com.mygdx.random.objects;

import java.io.Serializable;

import com.mygdx.hosting.Hosting;
import com.mygdx.players.Player;
import com.mygdx.random.controller.RandomObjectType;


@SuppressWarnings("serial")
public class TimeBoost extends RandomObject implements Serializable{
	
	public TimeBoost(){
	
		super(5);
	}	
	
	@Override
	public void addEffectsToPlayers(Player playerTouchet, Player playerNotTouchet, Hosting host) 
	{
		host.startTime -= 10000;
	}
	
	@Override
	public int getType() {
		
		return RandomObjectType.TimeBost;
	}
}
