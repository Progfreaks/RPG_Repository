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
		eCreator = new GuiEventCreator(EVENT_TYPE.DEFAULT);
		
		//guiMgr.setBackFrame(new BackFrame());
		guiMgr.initBackFrame();
		IntroScreen introScreen = new IntroScreen();
		eCreator.setStartCall(introScreen.getSingleButton());
		guiMgr.addToMainPanel(introScreen.getIntroScreen(), 0, "");
		

	}

	public static void main(String[] args) {

		new StartGUI();
	}



}
