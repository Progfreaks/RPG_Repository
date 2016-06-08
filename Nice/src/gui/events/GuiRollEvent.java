package gui.events;

import gui.manager.GuiGameConsole;
import gui.manager.GuiMapHandler;
import valueobject.Dice;

public class GuiRollEvent extends GuiGameEvent {

	private GuiMapHandler mapHdr;

	public GuiRollEvent() {
		mapHdr = GuiMapHandler.getInstance();
	}

	
	@Override
	public void run() {
		process();

	}
	
	public void process() {
		int diceNum = Dice.getRandomNummer(6);
		GuiGameConsole.getInstance().showNumMessage(diceNum);
		mapHdr.paintMoveRange(diceNum);
	}




}
