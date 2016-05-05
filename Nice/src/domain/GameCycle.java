package domain;
import valueobject.character.Character;
import java.io.*;
import domain.DuD;
import valueobject.Dice;


import java.util.Random;


public class GameCycle {
	
	private Character p01;
	private boolean endIndicator;
	private static int rounds;
	private Dice playDice;
	private static DuD game;
	

	public static int getRounds(){
		return rounds;
	}
    public boolean getIndicator(){
    	return this.endIndicator;
    }
    public void setIndicator(boolean in){
    	
    }
	
    public void nextRound(){
    	if(!endIndicator){
    		p01 = game.getPlayer(0);
    		int diceNum = 0;
    		diceNum = Dice.diceForRound(p01, rounds);
    		game.callForAction(diceNum);
    		rounds++;
    	}
    	else{
    		p01 = game.getPlayer(0);
    		int diceNum = 0;
    		diceNum = Dice.diceForRound(p01, rounds);
    		game.callForAction(diceNum);
    		rounds++;
    	}
    }
    	
    	
    
	
	public GameCycle(DuD game){
		this.endIndicator = false;
		this.rounds = 1;
		playDice = new Dice();
		this.game = game;
		
	}

}
