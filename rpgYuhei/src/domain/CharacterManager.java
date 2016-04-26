package domain;

import valueobject.character.Character;
import valueobject.character.CharacterEnum;
import domain.DuD;
import valueobject.PlayerArray;


/**
 * Klasse fuer die Charakter-Verwaltung.
 * 
 * @author YOU_HEY
 *
 */
public class CharacterManager {
	
	private DuD game;
	Character character = null;
	
	
	public CharacterManager(DuD game){
		this.game = game;
	}

	
	
	
	
	/**
	 * Erzeugt einen Charakter.
	 * @param pCharacter
	 * @return
	 */
	public Character createCharacter(CharacterEnum pCharacter){
		
		character = new Character(pCharacter.getName(), pCharacter.getLife(), pCharacter.getIsPlayer(),pCharacter.getSkills(), 0, 0); // 0 u. 0 sind x und y
				
		
		return character;
	}
	
	/**
	 * Setzt die Charakterkoodinaten.
	 * @param x
	 * @param y
	 */
	public void setCoords(int x, int y){
		character.setCoords(x, y);
	}
	
	/**
	 * Gibt x-Koodinaten zurueck.
	 * @return
	 */
	public int getXCoord(){
		return character.getXCoord();
	}
	
	/**
	 * Gibt y-Koodinaten zurueck.
	 * @return
	 */
	public int getYCoord(){
		return character.getYCoord();
	}
	
	/**
	 * Nimmt einen Spieler aus PlayerArray anhand der entsprechenden Index-Nummer.
	 * @param index
	 * @return
	 */
	public Character getPlayer(final int index){
		return PlayerArray.getPlayer(index);
	}
	
}
