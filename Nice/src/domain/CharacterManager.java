package domain;

import valueobject.character.Character;
import persistence.character.*;
import persistence.character.CharacterData;
import domain.DuD;
import persistence.character.CharacterData.Skill;
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
	Character enemy = null;
	
	
	
	/**
	 * Erzeugt einen Charakter.
	 * @param pCharacter
	 * @return
	 */
	public Character createCharacter(CharacterData data){
		
		String name = data.getValue(data.NAME);
		int hp = Integer.valueOf(data.getValue(data.HP));
		int mp = Integer.valueOf(data.getValue(data.MP));
		boolean isPlayer = Boolean.valueOf(data.getValue(data.ISPLAYER));
		Skill[] skills = data.getSkill();
		
		character = new Character(name, hp,mp, isPlayer,skills, 0, 0); // 0 u. 0 sind x und y				
		
		return character;
	}
	
	public Character createEnemy(CharacterData status){
		String name = status.getValue(status.NAME);
		int hp = Integer.valueOf(status.getValue(status.HP));
		int mp = Integer.valueOf(status.getValue(status.MP));
		boolean isPlayer = Boolean.valueOf(status.getValue(status.ISPLAYER));
		Skill[] skills = status.getSkill();
		
		character = new Character(name, hp, mp,isPlayer,skills, 0, 0); // 0 u. 0 sind x und y
		return enemy;
	}
	public void setCoords(int x, int y){
		character.setCoords(x, y);
	}
	public int getPlayerXCoord(){
		character = PlayerArray.getPlayer(0);
		return character.getXCoord();
	}
	public int getPlayerYCoord(){
		character = PlayerArray.getPlayer(0); //Hässlich, sollte weg
		return character.getYCoord();
	}
	public Character getPlayer(final int index){
		return PlayerArray.getPlayer(index);
	}
	
}
