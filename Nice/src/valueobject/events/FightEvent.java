package valueobject.events;

import domain.BattleManager;
import domain.CharacterManager;
import valueobject.PlayerArray;
import valueobject.EnemyArray;
import valueobject.character.Character;
import valueobject.character.CharacterEnum;
import domain.DuD;
import persistence.character.CharacterDataMap;
import domain.BattleManager;


public class FightEvent extends GameEvent {
	private DuD game;
	Character player;
	Character enemy;
	CharacterManager cm;
	BattleManager battleRoom;
	private int index = 0;
	private int xf, yf;
	public FightEvent(int x, int y){
		this.xf = x;
		this.yf = y;
		game = DuD.getGame();
	}
	@Override
	public void process() {
		
		player = PlayerArray.getPlayer(0);
		
		CharacterDataMap map = CharacterDataMap.getInstance();

		
		Character enemy = game.createEnemy(map.getCharacterData(5));
		EnemyArray.addPlayer(enemy);
		battleRoom = game.getBattleMgr();
		
		battleRoom.addPlayer(player);
		battleRoom.addEnemy(EnemyArray.getPlayer(index));
		battleRoom.startBattle();
		this.index++;
		game.recolour(this.xf,this.yf);
	}

}
