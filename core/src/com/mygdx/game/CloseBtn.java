package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;

public class CloseBtn {
	private int x, y;
	Texture texture;
	public CloseBtn(int x, int y) {
		this.x = x;
		this.y = y;
		texture = Storage.assetManager.get("close.png",Texture.class);
	}
	public void draw(){
		Storage.batch.draw(texture, x, y);
	}
	public boolean isClicked(float touchX, float touchY){

		return touchX >= x && touchX < x + texture.getWidth() && touchY >= y && touchY < y + texture.getHeight() ;
	}
}
