package server.domain;

import java.awt.event.ActionEvent;
import java.util.List;

import common.interfaces.DuDInterface;
import common.valueobject.Character;
import common.valueobject.IGameElements;
import server.domain.exceptions.MaxPlayerCountExceedException;
import server.domain.exceptions.SamePlayerSelectedException;
import server.domain.managers.CharacterManager;
import server.domain.managers.LeverManager;
import server.domain.managers.MapManager;
import server.domain.managers.PlayerTurnManager;
import server.persistence.characterdata.CharacterData;
import server.persistence.characterdata.CharacterDataMap;
import server.persistence.gamastate.GameState;


/**
 * einziger Absprechpartner für GUI.
 * @author YOU_HEY
 *
 */
public class DuD implements IGameElements, DuDInterface{

	private CharacterManager characterManager = null;
	private PlayerTurnManager playerTurn;
	private LeverManager levelManager;
	private static DuD singleton;
	MapManager mapManager;
	
	private DuD(){
		this.characterManager = new CharacterManager();
		playerTurn = new PlayerTurnManager();
		mapManager = new MapManager();
		levelManager = new LeverManager();
	}
	

	/**
	 * Wenn es keine Instanz exsistiert, dann wird eine Instanz erzeugt. Wenn 
	 * existiert, dann gibt die zurück.
	 * 
	 * @return
	 */
	public static DuD getGame(){
		if(singleton == null) singleton = new DuD();
		return singleton;
	}

//	public void setCharCoords(int x, int y){
//		characterManager.setCoords(x, y);
//	}
	public void setCharCoords(int x, int y){
		int[] coords = {x,y};
		//mapManager.setClickedCoords(coords);
		mapManager.setCoords(coords);
	}
	
	

//	public int getXCharCoord(){
//		return characterManager.getPlayerXCoord();
//	}
//
//	public int getYCharCoord(){
//		return characterManager.getPlayerYCoord();
//	}
	
	


	public void setCharToState(int i){
		GameState state = GameState.getInstance();
		state.setCharacter(characterManager.getPlayer(i));
		state.saveFile();
	}

	public Character getPlayer(){
		System.out.println("player count"+characterManager.getPlayerListSize());
		return characterManager.getPlayer(playerTurn.getCurrentPlayerIndex());
	}
	
	public Character getPlayerByIndex(int index){
		return characterManager.getPlayer(index);
	}
	
	


	public void addPlayer(final Character c) throws MaxPlayerCountExceedException,SamePlayerSelectedException{
		characterManager.addPlayer(c);
	}



	public void removePlayer(Character player){
		characterManager.removePlayer(player);
	}



	public Character getEnemy(){
		return characterManager.getEnemy();
	}

	public void setEnemy(final Character c){
		characterManager.setEnemy(c);
	}

	public Character createCharacter(CharacterData data){
		return characterManager.createCharacter(data);
	}

	public Character createPlayerByIndex(int index){
		return createCharacter(getCharacterData(index));
	}
	
	public Character createEnemy(int characterIndex){
		Character enemy = characterManager.createCharacter(getCharacterData(characterIndex));
		setEnemyStrength(enemy);
		return enemy;
	}
	
	private void setEnemyStrength(Character enemy){
		int strength = (getPlayerCount()-1);
		enemy.setLife(enemy.getLife()*strength);
		enemy.setMP(enemy.getMP()*strength);
		enemy.setMaxLife(enemy.getLife());
		enemy.setMaxMP(enemy.getMP());
	}
	public void addEnemy(Character enemy){
		characterManager.addEnemy(enemy);
	}
	
	public Character getEnemy(String name){
		return characterManager.getEnemy(name);
	}
	public void removeEnemy(String name){
		characterManager.removeEnemy(name);
	}
	
	public int getEnemyListSize(){
		return characterManager.getEnemyListSize();
	}
	
	public boolean isAllEnemyDead(){
		return getEnemyListSize() == 0;
	}

	/**
	 * Gibt ein Charcterdata zurueck.
	 * @param id
	 * @return
	 */
	public CharacterData getCharacterData(int id){
		CharacterDataMap map = CharacterDataMap.getInstance();
		return map.getCharacterData(id);

	}

	public void setLifeToPlayer(String name, int life){
		for(Character c:getPlayers()){
			if(c.getName().equals(name)) c.setLife(life);
		}
	}
	
