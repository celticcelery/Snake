package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeCell {
	int colIndex, rowIndex;
	private boolean isHead;
	private Texture texture;

	SnakeCell(int colIndex, int rowIndex, boolean isHead){
		this.colIndex = colIndex;
		this.rowIndex = rowIndex;
		this.isHead = isHead;
		if(isHead){
			texture = Storage.assetManager.get("snake_head.bmp",Texture.class);
		} else {
			texture = Storage.assetManager.get("snake_body.bmp", Texture.class);
		}
	}


	public void draw(){

		Storage.batch.draw(texture, colIndex * texture.getWidth() + Field.xOffset , rowIndex * texture.getHeight());
	}
}
