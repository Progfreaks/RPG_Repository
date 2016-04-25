package domain;
import valueobject.character.Character;
import java.io.*;
import domain.DuD;
import valueobject.Dice;

import valueobject.IO;

import java.util.Random;


public class GameCycle {
	
	Character p01;
	private int endIndicator;
	private int rounds;
	private Dice playDice;
	private DuD game;
	

	public int getRounds(){
		return this.rounds;
	}
    public int getIndicator(){
    	return this.endIndicator;
    }
	
    public void gameCycle(){
    		int diceNum = 0;
    		diceNum = playDice.diceForRound(game.getPlayer(0), rounds);
    		game.callForAction(diceNum);
    		rounds++;
    	}
    	
    	
    
	
	public GameCycle(DuD game){
		this.endIndicator = 0;
		this.rounds = 1;
		playDice = new Dice();
		this.game = game;
		gameCycle();
		
	}

}
