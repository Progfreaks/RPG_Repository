package domain;

import valueobject.character.Character;
import valueobject.character.CharacterEnum;
import domain.DuD;
import valueobject.PlayerArray;


/**
 * Klasse fuer die Charakter-Verwaltung.
 * Diese ist zustaendig dafuer, Erzeugung des Charakters.
 * @author YOU_HEY
 *
 */
public class CharacterManager {
	public CharacterManager(DuD game){
		this.game = game;
	}

	private DuD game;
	Character character = null;
	
	
	
	/**
	 * Erzeugt einen Charakter.
	 * @param pCharacter
	 * @return
	 */
	public Character createCharacter(CharacterEnum pCharacter){
		
		character = new Character(pCharacter.getName(), pCharacter.getLife(), pCharacter.getIsPlayer(),pCharacter.getSkills(), 0, 0); // 0 u. 0 sind x und y
				
		
		return character;
	}
	public void setCoords(int x, int y){
		character.setCoords(x, y);
	}
	public int getXCoord(){
		return character.getXCoord();
	}
	public int getYCoord(){
		return character.getYCoord();
	}
	public Character getPlayer(final int index){
		return PlayerArray.getPlayer(index);
	}
	
}
