package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeCell {
	int colIndex, rowIndex;

	SnakeCell(int colIndex, int rowIndex){
		this.colIndex = colIndex;
		this.rowIndex = rowIndex;
	}


	public void draw(Texture texture){

		Storage.batch.draw(texture, colIndex * texture.getWidth() + Field.xOffset , rowIndex * texture.getHeight());
	}
}