	public void setLifeToEnemy(String name, int life){
		List<Character> list = characterManager.getEnemyList();
		for(Character c:list){
			if(c.getName().equals(name)) c.setLife(life);
		}
	}
	public void setMPToCharacter(String name, int mp){
		for(Character c:getPlayers()){
			if(c.getName().equals(name)) c.setMP(mp);
			System.out.println("MP Set by "+name+"\n");
		}
	}
	


	public void setMaxPlayerCount(int count){
		characterManager.setMaxPlayerCount(count);
	}
	
	public void increaseMaxPlayerCount(){
		characterManager.increaseMaxPlayerCount();
	}
	
	

	public int getMaxPlayerCount(){
		return characterManager.getMaxPlayerCount();
	}


	public int getPlayerCount(){
		return characterManager.getPlayerListSize();
	}
	
	public boolean isAllPlayerDead(){
		return getPlayerCount() == 0;
	}

	public List<Character> getPlayers(){
		return characterManager.getPlayerList();
	}
	
	
	//------player turn ---------

	

	
	public int getCurrentPlayerIndex(){
		return playerTurn.getCurrentPlayerIndex();
	}

	
	public void setCurrentPlayerIndex(int index){
		playerTurn.setCurrentPlayerIndex(index);
	}
	
	public void increaseCurrentPlayerIndex(){
		if(getCurrentPlayerIndex() < getPlayerCount()-1){
			System.out.println("nextPlayer "+getCurrentPlayerIndex());
			setCurrentPlayerIndex(getCurrentPlayerIndex()+1);

		}else if(getCurrentPlayerIndex() == getPlayerCount()-1){
			System.out.println("nextPlayer "+getCurrentPlayerIndex());
			setCurrentPlayerIndex(0);

		}
	}
	
	public Character getNextPlayer(){
		increaseCurrentPlayerIndex();
		return characterManager.getPlayer(getCurrentPlayerIndex());
	}

	
	//--------- MapManager ----------
	public int[][] getBoardMatrix(){
		return mapManager.getBoardMatrix();
	}
	
	public int getBoardElement(int x,int y){
		return mapManager.getBoardElement(x, y);
	}

	
	
	
	public void setClickedCoords(int[] coords){
		mapManager.setClickedCoords(coords);
	}
	
	public int[] getClickedCoords(){
		return mapManager.getClickedCoords();
	}
	
	
	@Override
	public void setCoords(int[] coords) {
		mapManager.setCoords(coords);
		
	}
	
	@Override
	public int getCY() {
		return mapManager.getCY();
		
	}


	@Override
	public int getCX() {
		return mapManager.getCX();
	}
	
	
	public int getXCharCoord(){
		return mapManager.getX();
	}

	public int getYCharCoord(){
		return  mapManager.getY();
	}
	
	public void setUpMapData(){
		int[][] boardMatrix = getBoardMatrix();
		for (int y = 0; y < boardMatrix.length; y++) {
			for (int x = 0; x < boardMatrix[y].length; x++) {
				if (boardMatrix[y][x] == PLAYER_ELEMENT) {
					setCharCoords(x, y); // x und y des Char setzen
				}
			}
		}
	}
	
	
	
	//--------- LeverManager ----------
		public void pullLever(){
			levelManager.countUpLever();
		}
		public String getCountPulledLever(){
			return levelManager.getCountPulledLever();
		}
		public boolean isAllLevePulled(){
			return levelManager.isAllLeverPulled();
		}
	


	//---------fuer persistence----------

	public int[][] getSavedBoardMatrix(){
		return mapManager.getSavedBoardMatrix();
	}
	
	public void setMapElement(int x,int y ,int element){
		mapManager.setBoardElement(x, y, element);
	}
	
	public Character getSavedCharacter(){
		return GameState.getInstance().getSavedCharacter();
	}
	
	public void cureCharacter(){
		characterManager.curePlayers();
	}
	
	public void loadState(){
		GameState.loadFile();
        characterManager.setSavedPlayers();		
		mapManager.setSavedBoardMatrix();
		playerTurn.setSavedCurrentPlayerIndex();
	}

	public void saveState(){
		if(characterManager != null){
			GameState gameState = GameState.getInstance();
			characterManager.savePlayerList();
			
			mapManager.saveBoardMatrix(getYCharCoord(),getXCharCoord());
			playerTurn.saveCurrentPlayerIndex();
			gameState.saveFile();
		}
	}


	


	


	
}

