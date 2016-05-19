package gui.events;

import gui.creater.GuiManager;
import domain.DuD;

public class GuiRollEvent extends GuiGameEvent {

	private DuD game;
	private int diceNum;
	private int rounds = 0;

	public GuiRollEvent() {
		game = DuD.getGame();
		rounds++;
	}

	public void process() {
//		MyConsole console = game.getConsole();
//		console.printMsg("from process");
		diceNum = game.nextRound();
		
		GuiManager guiMgr = GuiManager.getInstance();
		guiMgr.paintMoveRange(diceNum);
		
	}

	public int rollProcess() {
		return game.nextRound();

	}
	
	public int getDiceNum() {
		return diceNum;
	}

	@Override
	public void run() {
		process();
		
	}

}
