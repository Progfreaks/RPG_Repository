package domain;

import valueobject.PlayerArray;
import valueobject.character.Character;
import valueobject.character.*;
import valueobject.events.StartEvent;


public class TestCUI {

	static Character player = null;
	static Character enemy = null;
	static CharacterManager cm ;

	

	public static void main(String[] args) {

		 
		cm = new CharacterManager();
		StartEvent sEvent = new StartEvent();
		sEvent.process();
		
		
		player = PlayerArray.getPlayer(0);
		enemy = cm.createCharacter(CharacterEnum.Zombie);
		
		BattleManager bm = new BattleManager();
		
		bm.addPlayer(player);
		bm.addEnemy(enemy);
		
		bm.startBattle();


      
	}
	

}



