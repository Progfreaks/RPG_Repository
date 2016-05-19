package gui.events;

import gui.creater.GuiGameConsole;
import gui.creater.GuiManager;
import persistence.character.CharacterDataMap;
import valueobject.Character;
import domain.CharacterManager;
import domain.DuD;


public class GuiFightEvent extends GuiGameEvent {
	
	private DuD game;
	Character player;
	Character enemy;
	CharacterManager cm;
	private int index = 0;
	private int xf, yf;
	GuiManager guiMgr;
	
	public GuiFightEvent(int x, int y){
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
		GuiGameConsole console = GuiGameConsole.getInstance();
		console.roundMessage(player, enemy);
	}

	@Override
	public void run() {
		process();
		
	}

}
