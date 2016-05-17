package domain;

import gui.GameConsole;
import gui.events.GameEvent;
import persistence.character.CharacterData;
import valueobject.character.Character;


/**
 * einziger Absprechpartner für GUI.
 * @author YOU_HEY
 *
 */
public class DuD {

	private CharacterManager CharMgr = null;
	private GameCycle cycle = null;
	private  GameConsole console = null;
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

	
	public void setConsole(GameConsole console){
		this.console = console;
	}

//	public GameConsole getConsole(){
//		return console;
//	}
	
	
	public void processEvent(GameEvent event) {

		event.process();
	}

//	public static DuD getGame(){
//
//		return game;
//	}



//	public void setGame(DuD game){
//		this.game = game;
//	}

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


}

