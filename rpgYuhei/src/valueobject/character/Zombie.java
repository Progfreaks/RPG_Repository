package valueobject.character;

import valueobject.Dice;//
import valueobject.IO;

public class Zombie extends Character{

	public Zombie(final String name,final int life) {
		super(name, life);
	}

	@Override
	public int attack() {
		
		int damage = 0;
		
		if(IO.checkMP(this, 8)){
			setMP(-8);//je Angriff 8MP verwendet
			damage = Dice.diceForAtk(this);
		}else{
			setMP(Dice.diceForMP(this));
		}
		return damage;
		
		
	}

}
