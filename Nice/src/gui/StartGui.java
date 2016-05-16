package gui;

import gui.EventCreator.EVENT_TYPE;
import gui.objects.BackFrame;
import gui.objects.sublayer.IntroScreen;
import domain.DuD;

public class StartGUI {

	private EventCreator eCreator;
	private GuiManager guiMgr;

	/**
	 * Konstruktor dieser Klasse.
	 */
	public StartGUI() {

		DuD game = new DuD();
		guiMgr = new GuiManager();
		eCreator = new EventCreator(EVENT_TYPE.DEFAULT);
		game.setGame(game);
		guiMgr.setBackFrame(new BackFrame());
		IntroScreen introScreen = new IntroScreen();
		eCreator.setStartCall(introScreen.getSingleButton());
		guiMgr.addPanel(introScreen.getIntroScreen(), 0, "");
		MyConsole console = new MyConsole();
		game.setConsole(console);

	}

	public static void main(String[] args) {

		new StartGUI();
	}



}
