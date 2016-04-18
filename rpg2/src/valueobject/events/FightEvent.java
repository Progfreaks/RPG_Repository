package valueobject.events;

import domain.BattleRoom;
import valueobject.Warrior;
import valueobject.Zombie;
import valueobject.ICharacter;

public class FightEvent extends GameEvent {
	ICharacter p01;
	@Override
	public void process() {

		p01 = new Warrior("Held", 100);
		Zombie z01 = new Zombie("Zombie", 40);
        p01.setIsPlayer(true);		
		  

        z01.setIsPlayer(false);		

		
        //p01.attack();
		BattleRoom battleRoom = new BattleRoom();
		
		battleRoom.addPlayer(p01);
		battleRoom.addEnemy(z01);
		battleRoom.startBattle();
	
	}

}
