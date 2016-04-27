package domain;

import valueobject.IO;
import valueobject.PlayerArray;
import valueobject.character.Character;
import valueobject.character.*;
import valueobject.events.StartEvent;


public class TestCUI {

	static Character player = null;
	static Character enemy = null;
	static CharacterManager cm;


	public static void main(String[] args) {

		 
		DuD game = new DuD();
		game.setGame(game);

		
		CharacterManager cm = new CharacterManager(game);

		player = cm.createCharacter(IO.chooseCharacterMessage());

		PlayerArray.addPlayer(player);// Der ausgewaehlte Player ist ins Array hinzugefuegt.
										
		player = PlayerArray.getPlayer(0);
		enemy = cm.createCharacter(CharacterEnum.Zombie);

		BattleManager bm = new BattleManager(new DuD());

		bm.addPlayer(player);
		bm.addEnemy(enemy);

		bm.startBattle();



      
	}
	

}



