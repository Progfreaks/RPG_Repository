package valueobject.character;

import valueobject.IO;

public class Wizzard extends Character{

	public Wizzard(String name, int life) {
		super(name, life);
		
	}

	@Override
	public int attack() {
		
		return IO.selectCommandMessage(this);
	}

}
