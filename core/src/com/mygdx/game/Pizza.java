package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Pizza {
	Texture img = Storage.assetManager.get("pizza.bmp", Texture.class);
	int colIndex, rowIndex;
	Pizza() {
		respawn();
	}
	public void draw(){
		Storage.batch.draw(img, colIndex * img.getWidth() + Field.xOffset, rowIndex * img.getHeight());
	}

	public void respawn(){


//		while(snake.contains(colIndex, rowIndex)){
//			colIndex = random.nextInt(Field.MAX_COL_COUNT);
//			rowIndex = random.nextInt(Field.MAX_ROW_COUNT);
//			snake.contains(colIndex, rowIndex);
//		}
		do {
			colIndex = Storage.random.nextInt(Field.MAX_COL_COUNT);
			rowIndex = Storage.random.nextInt(Field.MAX_ROW_COUNT);
			Storage.snake.contains(colIndex, rowIndex);
		} while (Storage.snake.contains(colIndex, rowIndex));

	}


}
