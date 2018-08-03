package com.mygdx.game;


import com.badlogic.gdx.Gdx;

public class Timer {
	int intervalMs;
	int msElapsed;

	Timer(int intervalMs){
		this.intervalMs = intervalMs;
	}

	boolean isOnTime(){
		msElapsed += 1000 * Gdx.graphics.getDeltaTime();
		if(msElapsed >= intervalMs){
			msElapsed -= intervalMs;
			return true;
		}else {
			return false;
		}
	}

}
