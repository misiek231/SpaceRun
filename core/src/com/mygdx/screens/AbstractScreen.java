package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Berek;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;


public abstract class AbstractScreen implements Screen {

	public SpriteBatch batch;
	 
	protected OrthographicCamera camera;
	
	protected Berek game;
	
	protected Stage stage;
	
	Texture background;
	
	public AbstractScreen(Berek game){
		this.game=game;
		
		init();
		
		game.stage = stage;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

		batch.dispose();						
	}
	
	private void init() {
		
		batch = new SpriteBatch();
		
		camera = new OrthographicCamera(Berek.GAME_WIDTH, Berek.GAME_HEIGHT);
		
		stage = new Stage(new StretchViewport(Berek.GAME_WIDTH, Berek.GAME_HEIGHT, camera));
		
		background = new Texture("background7.jpg");
        
		Gdx.input.setInputProcessor(stage);
	}
	
	protected void clearScreen() {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
	}
	
	protected void drawBackground() {
		
		batch.begin();	
		  		
		batch.draw(background, 0, 0, Berek.GAME_WIDTH, Berek.GAME_HEIGHT);
	 	
		batch.end();
		
	}
	
	protected void drawStage() {
		
		stage.act(Gdx.graphics.getDeltaTime());	    
		
		stage.draw();
		
	}
	
	protected void refreschRooms() {
		
		try{
			
			WarpClient.getInstance().getLiveRoomInfo(game.roomId);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
