package com.mygdx.random.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Berek;
import com.mygdx.players.Player;

public abstract class RandomObject extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	Berek game;
	
	Texture texture;
	
	public boolean exist = true;
	
	public RandomObject(Berek game) {		
		
		super();
		
		this.game = game;
		
		setSize(50,50);	
		RandomPosition();
	}

	public void RandomPosition() {

		y = MathUtils.random(100, 1230);
		
		x = MathUtils.random(100, 670);		
	}

	public Texture getTexture() {

		return texture;
	}
	
	public abstract void addEffectsToPlayers( Player playerTouchet, Player playerNotTouchet);	
	
}
