package valueobject.character;

import valueobject.Dice;
import persistence.character.CharacterData;
import domain.CentralSave;



public  class Character{
	//Name des Charakters
	private String name;
//	hp char
	private int life;
//	lvl char
	private int level;
//	exp char
	private int exPoint;
//	checkt ob er noch lebt
	private boolean isAlive;
//	checkt ob er ein spieler ist
	private boolean isPlayer;
//	Max leben
	private final int maxLife;
//	Max Mana
	private int maxMP;
//	Fähigkeiten des Char
	private int mp;
	
	private int x, y;
	
	private persistence.character.CharacterData.Skill[] skills;
	
	public  Character(final String name,final int life,final int mp, final boolean isPlayer, final persistence.character.CharacterData.Skill[] skills, int x, int y) {
		
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
		this.maxMP = mp;
		maxLife = life;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public int getLife() {
		return life;
	}

	public void setLife(final int life) {
		this.life += life;
		if(this.life <= 0){
			isAlive = false;
		    this.life = 0;
		}
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExPoint() {
		return exPoint;
	}

	public void setExPoint(int exPoint) {
	     this.exPoint = exPoint;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	public void setAlive(final boolean param){
		this.isAlive = param;
	}
	
	public boolean isPlayer(){
		return isPlayer;
	}
	
	public void setIsPlayer(final boolean param){
		isPlayer = param;
	}
	
	public int getMaxLife(){
		return maxLife;
	}

	public String toString(){
		return "Name:"+getName()+" Life:"+getLife()+"/"+getMaxLife()
				+" MP:"+getMP()+"/"+maxMP+" Level:"+getLevel();
	}
	
	public void setMP(final int pMP){
		mp += pMP;
		if(mp < 0) mp = 0;
	}
	
	public int getMP(){
		return mp;
	}
	
	public persistence.character.CharacterData.Skill[] getSkills(){
		return skills;
	}
	public void setCoords(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getXCoord(){
		return this.x;
	}
	public int getYCoord(){
		return this.y;
	}
	public Character getThis(){
		return this;
	}

}
