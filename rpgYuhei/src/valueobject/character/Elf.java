package valueobject.character;

import valueobject.IO;

public class Elf extends Character{

	public Elf(String name, int life) {
		super(name, life);
	}

	@Override
	public int attack() {
		
		return IO.selectCommandMessage(this);
	}

}
