package client.gui.events;

import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiMapHandler;
import common.valueobject.Dice;

public class GuiRollEvent extends Thread implements GuiGameEvent {

	private GuiMapHandler mapHdr;

	public GuiRollEvent() {
		mapHdr = GuiMapHandler.getInstance();
	}

	
	
	public void process() {
		start();
	}
	
	public void run(){
		int diceNum = Dice.getRandomNummer(6);
		GuiGameConsole.getInstance().showNumMessage(diceNum);
		mapHdr.paintMoveRange(diceNum);
	}




}
