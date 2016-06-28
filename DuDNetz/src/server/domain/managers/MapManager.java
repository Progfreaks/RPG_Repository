package server.domain.managers;

import java.awt.event.ActionEvent;

import common.valueobject.Board;
import common.valueobject.IGameElements;
import server.persistence.gamastate.GameState;

public class MapManager implements IGameElements{

	
int[][] boardMatrix;
int clickedX;
int clickedY;
int[] clickedCoords;
	
	public int[][] getBoardMatrix(){
		if(boardMatrix == null) boardMatrix = new Board().getBoardMatrix();
		return boardMatrix;
	}
	
	public int[][] getSavedBoardMatrix(){
		return GameState.getInstance().getSavedBoardMatrix();
	}
	
	
	public void setSavedBoardMatrix(){
		boardMatrix = GameState.getInstance().getSavedBoardMatrix();
	}
	
	 public int getBoardElement(int x,int y){
		return boardMatrix[y][x];
	}
	 
	 int[] clicked;
	 public void setClickedCoords(int[] coords){
		 clickedX = coords[0];
		 clickedY = coords[1];
		 clicked = coords;
	 }
	 int x;
	 int y;
	 int[] c;
	 public void setCoords(int[] coords){
		 x = coords[0];
		 y = coords[1];
		 c = coords;
	 }
	 
	 public int getX(){
		 return x;
	 }
	 
	 public int getY(){
		 return y;
	 }
	 
	 public int getCX(){
		 return clickedX;
	 }
	 public int getCY(){
		 return clickedY;
	 }
	 
	 
	 
	 public int[] getClickedCoords(){
		 return clicked;
	 }
	
		
	 public void setBoardElement(int x,int y, int element){
		 boardMatrix[y][x] = element;
	 }
	
	public void saveBoardMatrix(int playerX,int playerY){
		for (int y = 0; y < boardMatrix.length; y++) {
			for (int x = 0; x < boardMatrix[y].length; x++) {
				if(boardMatrix[y][x] == PLAYER_ELEMENT) boardMatrix[y][x] = FLOOR_ELEMENT;
			}
		}
		boardMatrix[playerX][playerY] = PLAYER_ELEMENT;
		GameState.getInstance().setBoardMatrix(boardMatrix);
	}
	
}
