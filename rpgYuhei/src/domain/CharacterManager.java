package domain;

import valueobject.character.Character;
import valueobject.character.CharacterEnum;


/**
 * Klasse fuer die Charakter-Verwaltung.
 * Diese ist zustaendig dafuer, Erzeugung des Charakters.
 * @author YOU_HEY
 *
 */
public class CharacterManager {

	
	Character character = null;
	
	
	
	/**
	 * Erzeugt einen Charakter.
	 * @param pCharacter
	 * @return
	 */
	public Character createCharacter(CharacterEnum pCharacter){
		
		character = new Character(pCharacter.getName(), pCharacter.getLife(), pCharacter.getIsPlayer(),pCharacter.getSkills()
				);
		
		return character;
	}
	
}
