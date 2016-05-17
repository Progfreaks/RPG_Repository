package gui.events;

import gui.GuiManager;
import gui.GameConsole;
import persistence.character.CharacterDataMap;
import domain.CharacterManager;
import valueobject.character.Character;
import domain.DuD;


public class FightEvent extends GameEvent {
	
	private DuD game;
	Character player;
	Character enemy;
	CharacterManager cm;
	private int index = 0;
	private int xf, yf;
	GuiManager guiMgr;
	
	public FightEvent(int x, int y){
		this.xf = x;
		this.yf = y;
		game = DuD.getGame();
		guiMgr = GuiManager.getInstance();
		guiMgr.repaintButton(x, y);
		
	}
	
	@Override
	public void process() {
		
		guiMgr.repaintButton(xf, yf);
		player = game.getPlayer(0);
	    CharacterDataMap map = CharacterDataMap.getInstance();
		Character enemy = game.createEnemy(map.getCharacterData(5));
		game.addEnemy(enemy);
		this.index++;
		GameConsole console = GameConsole.getInstance();
		console.roundMessage(player, enemy);
	}

	@Override
	public void run() {
		process();
		
	}

}
