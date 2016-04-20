package valueobject.character;


import valueobject.IO;

public class Warrior extends Character{

	public Warrior(String name, int life) {
		super(name, life);
		
	}

	@Override
	public int attack() {
		
		return IO.selectCommandMessage(this);
	}
	
	

}
