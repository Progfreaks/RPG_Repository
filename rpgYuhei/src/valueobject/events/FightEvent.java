package valueobject.events;

import domain.BattleRoom;
import valueobject.PlayerArray;
import valueobject.character.Character;
import valueobject.character.Warrior;
import valueobject.character.Zombie;

public class FightEvent extends GameEvent {
	Character p01;
	
	@Override
	public void process() {

		//p01 = PlayerArray.list.get(0);//Bekommt ein Spieler vom PlayerArray 
		p01 = new Warrior("Held", 100);

		Zombie z01 = new Zombie("Zombie", 40);
	    z01.setIsPlayer(false);		

		
		BattleRoom battleRoom = new BattleRoom();
		
		battleRoom.addPlayer(p01);
		battleRoom.addEnemy(z01);
		battleRoom.startBattle();
	
	}

}
