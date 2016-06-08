package persistence.gamestate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import valueobject.Character;

public class GameState implements Serializable{



	private static final long serialVersionUID = 4944950131529020628L;

	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private static GameState singleton;

	private valueobject.Character character;

	private List<Character> players;

	private int[][] boardMatrix;	//private CharacterManager charMgr;
	private int currentPlayerIndex;

	private GameState(){}


	public static GameState getInstance(){
		if(singleton == null) singleton = new GameState();
		return singleton;
	}

	public void setCharacter(valueobject.Character c){
		character = c;
	}

	public void setPlayers(List<Character> players){
		this.players = players;
	}

	public List<Character> getSavedPlayers(){
		return players;
	}

	public valueobject.Character getSavedCharacter(){
		return character;
	}


	public void setBoardMatrix(int[][] b){
		boardMatrix = b;
	}

	public int[][] getSavedBoardMatrix(){
		return boardMatrix;
	}

	public int getSavedCurrentPlayerIndex(){
		return currentPlayerIndex;
	}
	
	public void setCurrentPlayerIndex(int index){
		currentPlayerIndex = index;
	}






	/**
	 * Liest die Datei.
	 */
	public  GameState loadFile() {
		try {
			FileInputStream fIS = new FileInputStream("./gamestate.dat");
			ObjectInputStream oIS = new ObjectInputStream(fIS);
			singleton = (GameState) oIS.readObject();
			oIS.close();
			System.out.println("loaded");
			return singleton;
		} catch (Exception e) {
			System.out.println(" keine Datei exsistiert. wird 'gamestate.dat' erzeugt");
			singleton = new GameState();
			return singleton;
		}
	}


	/**
	 * Schreibt in die Datei.
	 */
	public void saveFile() {
		try {
			FileOutputStream fOS = new FileOutputStream("./gamestate.dat");
			ObjectOutputStream oOS = new ObjectOutputStream(fOS);
			oOS.writeObject(this);
			oOS.close();
			System.out.println("saved");
		} catch (Exception e) {
			System.out.println(" exception saveFile");
			e.printStackTrace();
		}
	}
}
