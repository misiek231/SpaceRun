package com.mygdx.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundsControler {
	
	public Music menuMusic;
	
	public void init(){
		
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/backgroundMenuMusic.mp3"));
		
		menuMusic.setLooping(true);
		
		menuMusic.play();
	}

}
