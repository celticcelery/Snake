package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class SnakeGame extends ApplicationAdapter implements GestureDetector.GestureListener{

	final static int MS_CONSTANT = 90;
	Timer timer;




	
	@Override
	public void create () {
		Storage.batch = new SpriteBatch();
		Storage.assetManager = new AssetManager();
		Storage.assetManager.load("field_cell.bmp", Texture.class);
		Storage.assetManager.load("snake_head.bmp", Texture.class);
		Storage.assetManager.load("snake_body.bmp", Texture.class);
		Storage.assetManager.load("pizza.bmp", Texture.class);
		Storage.assetManager.load("replay.png", Texture.class);
		Storage.assetManager.load("close.png", Texture.class);
		Storage.assetManager.finishLoading();
		Storage.random = new Random();
		Storage.font   = new BitmapFont();
		Storage.font.getData().setScale(5);
		Storage.field  = new Field();
		Storage.snake  = new Snake();
		Storage.pizza  = new Pizza();
		timer = new Timer(MS_CONSTANT);
		Gdx.input.setInputProcessor(new GestureDetector(new SnakeGame()));
	}

	@Override
	public void render () {

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			Storage.snake.setCurDirection(Snake.Direction.LEFT);
		}else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			Storage.snake.setCurDirection(Snake.Direction.RIGHT);
		}else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			Storage.snake.setCurDirection(Snake.Direction.UP);
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			Storage.snake.setCurDirection(Snake.Direction.DOWN);
		}





		if(timer.isOnTime()){
			Storage.snake.move();
			if(Storage.snake.getHasCrashed()){
				Storage.closeBtn = new CloseBtn(0,0);
				Storage.replayBtn = new ReplayBtn(Gdx.graphics.getWidth() - Storage.assetManager.get("replay.png",Texture.class).getWidth(), 0);

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
		if(Storage.snake.getHasCrashed()){
			Storage.closeBtn.draw();
			Storage.replayBtn.draw();

		}
		Storage.batch.end();

	}
	
	@Override
	public void dispose () {
		Storage.batch.dispose();
		Storage.font.dispose();
		Storage.assetManager.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		y = Gdx.graphics.getHeight() - y;
		if(Storage.closeBtn.isClicked(x, y)){
			System.exit(0);
		}
		if (Storage.replayBtn.isClicked(x, y)) {
			Storage.snake = new Snake();
		}
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if(Math.abs(deltaX) > Math.abs(deltaY)) {
			if(deltaX > 0) {
				Storage.snake.setCurDirection(Snake.Direction.RIGHT);
			} else {
				Storage.snake.setCurDirection(Snake.Direction.LEFT);
			}

		} else if(Math.abs(deltaX) < Math.abs(deltaY)) {
			if(deltaY > 0){
				Storage.snake.setCurDirection(Snake.Direction.DOWN);
			} else {
				Storage.snake.setCurDirection(Snake.Direction.UP);
			}
		} else {
			return false;
		}
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}
}

