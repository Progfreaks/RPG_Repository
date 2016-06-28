package server.domain.managers;

import server.persistence.gamastate.GameState;

public class PlayerTurnManager {

	private int currentPlayerIndex;
//	private int maxPlayerCount;



	public PlayerTurnManager(){
		currentPlayerIndex = 0;
	}
	
	public int getCurrentPlayerIndex(){
		System.out.println("from turn"+currentPlayerIndex);
		return currentPlayerIndex;
	}
	
	public void setCurrentPlayerIndex(int index){
		currentPlayerIndex = index;
	}
	
//	public void setMaxPlayerCount(int count){
//		maxPlayerCount = count;
//	}
//	
//	public int getMaxPlayerCount(){
//		return maxPlayerCount;
//	}
	
	public void saveCurrentPlayerIndex(){
		
		GameState.getInstance().setCurrentPlayerIndex(currentPlayerIndex);
	}
	
	public void setSavedCurrentPlayerIndex(){
		currentPlayerIndex = GameState.getInstance().getSavedCurrentPlayerIndex();
	}
}

	