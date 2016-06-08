package domain;

import java.util.List;

import persistence.character.CharacterData;
import persistence.character.CharacterDataMap;
import persistence.gamestate.GameState;
import valueobject.Character;
import valueobject.IGameElements;


/**
 * einziger Absprechpartner für GUI.
 * @author YOU_HEY
 *
 */
public class DuD implements IGameElements{

	private CharacterManager CharMgr = null;
	private PlayerTurn playerTurn;
	private LeverManager levMgr;
	private static DuD singleton;
	MapManager mapManager;
	
	private DuD(){
		this.CharMgr = new CharacterManager(this);
		playerTurn = new PlayerTurn();
		mapManager = new MapManager();
		levMgr = new LeverManager();
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

	public void setCharCoords(int x, int y){
		CharMgr.setCoords(x, y);
	}

	public int getXCharCoord(){
		return CharMgr.getPlayerXCoord();
	}

	public int getYCharCoord(){
		return CharMgr.getPlayerYCoord();
	}


	public void setCharToState(int i){
		GameState state = GameState.getInstance();
		state.setCharacter(CharMgr.getPlayer(i));
		state.saveFile();
	}

	public Character getPlayer(){
		System.out.println("player count"+CharMgr.getPlayerListSize());
		return CharMgr.getPlayer(playerTurn.getCurrentPlayerIndex());
	}


	public void addPlayer(final Character c){
		CharMgr.addPlayer(c);
	}



	public void removePlayer(Character player){
		CharMgr.removePlayer(player);
	}



	public Character getEnemy(){
		return CharMgr.getEnemy();
	}

	public void setEnemy(final Character c){
		CharMgr.setEnemy(c);
	}

	public Character createPlayer(CharacterData data){
		return CharMgr.createCharacter(data);
	}

	public Character createPlayerByIndex(int index){
		return createPlayer(getCharacterData(index));
	}
	
	public Character createEnemy(int characterIndex){
		Character enemy = CharMgr.createCharacter(getCharacterData(characterIndex));
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
		CharMgr.addEnemy(enemy);
	}
	
	public Character getEnemy(String name){
		return CharMgr.getEnemy(name);
	}
	public void removeEnemy(String name){
		CharMgr.removeEnemy(name);
	}
	
	public int getEnemyListSize(){
		return CharMgr.getEnemyListSize();
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


	


	public void setMaxPlayerCount(int count){
		playerTurn.setMaxPlayerCount(count);
	}

	public int getMaxPlayerCount(){
		return playerTurn.getMaxPlayerCount();
	}


	public int getPlayerCount(){
		return CharMgr.getPlayerListSize();
	}
	
	public boolean isAllPlayerDead(){
		return getPlayerCount() == 0;
	}

	public List<Character> getPlayers(){
		return CharMgr.getPlayerList();
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
		return CharMgr.getPlayer(getCurrentPlayerIndex());
	}

	
	//----map-----
	public int[][] getBoardMatrix(){
		return mapManager.getBoardMatrix();
	}
	
	public int getBoardElement(int x,int y){
		return mapManager.getBoardElement(x, y);
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
	
	public void loadState(){
		GameState.getInstance().loadFile();
        CharMgr.setSavedPlayers();		
		mapManager.setSavedBoardMatrix();
		playerTurn.setSavedCurrentPlayerIndex();
	}

	public void saveState(){
		if(CharMgr != null){
			GameState gameState = GameState.getInstance();
			CharMgr.savePlayerList();
			levMgr.saveCurrentLeverCount();
			mapManager.saveBoardMatrix(getYCharCoord(),getXCharCoord());
			playerTurn.saveCurrentPlayerIndex();
			gameState.saveFile();
		}
	}
	//--------- LeverManager ----------
	public void pullLever(){
		levMgr.pulledLever();
	}
	public String pulledLever(){
		return levMgr.pulledLever();
	}
	public boolean checkAllLever(){
		return levMgr.checkAllLever();
	}
}

