package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Snake {
	ArrayList<SnakeCell> cells = new ArrayList<SnakeCell>();
	enum Direction {UP, DOWN, LEFT, RIGHT};
	private Direction curDirection = Direction.RIGHT;
	private Direction pendingNewDirection = Direction.RIGHT;
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

	public Direction getCurDirection() {
		return curDirection;
	}

	public void setCurDirection(Direction newDirection) {

		if(curDirection == Direction.DOWN && newDirection == Direction.UP) return;
		if(curDirection == Direction.UP && newDirection == Direction.DOWN) return;
		if(curDirection == Direction.LEFT && newDirection == Direction.RIGHT) return;
		if(curDirection == Direction.RIGHT && newDirection == Direction.LEFT) return;

		pendingNewDirection = newDirection;
	}



	public void draw(){
		for(SnakeCell curCell : cells) {
			curCell.draw();
		}

	}

	public void move(){
		if(hasCrashed){
			return;
		}
		curDirection = pendingNewDirection;
		int oldHeadColIndex = cells.get(0).colIndex;
		int oldHeadRowIndex = cells.get(0).rowIndex;

		switch(curDirection){
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
		for(SnakeCell curCell : cells) {
			if (headCell == curCell) {continue;}
			if(headCell.rowIndex == curCell.rowIndex && headCell.colIndex == curCell.colIndex) {

				return false;
			}
		}
		return headCell.colIndex >= 0 && headCell.colIndex < Field.MAX_COL_COUNT && headCell.rowIndex < Field.MAX_ROW_COUNT && headCell.rowIndex >= 0;

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
