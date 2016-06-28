package common.valueobject;

import java.io.Serializable;
import java.util.Map;

import server.persistence.characterdata.CharacterData;


/**
 * Diese Klasse repraesentiert einen Charakter.
 * @author YOU_HEY
 *
 */
public  class Character implements Serializable {
	
	
	    //Name des Charakters.
		private String name;
		//Lebensmunkte des Charakters.
		private int life;
		//Level des Charakters.
		private int level;
		//Erfahrungspunkte des Charakters.
		private int exPoint;
		//Ob der Charakter noch lebt.
		private boolean isAlive;
		//Ob der Charakter ein Spieler ist.
		private boolean isPlayer;
		//Maximale Lebenspunkte.
		private  int maxLife;
		//Maximale Manapunkte.
		private  int maxMP;
		private int mp;
		//Koordinate des Charakters.
		private int x, y;
		//Faehigkeiten des Charakters.
		private server.persistence.characterdata.CharacterData.Skill[] skills;
		
	
		/**
		 * Konstruktor des Charakters.
		 * @param name
		 * @param life
		 * @param isPlayer
		 * @param skills
		 * @param x
		 * @param y
		 */
	public  Character(final String name,final int life,final int mp,final boolean isPlayer,final server.persistence.characterdata.CharacterData.Skill[] skills, int x, int y) {
		
		this.name = name;
		this.life = life;
		this.level = 1;
		this.exPoint = 0;
		this.isAlive = true;
		this.isPlayer = isPlayer;
		this.mp = mp;
		this.skills = skills;
		this.x = x;
		this.y = y;
//	    maxMP = mp;
//		maxLife = life;
	}


	/**
	 * Getter-Methode fuer den Name des Charakters.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter-Methode fuer den Name des Charakters.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
		
	}
	
	public void setMaxLife(int max){
		maxLife = max;
	}
	
	public void setMaxMP(int max){
		maxMP = max;
	}
	
	public int getMaxMP(){
		return maxMP;
	}
	/**
	 * Getter-Methode fuer die Lebenspunkte des Charakters.
	 * @return
	 */
	public int getLife() {
		return life;
	}
/**
 * 
* Setter-Methode fuer die Lebenspunkte des Charakters.
 * @param life
 */
	public void setLife(final int life) {
		this.life += life;
		if(this.life <= 0){
			isAlive = false;
		    this.life = 0;
		}
		
	}
	/**
	 * Getter-Methode fuer das Level des Charakters.
	 * @return
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * Setter-Methode fuer das Level des Charakters.
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * Getter-Methode fuer die  Erfahrungspunkte des Charakters.
	 * @return
	 */
	public int getExPoint() {
		return exPoint;
	}
	/**
	 * Setter-Methode fuer die  Erfahrungspunkte des Charakters.
	 * @param exPoint
	 */
	public void setExPoint(int exPoint) {
	     this.exPoint = exPoint;
	}
	
	/**
	 * Prueft, ob der Charakter noch am leben ist.
	 * @return
	 */
	public boolean isAlive(){
		return isAlive;
	}
	/**
	 * Setter-Methode. um zu aendern, ob der Charakter noch am leben ist.
	 * @param param
	 */
	public void setAlive(final boolean param){
		this.isAlive = param;
	}
	/**
	 * Prueft, ob der Charakter ein Spieler ist.
	 * @return
	 */
	public boolean isPlayer(){
		return isPlayer;
	}
	/**
	 * Um zu aendern, ob der Charakter ein Spieler ist.
	 * @param param
	 */
	public void setIsPlayer(final boolean param){
		isPlayer = param;
	}
	/**
	 * Getter-Methode fuer die Max-Lebenspunkte.
	 * @return
	 */
	public int getMaxLife(){
		return maxLife;
	}

	
	public String toString(){
		return "Name:"+getName()+" Life:"+getLife()+"/"+maxLife
				+" MP:"+getMP()+"/"+maxMP+"\n";//+" Level:"+getLevel();
	}
	
	public String toStringEnemy(){
		return "Name:"+getName()+" Life:"+getLife()+"/"+maxLife
				+"\n";
	}

	/**
	 * Setter-Methode fuer MP.
	 * @param mp
	 */
	public void setMP(final int pMP){
		mp += pMP;
		if(mp < 0){
			mp = 0;
		}
		else if(mp > maxMP){
			mp = maxMP;
		} 
	}
	/**
	 * Getter-Methode fuer MP.
	 * @return
	 */
	public int getMP(){
		return mp;
	}
	/**
	 * GetterMethode fuer die Faehigkeiten.
	 * @return*/
	 
	public server.persistence.characterdata.CharacterData.Skill[] getSkills(){
		return skills;
	}
	/**
	 * Setter-Methode fuer die Koordinaten.
	 * @param x
	 * @param y
	 */
	public void setCoords(final int x,final int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * Getter-Methode fuer die X-Koordinate.
	 * @return
	 */
	public int getXCoord(){
		return this.x;
	}
	/**
	 * Getter-Methode fuer die Y-Koordinate.
	 * @return
	 */
	public int getYCoord(){
		return this.y;
	}
	/**
	 * Gibt die Instanz dieser Charakter-klasse zurueck.
	 * @return
	 */
	public Character getThis(){
		return this;
	}
	
	
}
