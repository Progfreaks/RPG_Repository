package valueobject.events;

import valueobject.IO;
import domain.DuD;
import valueobject.Dice;

public class RollEvent extends GameEvent {
	private DuD game;
	private Dice playDice = new Dice();
	private int diceNum; 
	private int rounds = 1;
	public RollEvent(){
		game = DuD.getGame();
		this.diceNum = 0;
		
	}
	public void process(){
		diceNum = playDice.diceForRound(game.getPlayer(0), rounds);
		game.callForAction(diceNum);
		rounds++;
		
		
		
	}
	

}
