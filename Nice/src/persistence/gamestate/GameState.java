package persistence.gamestate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameState implements Serializable{



	private static final long serialVersionUID = 4944950131529020628L;

	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private static GameState singleton;

	private valueobject.character.Character character;


	private GameState(){}


	public static GameState getInstance(){
		if(singleton == null) singleton = new GameState();
		return singleton;
	}

	public void setCharacter(valueobject.character.Character c){
		character = c;
	}

	public valueobject.character.Character getCharacter(){
		return character;
	}
	/**
	 * Liest die Datei.
	 */
	private static void loadFile() {
		try {
			FileInputStream fIS = new FileInputStream("./gamestate.dat");
			ObjectInputStream oIS = new ObjectInputStream(fIS);
			singleton = (GameState) oIS.readObject();
			oIS.close();
			System.out.println("loaded");
		} catch (Exception e) {
			System.out.println(" keine Datei exsistiert. wird 'gamestate.dat' erzeugt");
			singleton = new GameState();
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
