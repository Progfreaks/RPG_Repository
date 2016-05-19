package domain;

import java.util.ArrayList;
import java.util.List;

import valueobject.Character;
import persistence.character.CharacterData;
import persistence.character.CharacterData.Skill;
import domain.DuD;


/**
 * Klasse fuer die Charakter-Verwaltung.
 * Diese ist zustaendig dafuer, Erzeugung des Charakters.
 * @author YOU_HEY
 *
 */
public class CharacterManager {
	
	//Spielern werden hier gespeichert.
	private  List<Character> list = new ArrayList<Character>();
	//alle Gegner werden hier gespeichert.
	private  List<Character> eList = new ArrayList<Character>();

	
	/**
	 * Konstruktor dieser Klasse.
	 * @param game
	 */
	public CharacterManager(DuD game){
		this.game = game;
	}

	private DuD game;
	Character character = null;
	Character enemy = null;
	
	
	
	/**
	 * Erzeugt einen Charakter.
	 * @param data
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
	
	/**
	 * Erzeugt einen Gegner.
	 * @param pCharacter
	 * @return
	 */
	public Character createEnemy(CharacterData status){
		String name = status.getValue(status.NAME);
		int hp = Integer.valueOf(status.getValue(status.HP));
		int mp = Integer.valueOf(status.getValue(status.MP));
		boolean isPlayer = Boolean.valueOf(status.getValue(status.ISPLAYER));
		Skill[] skills = status.getSkill();
		
		character = new Character(name, hp, mp,isPlayer,skills, 0, 0); // 0 u. 0 sind x und y
		return enemy;
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
	public int getPlayerXCoord(){
		character = list.get(0);
		return character.getXCoord();
	}
	
	/**
	 * Gibt y-Koodinaten zurueck.
	 * @return
	 */
	public int getPlayerYCoord(){
		character = list.get(0); //H�sslich, sollte weg
		return character.getYCoord();
	}
	
	/**
	 * Nimmt einen Spieler aus PlayerArray anhand der entsprechenden Index-Nummer.
	 * @param index
	 * @return
	 */
	public Character getPlayer(final int index){
		return list.get(index);
	}
	
	/**
	 * Fuegt einen Speieler in die List hinzu.
	 * @param character
	 */
	public void addPlayer(final Character character){
		list.add(character);
	}
	
	/**
	 * Nimmt einen Gegner aus der List anhand der entsprechenden Index-Nummer.
	 * @param index
	 * @return
	 */
	public Character getEnemy(final int index){
		return eList.get(index);
	}
	
	/**
	 * Fuegt einen Gegner in die List hinzu.
	 * @param character
	 */
	public void addEnemy(final Character character){
		eList.add(character);
	}
	

	
	
}
