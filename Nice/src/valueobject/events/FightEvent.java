package valueobject.events;

import domain.BattleManager;
import domain.CharacterManager;
import valueobject.PlayerArray;
import valueobject.character.Character;
import valueobject.character.CharacterEnum;
import domain.DuD;


public class FightEvent extends GameEvent {
	private DuD game;
	Character player;
	Character enemy;
	CharacterManager cm;
	
	@Override
	public void process() {
		game = DuD.getGame();
		player = PlayerArray.getPlayer(0);

	    cm = game.getCharMgr();
		
		Character enemy = cm.createCharacter(CharacterEnum.Zombie);
		
		BattleManager battleRoom = new BattleManager();
		
		battleRoom.addPlayer(player);
		battleRoom.addEnemy(enemy);
		battleRoom.startBattle();
	
	}

}
