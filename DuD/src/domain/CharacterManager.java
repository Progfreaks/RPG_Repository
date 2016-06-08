package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import valueobject.Character;
import persistence.character.CharacterData;
import persistence.character.CharacterData.STATUS;
import persistence.character.CharacterData.Skill;
import persistence.gamestate.GameState;
import domain.DuD;


/**
 * Klasse fuer die Charakter-Verwaltung.
 * Diese ist zustaendig dafuer, Erzeugung des Charakters.
 * @author YOU_HEY
 *
 */
public class CharacterManager  {
	
	
	private static final long serialVersionUID = 278654584065262574L;
	
	//Spielern werden hier gespeichert.
	private  List<Character> playerList = new ArrayList<Character>();
	//alle Gegner werden hier gespeichert.
	private  List<Character> enemyList = new ArrayList<Character>();
	
	private DuD game;
	private Character character = null;
	//private Character enemy = null;
	

	
	/**
	 * Konstruktor dieser Klasse.
	 * @param game
	 */
	public CharacterManager(DuD game){
		this.game = game;
	}

	
	
	
	/**
	 * Erzeugt einen Charakter.
	 * @param data
	 * @return
	 */
	public Character createCharacter(CharacterData data){
		
		String name = data.getValue(STATUS.NAME);
		int hp = Integer.valueOf(data.getValue(STATUS.HP));
		int mp = Integer.valueOf(data.getValue(STATUS.MP));
		boolean isPlayer = Boolean.valueOf(data.getValue(STATUS.ISPLAYER));
		Skill[] skills = data.getSkill();
		
		character = new Character(name, hp,mp, isPlayer,skills, 0, 0); // 0 u. 0 sind x und y
		
		if(isPlayer){
		character.setMaxLife(hp);
		character.setMaxMP(mp);
		}
		
		return character;
	}
	

	
	/**
	 * Setzt die Charakterkoodinaten.
	 * @param x
	 * @param y
	 */
	public void setCoords(int x, int y){
		character = playerList.get(0);
		character.setCoords(x, y);
	}
	
	/**
	 * Gibt x-Koodinaten zurueck.
	 * @return
	 */
	public int getPlayerXCoord(){
		character = playerList.get(0);
		return character.getXCoord();
	}
	
	/**
	 * Gibt y-Koodinaten zurueck.
	 * @return
	 */
	public int getPlayerYCoord(){
		character = playerList.get(0); //H�sslich, sollte weg
		return character.getYCoord();
	}
	
	/**
	 * Nimmt einen Spieler aus PlayerArray anhand der entsprechenden Index-Nummer.
	 * @param index
	 * @return
	 */
	public Character getPlayer(final int index){
		return playerList.get(index);
	}
	
	/**
	 * Fuegt einen Speieler in die List hinzu.
	 * @param character
	 */
	public void addPlayer(final Character character){
		playerList.add(character);
	}
	
	public void removePlayer(Character player){
		//playerList.remove(0);
		playerList.remove(player);
	}
	
	public int getPlayerListSize(){
		return playerList.size();
	}
	
	public void addEnemy(Character enemy){
		enemyList.add(enemy);
	}
	
	public Character getEnemy(String name){
		Character enemy = null;
		System.out.println("enemy list"+enemyList.size());
		for(Character e:enemyList){
			System.out.println("enemy name from schmgr"+e.getName());
			
			if(e.getName().equals(name)){
				enemy = e;
			}else{
				System.out.println("no enemy from charmgr");
				
			}
		}
		System.out.println(enemy.getName());
		return enemy;
	}
	
	public void removeEnemy(String name){
		for(int i=0;i<getEnemyListSize();i++){
			if(enemyList.get(i).getName().equals(name)){
				enemyList.remove(enemyList.get(i));
			}else{
				System.out.println("no enemy from charmgr");
			}
		}
	}
	
	public int getEnemyListSize(){
		return enemyList.size();
	}
	
	/**
	 * Nimmt einen Gegner aus der List anhand der entsprechenden Index-Nummer.
	 * @param index
	 * @return
	 */
	private Character enemy;
	
	public Character getEnemy(){
		return enemy;
	}
	
	
	public void setEnemy(final Character character){
		enemy = character;
	}
	
	
	public List<Character> getPlayerList(){
		return playerList;
	}
	
	public void savePlayerList(){
		GameState.getInstance().setPlayers(playerList);
	}
	
	
	public void setSavedPlayers(){
		int i=0;
		for(Character player:GameState.getInstance().getSavedPlayers()){
			addPlayer(player);
			System.out.println("from loadState -> "+getPlayer(i++));
		}
	}
	
	

	
	
}
