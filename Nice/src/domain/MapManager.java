package domain;

import persistence.gamestate.GameState;
import valueobject.Board;
import valueobject.IGameElements;

public class MapManager implements IGameElements{

	
int[][] boardMatrix;
	
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
