package gui.creater;

import gui.creater.GuiEventCreator.EVENT_TYPE;
import domain.DuD;

public class StartGUI {

	private GuiEventCreator eCreator;
	private GuiManager guiMgr;

	/**
	 * Konstruktor dieser Klasse.
	 */
	public StartGUI() {

		DuD.getGame();
		GuiGameConsole.getInstance();
		guiMgr = GuiManager.getInstance();
		eCreator = new GuiEventCreator(EVENT_TYPE.DEFAULT);
		guiMgr.initBackFrame();
		guiMgr.createNewOrLoadPanel();
		
		

	}

	public static void main(String[] args) {

		new StartGUI();
	
	}



}
