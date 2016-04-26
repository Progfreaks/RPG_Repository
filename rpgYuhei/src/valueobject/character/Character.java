package valueobject.character;

import valueobject.Dice;
import valueobject.IO;
import valueobject.character.Skill.SkillEnum;


/**
 * Diese Klasse repraesentiert einen Charakter.
 * @author YOU_HEY
 *
 */
public  class Character{
	
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
	private final int maxLife;
	//Maximale Manapunkte.
	private int manaPoint;
	//Faehigkeiten des Charakters.
	private SkillEnum[] skills;
	//Koordinate des Charakters.
	private int x, y;
	
	
	/**
	 * Konstruktor des Charakters.
	 * @param name
	 * @param life
	 * @param isPlayer
	 * @param skills
	 * @param x
	 * @param y
	 */
	public  Character(final String name,final int life,final boolean isPlayer,final SkillEnum[] skills,final int x,final int y) {
		
		this.name = name;
		this.life = life;
		maxLife = life;
		this.level = 1;
		this.exPoint = 0;
		this.isAlive = true;
		this.isPlayer = isPlayer;
		this.manaPoint = 36;
		this.skills = skills;
		this.x = x;
		this.y = y;
		
		
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
	/**
	 * Eine print-Methode.
	 */
	public void print(){
		System.out.println("Name:"+getName()+" Life:"+getLife()+"/"+getMaxLife()
				+" MP:"+getMP()+"/36"+" Level:"+getLevel()+"\n");
	}
	/**
	 * Methode, die Angriff-Prozess ausfuehrt.
	 * @return
	 */
	public int attack(){
		return isPlayer() ? IO.selectCommandMessage(this):Dice.diceForAtk(this);
	}
	/**
	 * Setter-Methode fuer MP.
	 * @param mp
	 */
	public void setMP(final int mp){
		manaPoint += mp;
		if(manaPoint < 0) manaPoint = 0;
	}
	/**
	 * Getter-Methode fuer MP.
	 * @return
	 */
	public int getMP(){
		return manaPoint;
	}
	/**
	 * GetterMethode fuer die Faehigkeiten.
	 * @return
	 */
	public SkillEnum[] getSkills(){
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
