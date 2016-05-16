package gui.events;

import gui.GuiManager;
import gui.MyConsole;
import domain.DuD;
import valueobject.Dice;

public class RollEvent extends GameEvent {

	private DuD game;
	private int diceNum;
	private int rounds = 0;

	public RollEvent() {
		game = DuD.getGame();
		rounds++;
	}

	public void process() {
//		MyConsole console = game.getConsole();
//		console.printMsg("from process");
		diceNum = game.nextRound();
		
		GuiManager guiMgr = new GuiManager();
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