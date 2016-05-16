package gui.events;

import gui.EventCreator;
import gui.GuiManager;
import gui.objects.ButtonPanel;
import gui.objects.MenuPanel;
import domain.DuD;

/**
 * Klasse fuer die Konfiguration vom Spiel-Angfang
 * 
 * @author YOU_HEY
 *
 */
public class StartEvent extends GameEvent   {

	private DuD game;
	private GuiManager guiMgr;
	private EventCreator eCreator;

	public  StartEvent(EventCreator e) {
		guiMgr = new GuiManager();
		eCreator = e;
	}

	@Override
	public void process() {

		game = DuD.getGame();
		guiMgr.removePanel(0);
		guiMgr.createOpeningPanel();
		game.addPlayer(game.createPlayer(game.getConsole().selectCharacter()));
		setUpNewGame();
	}


	@Override
	public void run() {
		process();

	}

	private void setUpNewGame() {


		guiMgr.removePanel(0);                    
		guiMgr.paintButtonPanel();
		eCreator.setActionCalls(guiMgr.getButtonMatrix());
		ButtonPanel buttonPanel  = guiMgr.getButtonPanel();
		MenuPanel menuPanel = new MenuPanel();
		eCreator.setRollCall(menuPanel.getRollButton());
		guiMgr.createMainPanel(buttonPanel,menuPanel);

	}


}
