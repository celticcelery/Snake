package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Field {
	final static int MAX_ROW_COUNT = 23;
	final static int MAX_COL_COUNT = 15;
	static int xOffset;
	Texture img = Storage.assetManager.get("field_cell.bmp", Texture.class);


	Field(){
		this.img = img;
		xOffset = (Gdx.graphics.getWidth() - MAX_COL_COUNT * img.getWidth())/2;
	}

	public void draw(){

		for(int iRow = 0; iRow < MAX_ROW_COUNT; iRow++) {
			for(int iCol = 0; iCol < MAX_COL_COUNT; iCol++) {
				Storage.batch.draw(img, iCol * img.getWidth() + xOffset , iRow * img.getHeight());
			}
		}
	}
}


