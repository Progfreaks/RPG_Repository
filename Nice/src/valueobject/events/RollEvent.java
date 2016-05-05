package valueobject.events;

import domain.DuD;
import valueobject.Dice;

public class RollEvent extends GameEvent {
	private DuD game;
	private int diceNum; 
	private int rounds = 0;
	public RollEvent(){
		game = DuD.getGame();
		rounds++;
		
		
	}
	public void process(){
	
		game.nextRound();	
	}
	

}
