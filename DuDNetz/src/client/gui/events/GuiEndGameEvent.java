package client.gui.events;

import server.domain.DuD;
import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiMapHandler;

public class GuiEndGameEvent implements GuiGameEvent, Runnable{
	
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
