package valueobject;

public class Zombie extends Character{

	public Zombie(final String name,final int life) {
		super(name, life);
	}

	@Override
	public int attack() {
		int damage = 0;
		
		

			Dice dice = new Dice();
			damage = dice.shakeDice(this);

			
		
		return damage;
		
		
	}

}
