package valueobject.events;

import domain.BattleManager;
import domain.CharacterManager;
import valueobject.PlayerArray;
import valueobject.character.Character;
import valueobject.character.CharacterEnum;


public class FightEvent extends GameEvent {
	
	Character player;
	Character enemy;
	CharacterManager cm;
	
	@Override
	public void process() {

		player = PlayerArray.getPlayer(0);

	    cm = new CharacterManager();
		
		Character enemy = cm.createCharacter(CharacterEnum.Zombie);
		
		BattleManager battleRoom = new BattleManager();
		
		battleRoom.addPlayer(player);
		battleRoom.addEnemy(enemy);
		battleRoom.startBattle();
	
	}

}
