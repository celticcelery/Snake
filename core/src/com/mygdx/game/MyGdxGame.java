package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {

	Texture img, imgOfSnakeBody, imgOfPizza;
	float msElapsed;
	final static int MS_CONSTANT = 150;
	Timer timer;




	
	@Override
	public void create () {
		Storage.batch = new SpriteBatch();
		img = new Texture("field_cell.bmp");
		imgOfSnakeBody = new Texture("snake_body.bmp");
		imgOfPizza = new Texture("pizza.bmp");
		Storage.random = new Random();
		Storage.font   = new BitmapFont();
		Storage.font.getData().setScale(5);
		Storage.field  = new Field(img);
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
		}

		if(Storage.snake.contains(Storage.pizza.colIndex, Storage.pizza.rowIndex)){
			Storage.snake.eatPizza();
			Storage.pizza.respawn();

		}



		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Storage.batch.begin();
		Storage.field.draw();
		Storage.snake.draw(imgOfSnakeBody);
		Storage.pizza.draw(imgOfPizza);
		Storage.font.draw(Storage.batch, "pizza count: " + Storage.snake.getEatenPizzasCount() + " cells moved: " + Storage.snake.getSnakeCellsMoved(), 0, Gdx.graphics.getHeight());
		Storage.batch.end();
	}
	
	@Override
	public void dispose () {
		Storage.batch.dispose();
		img.dispose();
		imgOfSnakeBody.dispose();
		imgOfPizza.dispose();
		Storage.font.dispose();
	}

}

