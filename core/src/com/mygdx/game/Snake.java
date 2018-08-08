package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Snake {
	ArrayList<SnakeCell> cells = new ArrayList<SnakeCell>();
	enum Direction {UP, DOWN, LEFT, RIGHT};
	private Direction direction = Direction.RIGHT;
	private SnakeCell headCell;
	private int eatenPizzasCount;
	private int snakeCellsMoved, oldTailColIndex, oldTailRowIndex;
	private boolean hasCrashed;

	public Snake(){
		headCell = new SnakeCell(Field.MAX_COL_COUNT / 2, Field.MAX_ROW_COUNT / 2, true);
		cells.add(headCell);
		cells.add(new SnakeCell(cells.get(0).colIndex - 1, Field.MAX_ROW_COUNT / 2, false));
		cells.add(new SnakeCell(cells.get(0).colIndex - 2, Field.MAX_ROW_COUNT / 2, false));

//		cells.add(new SnakeCell());
	}

	public boolean getHasCrashed() {
		return hasCrashed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {

		if(this.direction == Direction.RIGHT && Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			direction = Direction.RIGHT;
		}
		if(this.direction == Direction.LEFT && Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			direction = Direction.LEFT;
		}
		if(this.direction == Direction.UP && Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			direction = Direction.UP;
		}
		if(this.direction == Direction.DOWN && Gdx.input.isKeyPressed(Input.Keys.UP)){
			direction = Direction.DOWN;
		}

		this.direction = direction;
	}



	public void draw(){
		for(SnakeCell curCell : cells) {
			curCell.draw();
		}

	}

	public void move(){
		int oldHeadColIndex = cells.get(0).colIndex;
		int oldHeadRowIndex = cells.get(0).rowIndex;

		switch(direction){
			case UP: headCell.rowIndex++; break;
			case DOWN: headCell.rowIndex--; break;
			case LEFT: headCell.colIndex--; break;
			case RIGHT: headCell.colIndex++; break;
		}
		if(!hasValidHeadPos()){
			headCell.rowIndex = oldHeadRowIndex;
			headCell.colIndex = oldHeadColIndex;
			hasCrashed = true;
			return;
		}
		repositionTail(oldHeadColIndex, oldHeadRowIndex);

		snakeCellsMoved++;



	}

	private void repositionTail(int oldHeadColIndex, int oldHeadRowIndex) {
		SnakeCell tail = cells.get(cells.size() - 1);
		oldTailColIndex = tail.colIndex;
		oldTailRowIndex = tail.rowIndex;
		tail.colIndex = oldHeadColIndex;
		tail.rowIndex = oldHeadRowIndex;
		cells.add(1, tail);
		cells.remove(cells.size() - 1);
	}

	private boolean hasValidHeadPos(){
		if(headCell.colIndex < 0 || headCell.colIndex >= Field.MAX_COL_COUNT || headCell.rowIndex >= Field.MAX_ROW_COUNT || headCell.rowIndex < 0){
			return false;
		}
		return true;
	}

	public boolean contains(int colIndex, int rowIndex){
		for(SnakeCell curCell : cells){
			if(curCell.rowIndex == rowIndex && curCell.colIndex == colIndex){
				return true;
			}
		}
		return false;
	}

	public void eatPizza(){
		eatenPizzasCount++;
		cells.add(new SnakeCell(oldTailColIndex, oldTailRowIndex, false));


	}

	public int getEatenPizzasCount() {
		return eatenPizzasCount;
	}

	public int getSnakeCellsMoved() {
		return snakeCellsMoved;
	}
}
