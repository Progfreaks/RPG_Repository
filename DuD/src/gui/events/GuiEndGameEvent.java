package gui.events;

import gui.manager.GuiGameConsole;
import gui.manager.GuiManager;
import gui.manager.GuiMapHandler;
import valueobject.Character;
import domain.DuD;

public class GuiEndGameEvent extends GuiGameEvent implements Runnable{
	
	private DuD game;
	private GuiGameConsole console;
	private GuiMapHandler mapHdr;
	
	public GuiEndGameEvent(){
		game = DuD.getGame();
		mapHdr = GuiMapHandler.getInstance();
		console = GuiGameConsole.getInstance();
	}
	public void run() {
		mapHdr.placeBoss();
		console.appendln("Endgegner ist aufgetreten!!!!!!\n");
		
	}

	@Override
	public void process() {
		run();
		
	}

}
