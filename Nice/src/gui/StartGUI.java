package gui;

import gui.GuiEventCreator.EVENT_TYPE;
import gui.objects.BackFrame;
import gui.objects.sublayer.IntroScreen;
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
		
		
		//guiMgr.setBackFrame(new BackFrame());
		guiMgr.initBackFrame();
		guiMgr.createIntroScreen();
		

	}

	public static void main(String[] args) {

		new StartGUI();
	}



}
