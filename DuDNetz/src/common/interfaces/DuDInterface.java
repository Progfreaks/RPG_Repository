package common.interfaces;

import java.awt.event.ActionEvent;
import java.util.List;

import common.valueobject.Character;
import server.domain.exceptions.MaxPlayerCountExceedException;
import server.domain.exceptions.SamePlayerSelectedException;
import server.persistence.characterdata.CharacterData;

public interface DuDInterface {
	
	public void setCharCoords(int x, int y);

	public int getXCharCoord();

	public int getYCharCoord();
	
	public int getCY();
	public int getCX();


	public void setCharToState(int i);

	public Character getPlayer();
	
	public Character getPlayerByIndex(int index);



	public void addPlayer(final Character c) throws MaxPlayerCountExceedException,SamePlayerSelectedException;


	public void setLifeToPlayer(String name,int life);
	
	public void setLifeToEnemy(String name,int life);

	
	public void setMPToCharacter(String name,int mp);


	public void removePlayer(Character player);



	public Character getEnemy();

	public void setEnemy(final Character c);

	public Character createCharacter(CharacterData data);

	public Character createPlayerByIndex(int index);
	
	public Character createEnemy(int characterIndex);
	
	
	public void addEnemy(Character enemy);
	
	public Character getEnemy(String name);
	
	public void removeEnemy(String name);
	
	public int getEnemyListSize();
	
	public boolean isAllEnemyDead();

	/**
	 * Gibt ein Charcterdata zurueck.
	 * @param id
	 * @return
	 */
	public CharacterData getCharacterData(int id);

	


	public void setMaxPlayerCount(int count);
	
	public void increaseMaxPlayerCount();
	
	

	public int getMaxPlayerCount();


	public int getPlayerCount();
	
	public boolean isAllPlayerDead();

	public List<Character> getPlayers();

	//------player turn ---------

	

	
	public int getCurrentPlayerIndex();

	
	public void setCurrentPlayerIndex(int index);
	
	public void increaseCurrentPlayerIndex();
	
	public Character getNextPlayer();

	
	//--------- MapManager ----------
	public int[][] getBoardMatrix();
	
	public int getBoardElement(int x,int y);

	
	public void setUpMapData();
	
	public void setCoords(int[] coords);
	
	public void setClickedCoords(int[] coords);
	public int[] getClickedCoords();
	
	
	//--------- LeverManager ----------
		public void pullLever();
		
		public String getCountPulledLever();
		
		public boolean isAllLevePulled();
	


	//---------fuer persistence----------

	public int[][] getSavedBoardMatrix();
	
	public void setMapElement(int x,int y ,int element);
	
	public Character getSavedCharacter();
	
	public void cureCharacter();
	
	public void loadState();

	public void saveState();

}
