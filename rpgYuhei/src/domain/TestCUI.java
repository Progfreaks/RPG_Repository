package domain;

import valueobject.PlayerArray;
import valueobject.character.Character;
import valueobject.character.*;
import valueobject.events.StartEvent;


public class TestCUI {

	static Character p01 = null;
	static Character e01 = null;

	

	public static void main(String[] args) {

		 
		StartEvent sEvent = new StartEvent();
		sEvent.process();
		
		p01 = PlayerArray.getPlayer(0);

		e01 = new Zombie("Zombie", 40);
        
		p01.setIsPlayer(true);		
        e01.setIsPlayer(false);		

		
		BattleRoom battleRoom = new BattleRoom();
		
		battleRoom.addPlayer(p01);
		battleRoom.addEnemy(e01);
		
		battleRoom.startBattle();


      
	}
	

}



