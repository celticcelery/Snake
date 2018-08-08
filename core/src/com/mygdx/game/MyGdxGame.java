package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

	final static int MS_CONSTANT = 100;
	Timer timer;




	
	@Override
	public void create () {
		Storage.batch = new SpriteBatch();
		Storage.assetManager = new AssetManager();
		Storage.assetManager.load("field_cell.bmp", Texture.class);
		Storage.assetManager.load("snake_head.bmp", Texture.class);
		Storage.assetManager.load("snake_body.bmp", Texture.class);
		Storage.assetManager.load("pizza.bmp", Texture.class);
		Storage.assetManager.finishLoading();
		Storage.random = new Random();
		Storage.font   = new BitmapFont();
		Storage.font.getData().setScale(5);
		Storage.field  = new Field();
		Storage.snake  = new Snake();
		Storage.pizza  = new Pizza();
		timer = new Timer(MS_CONSTANT);
	}

	@Override
	public void render () {

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			Storage.snake.setDirection(Snake.Direction.LEFT);
		}else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			Storage.snake.setDirection(Snake.Direction.RIGHT);
		}else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			Storage.snake.setDirection(Snake.Direction.UP);
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			Storage.snake.setDirection(Snake.Direction.DOWN);
		}




		if(timer.isOnTime()){
			Storage.snake.move();
			if(Storage.snake.getHasCrashed()){
				System.out.println("Game Over");
			}
		}

		if(Storage.snake.contains(Storage.pizza.colIndex, Storage.pizza.rowIndex)){
			Storage.snake.eatPizza();
			Storage.pizza.respawn();

		}



		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Storage.batch.begin();
		Storage.field.draw();
		Storage.snake.draw();
		Storage.pizza.draw();
		Storage.font.draw(Storage.batch, "pizza count: " + Storage.snake.getEatenPizzasCount() + " cells moved: " + Storage.snake.getSnakeCellsMoved(), 0, Gdx.graphics.getHeight());
		Storage.batch.end();
	}
	
	@Override
	public void dispose () {
		Storage.batch.dispose();
		Storage.font.dispose();
		Storage.assetManager.dispose();
	}

}

