package valueobject.character;

import valueobject.Dice;
import valueobject.IO;



public  class Character{
	
	private String name;
	
	private int life;
	
	private int level;
	
	private int exPoint;
	
	private boolean isAlive;
	
	private boolean isPlayer;
	
	private final int maxLife;
	
	private int manaPoint;
	
	private int whichCharacter;
	
	
	public  Character(final String name,final int life) {
		
		this.name = name;
		this.life = life;
		this.level = 1;
		this.exPoint = 0;
		this.isAlive = true;
		this.isPlayer = true;
		this.manaPoint = 36;
		
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

	public void print(){
		System.out.println("Name:"+getName()+" Life:"+getLife()+"/"+getMaxLife()
				+" MP:"+getMP()+"/36"+" Level:"+getLevel()+"\n");
	}
	
	public int attack(){
		return isPlayer() ? IO.selectCommandMessage(this):Dice.diceForAtk(this);
	}
	
	public void setMP(final int mp){
		manaPoint += mp;
		if(manaPoint < 0) manaPoint = 0;
	}
	
	public int getMP(){
		return manaPoint;
	}

}
