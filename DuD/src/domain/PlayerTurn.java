package domain;

import persistence.gamestate.GameState;
import valueobject.Character;
import valueobject.RoundCount;

public class PlayerTurn {

	private int currentPlayerIndex;
	private int maxPlayerCount;



	public PlayerTurn(){
		currentPlayerIndex = 0;
	}
	
	public int getCurrentPlayerIndex(){
		System.out.println("from turn"+currentPlayerIndex);
		return currentPlayerIndex;
	}
	
	public void setCurrentPlayerIndex(int index){
		currentPlayerIndex = index;
	}
	
	public void setMaxPlayerCount(int count){
		maxPlayerCount = count;
	}
	
	public int getMaxPlayerCount(){
		return maxPlayerCount;
	}
	
	public void saveCurrentPlayerIndex(){
		
		GameState.getInstance().setCurrentPlayerIndex(currentPlayerIndex);
	}
	
	public void setSavedCurrentPlayerIndex(){
		currentPlayerIndex = GameState.getInstance().getSavedCurrentPlayerIndex();
	}
}

	