package domain;

import gui.creater.GuiGameConsole;
import gui.events.GuiGameEvent;
import persistence.character.CharacterData;
import persistence.character.CharacterDataMap;
import persistence.gamestate.GameState;
import valueobject.Character;


/**
 * einziger Absprechpartner für GUI.
 * @author YOU_HEY
 *
 */
public class DuD {

	private CharacterManager CharMgr = null;
	private GameCycle cycle = null;
	private  GuiGameConsole console = null;
	private static DuD singleton;



	// Vermeidet dass die Instanz dieser Klasse von aussen erzeugt wird.
	private DuD(){
		this.CharMgr = new CharacterManager(this);
		this.cycle = new GameCycle(this);
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

	
	public void setConsole(GuiGameConsole console){
		this.console = console;
	}

	
	public void processEvent(GuiGameEvent event) {

		event.process();
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
	public CharacterManager getCharMgr(){
		return CharMgr;
	}
	
	
	
	public void setCharToState(int i){
		GameState state = GameState.getInstance();
		state.setCharacter(getCharMgr().getPlayer(i));
		state.saveFile();
	}
	public Character getPlayer(final int index){
		return CharMgr.getPlayer(index);
	}

	public void addPlayer(final Character c){
		CharMgr.addPlayer(c);
	}

	public Character getEnemy(final int index){
		return CharMgr.getEnemy(index);
	}

	public void addEnemy(final Character c){
		CharMgr.addEnemy(c);
	}
	
	public Character createPlayer(CharacterData data){
		return CharMgr.createCharacter(data);
	}
	public Character createEnemy(CharacterData pCharacter){
		return CharMgr.createCharacter(pCharacter);
	}
	public int nextRound(){

		return cycle.nextRound();
	}
	public void setIndicator(boolean in){
		cycle.setIndicator(in);
	}
	
	/**
	 * Gibt ein Charcterdata zurueck.
	 * @param id
	 * @return
	 */
	public CharacterData getCharData(int id){
		 CharacterDataMap map = CharacterDataMap.getInstance();
		 return map.getCharacterData(id);
		 
	}

 
}

