package com.mygdx.players;

import com.badlogic.gdx.graphics.Texture;

public class PlayerTexturesInitializer {
	
	public Texture berekTexture;
	
	public Texture noBerekTexture;
	
	public PlayerTexturesInitializer(){
		
		berekTexture = new Texture("berek_circle.png");
		
		noBerekTexture = new Texture("no_berek_circle.png");
	}	
}
